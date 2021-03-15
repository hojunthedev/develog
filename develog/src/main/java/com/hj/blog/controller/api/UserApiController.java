package com.hj.blog.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hj.blog.dto.ResponseDto;
import com.hj.blog.model.RoleType;
import com.hj.blog.model.User;
import com.hj.blog.service.UserService;

@RestController
public class UserApiController {
	
	@Autowired
	private UserService userService;
	
//	@Autowired //세션 객체는 스프링 컨테이너가 빈으로 가지고있다.
//	private HttpSession session;
	
	@PostMapping("/api/user")
	public ResponseDto<Integer> save(@RequestBody User user) {
		System.out.println("UserApiController : save 호출됨");
		user.setRole(RoleType.USER);
		userService.회원가입(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	// TODO: 스프링 시큐리티를 이용해 로그인
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
