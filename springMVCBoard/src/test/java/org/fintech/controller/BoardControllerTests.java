package org.fintech.controller;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
//객체를 테스트한다는 선언
@RunWith(SpringJUnit4ClassRunner.class)
//Controller 및 Web 환경에 사용되는 Bean들을
//자동 생성하여 등록처리
@WebAppConfiguration
//설정파일 위치 지정
@ContextConfiguration({
	"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
@Log4j
public class BoardControllerTests {

	@Setter(onMethod_ = @Autowired)
	public WebApplicationContext ctx;
	
	private MockMvc mockMvc;
	
	//@Test 보다 먼저 수행
	//MockMvcBuilders
	//요청 데이터를 설정할 때 사용하는 
	//static method
	//mockmvc를 사용하기 위한 사전 설정
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	@Ignore
	public void testList() throws Exception {
		
		//perform() : 결과값을 ResultActions
		//객체로 받는다.
		//andReturn: 테스트한 결과값을 리턴받을때
		//선언
		//getModelAndView()
		//리턴되는 View와 Model에 저장된 속성값을 확인
		//getModelMap()
		//리턴값을 (Key,Value) 형태로 가져온다.
		
		log.info(
			mockMvc.perform(MockMvcRequestBuilders.get("/board/list"))
			       .andReturn()
			       .getModelAndView()
			       .getModelMap()
				);
		
	}
	
	//게시판 등록 테스트
	@Ignore
	public void testRegister() throws Exception{
		
		String resultPage = 
			mockMvc.perform(
				MockMvcRequestBuilders.post("/board/register")
					.param("title","신상품 출시")
		           	.param("content","7월24일 기준")
		           	.param("writer","이순신"))
				.andReturn()
		        .getModelAndView()
		        .getViewName();
		
		log.info(resultPage);
	}
	
	//게시판 상세보기 테스트
	@Ignore
	public void testGet() throws Exception {
		log.info(mockMvc.perform(MockMvcRequestBuilders.get("/board/get")
				         .param("bno","22"))
				        .andReturn()
				        .getModelAndView()
				        .getModelMap());
	}
	
	//게시판 수정 테스트
	@Ignore
	public void testModify() throws Exception {
		
		String resultPage 
		    = mockMvc.perform(MockMvcRequestBuilders.post("/board/modify")
					 .param("bno","3")
					 .param("title","수정 테스트 제목임")
					 .param("content","수정 내용임")
					 .param("writer","강감찬"))
		         	 .andReturn()
		             .getModelAndView()
		             .getViewName();
		
		log.info(resultPage);
	}
	
	//게시판 수정 테스트
	@Ignore
	public void testRemove() throws Exception {
		String resultPage =
		  mockMvc.perform(MockMvcRequestBuilders.post("/board/remove")
				 .param("bno","24"))
		         .andReturn()
		         .getModelAndView()
		         .getViewName();
	
		log.info(resultPage);
		
	}
	
	//2020-07-30 페이징처리
	@Test
	public void testListPaging() throws Exception {
		
		log.info(mockMvc.perform(MockMvcRequestBuilders.get("/board/list")
						.param("pageNum", "2")
						.param("amount", "50"))
						.andReturn().getModelAndView().getModelMap());
		
	}
}
