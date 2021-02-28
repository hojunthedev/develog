package com.hj.blog.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hj.blog.model.RoleType;
import com.hj.blog.model.User;
import com.hj.blog.repository.UserRepository;

@RestController
public class DummyControllerTest {
	
	@Autowired //UserRepository 타입으로 스프링이 관리하고있으면(메모리에 떠있으면) 같이 메모리에 띄워줘라(의존성 주입, DI)
	private UserRepository userRepository;
	
	// http://localhost:8000/develog/dummy/join (요청)
	// http의 body에 username, password, email 데이터를 가지고 요청
	@PostMapping("/dummy/join")
	//public String join(String username, String password, String email) { // 마임타입form key=value (약속된 규칙)
	public String join(User user) { // key=value (약속된 규칙)
		System.out.println("id : " + user.getId());
		System.out.println("username : " + user.getUsername());
		System.out.println("password : " + user.getPassword());
		System.out.println("email : " + user.getEmail());
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입이 완료되었습니다.";
	}
}
