package org.fintech.mapper;

import java.util.List;

import org.fintech.domain.BoardVO;
import org.fintech.domain.Criteria;

//인터페이스 = 추상메서드 + 상수로 구성
public interface BoardMapper {

	//@Select("select * from tbl_board where bno > 0")
	public List<BoardVO> getList();
	
	//페이징 처리 2020-07-30
	public List<BoardVO> getListWithPaging(Criteria cri);
	
	//게시판 등록 테스트
	public void insert(BoardVO board);
	
	//게시판 등록 테스트인데
	//게시판에 insert 되기 전에 등록될
	//Key값을 미리 가져온다
	public void insertSelectKey(BoardVO board);
	
	//게시판 상세보기
	public BoardVO read(Long bno);
	
	//특정 게시판 내역 삭제
	public int delete(Long bno);
	
	//특정 게시판 수정
	public int update(BoardVO board);
	
	//조회시 전체 데이터 건수 2020-07-30
	public int getTotalCount(Criteria cri);
}




