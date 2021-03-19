package com.hj.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hj.blog.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@GetMapping({ "", "/" })
	public String index(Model model, @PageableDefault(size=3, sort="id", direction = Sort.Direction.DESC) Pageable pageable ) {
		model.addAttribute("boards", boardService.글목록(pageable));
		return "index"; // viewResolver 작동! 해당 페이지로 model의 정보를가지고 이동.
	}
	
	@GetMapping({ "/board/saveForm"})
	public String saveForm() {
		// /WEB-INF/views/index.jsp
		return "board/saveForm";
	}
	
}
