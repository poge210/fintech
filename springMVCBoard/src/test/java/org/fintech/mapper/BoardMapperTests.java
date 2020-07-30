package org.fintech.mapper;

import java.util.List;

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
public class BoardMapperTests {
	
	//DI(Dependency Injection)개념에 의한 자동주입
	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;
	
	@Ignore
	public void testGetList() {
		 mapper.getList().forEach(board -> log.info(board));
	}
	
	@Ignore
	public void testInsert() {
		
		BoardVO board = new BoardVO();
		
		board.setTitle("새로 작성하는 글");
		board.setContent("새로 작성하는 내용");
		board.setWriter("홍길동");
		
		mapper.insert(board);
		
		log.info(board);
		
	}
	
	@Ignore
	public void testInsertSelectKey() {
		
		BoardVO board = new BoardVO();
		
		board.setTitle("새로 작성한 글 selectkey");
		board.setContent("새로 작성한 내용 selectkey");
		board.setWriter("이순신");
		
		mapper.insertSelectKey(board);
		
		log.info(board);
		
	}

	//특정 게시판번호 상세보기 테스트
	@Ignore
	public void testRead() {
		
		BoardVO board = mapper.read(8L);
		
		log.info(board);
	}
	
	@Ignore
	public void testDelete() {
		log.info("삭제건수:" + mapper.delete(10L));
	}
	
	@Ignore
	public void testUpdate() {
		
		BoardVO board = new BoardVO();
		
		board.setBno(2L);
		board.setTitle("수정된 제목");
		board.setContent("수정된 내용");
		board.setWriter("강감찬");
		
		int count = mapper.update(board);
		
		log.info("수정건수:" + count);
	}
	
	@Test
	public void testPaging() {
		
		Criteria cri = new Criteria();
		
		cri.setPageNum(3);
		cri.setAmount(10);
		
		List<BoardVO> list = mapper.getListWithPaging(cri);
		
		list.forEach(board -> log.info(board));
	}
	
	
}




