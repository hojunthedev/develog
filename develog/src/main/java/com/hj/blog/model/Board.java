package com.hj.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob // 대용량 데이터
	private String content; //섬머노트 라이브러리 <html> 태그가 섞여서 디자인됨.
	
	private int count; // 조회수
	
	@ManyToOne(fetch = FetchType.EAGER) //Many = Board, User = One,  한명의 유저는 여러개의 게시글을 쓸 수 있다.
	@JoinColumn(name="userId")
	private User user; // DB는 오브젝트를 저장할수 없다, FK, 자바는 오브젝트를 저장할 수 있다.
	
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE) // mappedBy 연관관계의 주인이 아니다.(난 FK가 아니에요) DB에 칼럼을 만들지 마세요 조인의 답만 필요해요
	// "board" 는 필드이름. Reply테이블의 필드이름을 적으면 됨
	//@JoinColumn 데이터베이스 1정규형 규칙(원자성)을 깨버림. 하면안됨.
	@JsonIgnoreProperties({"board"}) // 무한참조방지방법중 하나. Jackson이 파싱할때, getter를 호출하는데, 게터를 호출하지 않게 함
	@OrderBy("id desc")
	private List<Reply> replys;
	
	@CreationTimestamp
	private Timestamp createDate;
}
