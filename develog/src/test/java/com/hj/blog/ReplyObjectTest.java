package com.hj.blog;

import org.junit.jupiter.api.Test;

import com.hj.blog.model.Reply;


public class ReplyObjectTest {
	
	@Test
	public void 투스트링테스트() {
		Reply reply = Reply.builder()
				.id(1)
				.user(null)
				.board(null)
				.content("hello")
				.build();
		
		System.out.println(reply); // 오브젝트 출력시에 toString이 자동 호출됨
	}
}
