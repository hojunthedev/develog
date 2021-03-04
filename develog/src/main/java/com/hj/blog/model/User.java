package com.hj.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // 빌더 패턴!
@Entity	//User 클래스가 MySql에 테이블이 생성된다.
//@DynamicInsert // insert시 null인 필드 제외
public class User {
	
    @Id //Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private int id; //시퀀스, auto_increment
	
    @Column(nullable = false, length = 30, unique = true)
	private String username; //아이디
	
    @Column(nullable = false, length = 100)
	private String password; //비밀번호
    
    @Column(nullable = false, length = 50)
	private String email; //이메일
	
    //@ColumnDefault("'user'") //싱글쿼테이션을 더블쿼테이션으로 감싸야함
    @Enumerated(EnumType.STRING)
    private RoleType role; //Enum을 쓰는게 좋다. admin, user
    
    @CreationTimestamp //시간 자동 입력
	private Timestamp createDate; //가입일
}
