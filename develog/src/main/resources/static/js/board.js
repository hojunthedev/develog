let index ={
		init: function(){
			$("#btn-save").on("click", () => { 
				this.save();
			}),
			$("#btn-delete").on("click", () => { 
				this.deleteById();
			}),
			$("#btn-update").on("click", () => { 
				this.update();
			});
		},

		save: function(){
			let data = {
					title: $("#title").val(),
					content: $("#content").val(),
			};
			
			$.ajax({
				type: "POST",
				url: "/api/board",
				data: JSON.stringify(data),
				contentType: "application/json; charset=utf-8",
				dataType: "json" 
			}).done(function(resp){
				alert("글쓰기가 완료되었습니다.");
				location.href = "/";
			}).fail(function(err){
				alert(JSON.stringify(err));
			});
		},
		
		deleteById: function(){
			let id = $("#id").text();
			
			$.ajax({
				type: "DELETE",
				url: "/api/board/"+id,
				dataType: "json" 
			}).done(function(resp){
				alert("삭제가 완료되었습니다.");
				location.href = "/";
			}).fail(function(err){
				alert(JSON.stringify(err));
			});
		},
		
		update: function(){
			let id=$("#id").val();
			
			let data = {
					title: $("#title").val(),
					content: $("#content").val(),
			};
			
			$.ajax({
				type: "PUT",
				url: "/api/board/"+id,
				data: JSON.stringify(data),
				contentType: "application/json; charset=utf-8",
				dataType: "json" 
			}).done(function(resp){
				alert("글수정 완료되었습니다.");
				location.href = "/";
			}).fail(function(err){
				alert(JSON.stringify(err));
			});
		},
}

index.init();