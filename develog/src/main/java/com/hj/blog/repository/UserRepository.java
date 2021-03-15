package com.hj.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hj.blog.model.User;

// Data Access Object [DAO]
// 자동으로 bean 등록되기때문에(스프링IoC 에서 객체를 가짐) @Repository 생략가능
public interface UserRepository extends JpaRepository<User, Integer>{ //유저 테이블을 관리하며, 프라이머리키는 인트

}


// JPA Naming 쿼리
// Select * from user where username = ?1 AND password = ?2
// JPA 너무 신기한것
//0315 폐지
//User findByUsernameAndPassword(String username, String password);

// 같은 방법
//	@Query(value="Select * from user where username = ?1 AND password = ?2", nativeQuery = true)
//	User login(String username, String password);