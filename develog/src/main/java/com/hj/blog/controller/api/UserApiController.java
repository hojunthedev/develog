package com.hj.blog.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hj.blog.dto.ResponseDto;
import com.hj.blog.model.User;

@RestController
public class UserApiController {
	
	@PostMapping("/api/user")
	public ResponseDto<Integer> save(@RequestBody User user) {
		System.out.println("UserApiController : save 호출됨");
		//TODO: 실제로 DB에 INSERT하고  아래에서 return
		return new ResponseDto<Integer>(HttpStatus.OK, 1);
	}
}
