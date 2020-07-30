package org.fintech.service;

import java.util.List;

import org.fintech.domain.BoardVO;
import org.fintech.domain.Criteria;

public interface BoardService {

	//게시물 등록
	public void register(BoardVO board);
	
	//특정 게시물 상세보기
	public BoardVO get(Long bno);
	
	//게시물 수정
	public boolean modify(BoardVO board);
	
	//게시물 삭제
	public boolean remove(Long bno);
	
	//게시판 전체조회
	/* public List<BoardVO> getList(); */
	public List<BoardVO> getList(Criteria cri);
	
	//2020-07-30 전체 데이터 갯수
	public int getTotal(Criteria cri);
}
