package org.fintech.service;

import static org.junit.Assert.assertNotNull;

import org.fintech.domain.BoardVO;
import org.fintech.domain.Criteria;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardServiceTests {

	@Setter(onMethod_ = @Autowired)
	private BoardService service;	
	
	@Ignore
	public void testExist() {

		log.info(service);
		
		//객체가 null인지 유무 체크
		assertNotNull(service);
		
	}
	
	@Ignore
	public void testRegister() {
		
		BoardVO board = new BoardVO();
		
		board.setTitle("주문등록");
		board.setContent("빠른 배송 요망");
		board.setWriter("홍길동");
		
		service.register(board);
		
		log.info("생성된 게시물 번호:" + board.getBno());
		
	}
	
	@Test
	public void testGetList() {

		/* service.getList().forEach(board -> log.info(board)); */
		service.getList(new Criteria(2, 10)).forEach(board -> log.info(board));
		
	}
	
	@Ignore
	public void testGet() {
		log.info(service.get(22L));
	}
	
	@Ignore
	public void testDelete() {
		log.info("게시판 삭제 결과:" + service.remove(8L));
	}
	
	@Ignore
	public void testUpdate() {
		BoardVO board = service.get(22L);
		
		if(board == null) {
			return;
		}
		
		board.setTitle("배송요망");

		log.info("게시판 수정 결과:" + service.modify(board));
		
	}
	
	
}
