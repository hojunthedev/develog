package com.hj.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hj.blog.model.User;

// Data Access Object [DAO]
// 자동으로 bean 등록되기때문에(스프링IoC 에서 객체를 가짐) @Repository 생략가능
public interface UserRepository extends JpaRepository<User, Integer>{ //유저 테이블을 관리하며, 프라이머리키는 인트
	
	
}
