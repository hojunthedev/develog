package com.hj.blog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hj.blog.model.Board;
import com.hj.blog.model.RoleType;
import com.hj.blog.model.User;
import com.hj.blog.repository.BoardRepository;
import com.hj.blog.repository.UserRepository;

@Service
public class BoardService {
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Transactional
	public void 글쓰기(Board board, User user) {// title, content
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
	}
	
	
	
//	@Transactional(readOnly = true) // select 시 트랜잭션 시작, 서비스 종료시 트랜잭션 종료(정합성)
//	public User 로그인(User user) {
//		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//	}
}
