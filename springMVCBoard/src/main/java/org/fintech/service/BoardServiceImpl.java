package org.fintech.service;

import java.util.List;

import org.fintech.domain.BoardVO;
import org.fintech.domain.Criteria;
import org.fintech.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
//해당객체가 비즈니스 영역을 담당하는 객체
//임을 선언
@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService {

	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;
	
	//게시판 등록
	@Override
	public void register(BoardVO board) {
		
		log.info("게시판 등록:" + board);
		
		mapper.insertSelectKey(board);
		
	}

	//특정 게시판 상세보기
	@Override
	public BoardVO get(Long bno) {
		
		log.info("상세보기:" + bno);
		
		return mapper.read(bno);
	}

	@Override
	public boolean modify(BoardVO board) {
		log.info("게시판 수정:" + board);
		
		return mapper.update(board) == 1;
	}

	@Override
	public boolean remove(Long bno) {
		log.info("게시판 삭제:" + bno);
		
		return mapper.delete(bno) == 1;
	}

	//게시판 전체조회
	/*
	 * @Override public List<BoardVO> getList() {
	 * 
	 * log.info("getList");
	 * 
	 * return mapper.getList(); }
	 */
	@Override
	public List<BoardVO> getList(Criteria cri) {
		
		log.info("get List with criteria: " + cri);
		
		return mapper.getListWithPaging(cri);
	}
	
	@Override
	public int getTotal(Criteria cri) {
		
		log.info("get total count");
		return mapper.getTotalCount(cri);
	}
}
