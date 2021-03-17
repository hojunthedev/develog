package com.hj.blog.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.hj.blog.config.auth.PrincipalDetail;

@Controller
public class BoardController {

	@GetMapping({ "", "/" })
	public String index(@AuthenticationPrincipal PrincipalDetail principal) { // 세션 접근법
		System.out.println("로그인 사용자 아이디:" + principal.getUsername());
		
		// /WEB-INF/views/index.jsp
		return "index";
	}
}
