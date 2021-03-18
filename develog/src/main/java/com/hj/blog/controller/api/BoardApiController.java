package com.hj.blog.controller.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hj.blog.config.auth.PrincipalDetail;
import com.hj.blog.dto.ResponseDto;
import com.hj.blog.model.Board;
import com.hj.blog.service.BoardService;

@RestController
public class BoardApiController {
	
	@Autowired
	private BoardService boardService;
	
	@PostMapping("/api/board")
	public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal) {
		boardService.글쓰기(board, principal.getUser());
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	
	
	
	
// 전통적인 방식의 로그인
//	@PostMapping("/api/user/login")
//	public ResponseDto<Integer> login(@RequestBody User user){ //매개변수에 httpsession 직접 적어도되고
//		System.out.println("UserApiController : login 호출됨");
//		User principal = userService.로그인(user); // principal (접근주체)
//		
//		if(principal != null) {
//			session.setAttribute("principal", principal);
//		}
//		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); 
//	}
	
	
}
