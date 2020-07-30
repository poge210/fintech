package org.fintech.controller;

import org.fintech.domain.BoardVO;
import org.fintech.domain.Criteria;
import org.fintech.domain.PageDTO;
import org.fintech.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
//초기 root url 지정
@RequestMapping("/board/*")
//모든 멤버변수를 포함하는 생성자 생성
@AllArgsConstructor
public class BoardController {
	
	@Setter(onMethod_ = @Autowired)
	private BoardService service;
	
	/*
	 * @GetMapping("/list") public void list(Model model) {
	 * 
	 * log.info("list");
	 * 
	 * //속성추가 model.addAttribute("list",service.getList()); }
	 */
	@GetMapping("/list")
	public void list(Criteria cri, Model model) {
		
		log.info("list: " + cri);
		model.addAttribute("list", service.getList(cri));
		int total = service.getTotal(cri);
		log.info("total: " + total);
		
		model.addAttribute("pageMaker", new PageDTO(cri, total));
	}
	
	//게시판 등록
	//RedirectAttributes ?
	//모든 flashAttribute 들을 redirect
	//하기전에 모두 Session영역에 복사했다가
	//redirect한후에 Session에 있는
	//flashAttribute 정보들을 Model 객체로 이동
	//Header정보에 매개변수를 붙이지 않기 때문에
	//URL에 노출위험성은 없음
	
	@PostMapping("/register")
	public String register(BoardVO board,RedirectAttributes rttr) { 
		log.info("게시판 등록:" + board);
		service.register(board);
		rttr.addFlashAttribute("result", board.getBno());
		return "redirect:/board/list"; 
	}
	
	@GetMapping("/register")
	public void register() {
		
	}
	
	//게시판 상세보기
	@GetMapping({"/get","/modify"})
	//@RequestParam : url에 있는 매개변수를
	//변수에 대입
	public void get(@RequestParam("bno") Long bno,@ModelAttribute("cri") Criteria cri, Model model) {
		log.info("/get or midify");
		model.addAttribute("board",service.get(bno));
	}
	
	//게시판 수정
	@PostMapping("/modify")
	public String modify(BoardVO board,@ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
		log.info("modify:" + board);
		
		if(service.modify(board)) {
			rttr.addFlashAttribute("result","success");
		}
		
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		
		return "redirect:/board/list";
	}
	
	//게시판 삭제
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno,@ModelAttribute("cri") Criteria cri, 
			RedirectAttributes rttr) {
		
		log.info("remove..." + bno);
		
		if(service.remove(bno)) {
			rttr.addFlashAttribute("result","success");
		}
		
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		
		return "redirect:/board/list";
	}
}




