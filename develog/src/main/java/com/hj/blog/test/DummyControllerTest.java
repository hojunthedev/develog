package com.hj.blog.test;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hj.blog.model.RoleType;
import com.hj.blog.model.User;
import com.hj.blog.repository.UserRepository;

@RestController // data를 리턴해주는 컨트롤러
public class DummyControllerTest {
	
	@Autowired //UserRepository 타입으로 스프링이 관리하고있으면(메모리에 떠있으면) 같이 메모리에 띄워줘라(의존성 주입, DI)
	private UserRepository userRepository;
	
	
	@GetMapping("/dummy/users")
	public List<User> list() {
		return userRepository.findAll();
	}
	
	// 한 페이지당 2건의 데이터를 리턴받아 볼 예정
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size=2,sort="id", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<User> pagingUser = userRepository.findAll(pageable);
		
		List<User> users = pagingUser.getContent();
		return users;
	}
	
	// {id} 주소로 파라메터를 전달받을 수 있음
	// http://localhost:8000/develog/dummy/user/3
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		// null 리턴 방지용 Optional로 User객체를 감싸서 가져올테니 null인지 판단해서 return하세요!
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당 유저는 없습니다. id : " + id);
			}
		});
		// 람다식
		// User user = userRepository.findById(id).orElseThrow(() -> {
		// 	return new IllegalArgumentException("해당 사용자가 없습니다.");
		// })
		// 요청 : 웹브라우저
		// user객체 = 자바오브젝트
		// 변환 (웹브라우저가 이해할 수 있는 데이터) -> json
		// 스프링부트 = MessageConverter라는 애가 응답시에 자동 작동
		// 만약 자바 오브젝트를 리턴하게 되면 MC가 Jackson 라이브러리를 호출해서
		// User 오브젝트를 json으로 변환해서 브라우저에게 던져줍니다.
		return user;
	}
	
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
