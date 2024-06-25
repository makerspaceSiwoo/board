<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<title>findpw</title>
<style>

	
</style>
</head>
<body>
<h3>비밀번호 찾기</h3>
<h4>아이디, 이름, 이메일 주소를 입력하시면 해당 메일로 새로운 비밀번호를 전송합니다.</h4>


<table border="1" cellpadding="0" cellspacing="0">	
<form:form action="#" method="post">
<form:errors element="div"/>			
	<tr>
		<td bgcolor="orange" >아이디</td>
		<td><input name="id" type="text" size="10" id="id">
		<form:errors path="id"></form:errors> </td>
	</tr>
	<tr>
		<td bgcolor="orange" >이름</td>
		<td><input name="name" type="text" size="10" id="name">
		<form:errors path="name"></form:errors> </td>
	</tr>
</form:form>
	<tr>
		<td bgcolor="orange">email</td><td><input name="email" id="email">
      	<input type="button" id="mail_ck" value="메일 전송">
      	</td>
    </tr>

</table>
	

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>

$(function(){
	let pw ="";// 새 비밀번호
	$("#mail_ck").click(function(){
		let id=$("#id").val();
		let name=$("#name").val();
		if(!id || !name){
			alert("id와 이름을 입력하세요.");
			return false;
		}
		
		
	   let email = $("#email").val();
	  if(!email){
	        alert("메일 주소를 입력하세요");
	        return false;
	     } 
	$.ajax({url:"/sendmail",
	      data:{"emailAddress":email, "id" : id, "name":name},
	     dataType:"json"}
	  ).done(function(data){
		  console.log(data);
	     // [인증번호, true/false]
	     if(eval(data[1])){// 문자열로 옴 -> boolean값 - 메일이 잘 갔는지
	        pw = data[0];
	     console.log(pw);
	        alert("메일이 전송되었습니다. 새로운 비번으로 로그인 하십시오");
	        document.location.href = "/main";
	     }else{
	    	 alert("해당하는 유저 정보를 불러올 수 없습니다: 아이디나 이름 틀림");
	     }
	  }); 
	});

})//ready
</script>	
</body>