let index ={
		//let _this = this; //함수 사용시
		init: function(){
			$("#btn-save").on("click", () => { //this를 바인딩하기 위해 람다식을 사용한다 
				this.save(); //함수로 사용하면 이곳에서의 this는 윈도우함수를 가르키기 때문 
			});
			$("#btn-login").on("click", () => { 
				this.login();
			});
			
		},

		save: function(){
			//alert('user의 save함수 호출됨');
			let data = {
					username: $("#username").val(),
					password: $("#password").val(),
					email: $("#email").val()
			};
			
			//console.log(data);
			
			// ajax 호출시 default가 비동기 호출
			$.ajax({
				// 회원가입 수행 요청
				type: "POST",
				url: "/develog/api/user",
				data: JSON.stringify(data), // http body데이터
				contentType: "application/json; charset=utf-8", //body데이터가 어떤 타입인지(Mime)
				dataType: "json" // 요청을 서버로해서, 응답이 왔을때 기본적으로 모든것이 문자열 (모양이 json이라면) => javascript, 생략도 가능. ajax의 기본전략
			}).done(function(resp){
				// 성공시
				alert("회원가입이 완료되었습니다.");
				console.log(resp);
				location.href = "/develog";
			}).fail(function(err){
				// 실패
				alert(JSON.stringify(err));
			});
		},
		
		login: function(){
			//alert('user의 save함수 호출됨');
			let data = {
					username: $("#username").val(),
					password: $("#password").val(),
					email: $("#email").val()
			};
			
			$.ajax({
				type: "POST",
				url: "/develog/api/user/login",
				data: JSON.stringify(data), // http body데이터
				contentType: "application/json; charset=utf-8", //body데이터가 어떤 타입인지(Mime)
				dataType: "json" // 요청을 서버로해서, 응답이 왔을때 기본적으로 모든것이 문자열 (모양이 json이라면) => javascript, 생략도 가능. ajax의 기본전략
			}).done(function(resp){
				// 성공시
				alert("로그인이 완료되었습니다.");
				console.log(resp);
				location.href = "/develog";
			}).fail(function(err){
				// 실패
				alert(JSON.stringify(err));
			});
		}
}

index.init();