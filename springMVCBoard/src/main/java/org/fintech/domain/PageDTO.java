package org.fintech.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageDTO {
	
	//startPage : 각 페이지에 대해 하단에 표시되는 첫번째 페이지 
	private int startPage;
	//endPage : 각 페이지에 대해 하단에 표시되는 마지막 페이지
	private int endPage;
	//이전페이지,다음페이지 존재여부 체크
	private boolean prev, next;
	//검색된 총 데이터 건수
	private int total;
	//페이징 처리
	private Criteria cri;
	
	public PageDTO(Criteria cri, int total) {
		
		this.cri = cri;
		this.total = total;
		
		//ceil : 소수점 올림처리
		//floor(7.6) -> 7 (소수점 버림)
		//round(10.462,2) -> 10.47(소수점 올림)
		//trunc(10.467,2) -> 10.46(소수점 버림)
		
		this.endPage = (int) (Math.ceil(cri.getPageNum() / 10.0)) * 10;
		this.startPage = this.endPage - 9;
		int realEnd = (int) (Math.ceil((total * 1.0) / cri.getAmount()));
		
		//실제 페이지수가 하단에 표시되는 endPage보다 작으면 실제페이지수를 endPage로 지정
		if (realEnd < this.endPage) {
			this.endPage = realEnd;
		}
		//이전페이지가 존재하는 경우 true 없으면 false
		this.prev = this.startPage > 1;
		//다음페이지가 존재하는 경우 true 없으면 false
		this.next = this.endPage < realEnd;
		
	}
	
}
