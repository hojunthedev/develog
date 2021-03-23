package com.hj.blog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hj.blog.model.RoleType;
import com.hj.blog.model.User;
import com.hj.blog.repository.UserRepository;

// 스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌. IoC를 해준다.
@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Transactional
	public void 회원가입(User user) {
		String rawPassword = user.getPassword();
		String encPassword = encoder.encode(rawPassword);
		user.setPassword(encPassword);
		user.setRole(RoleType.USER);
		userRepository.save(user);
	}

	@Transactional
	public void 회원수정(User user) {
		// 수정 시, 영속성 컨텍스트 User오브젝트를 영속화 시키고, 영속화 된 User오브젝트를 수정
		// select를 해서 User오브젝트를 DB로 부터 가져와 영속화 한다
		User persistance = userRepository.findById(user.getId()).orElseThrow(()->{
			return new IllegalArgumentException("회원 찾기 실패");
		}) ;
		String rawPassword = user.getPassword();
		String encPassword = encoder.encode(rawPassword);
		persistance.setPassword(encPassword);
		persistance.setEmail(user.getEmail());
		// 회원 수정 함수 종료 = 서비스 종료 = 트랜잭션 종료 = auto commit
		// 영속화 된 persistance객체의 변화가 감지되면 더티체킹이 되어 update문을 날려줌 (flush)
	}
	
	
	
//	@Transactional(readOnly = true) // select 시 트랜잭션 시작, 서비스 종료시 트랜잭션 종료(정합성)
//	public User 로그인(User user) {
//		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//	}
}
