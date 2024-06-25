<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<title>로그인</title>
</head>
<body>
<h5>id와 password를 입력하세요</h5>
<form:form action="login" method="post" modelAttribute="command">
<form:errors element="div"/>
<table border="1" cellpadding="0" cellspacing="0">				
	<tr>
		<td bgcolor="orange" >아이디</td>
		<td><input name="id" type="text" size="10">
		<form:errors path="id"></form:errors> </td>
	</tr>
	<tr>
		<td bgcolor="orange" >비밀번호</td>
		<td><input name="password" type="password" size="10">
		<form:errors path="password"/>
		</td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<input type="submit" value="로그인">
			<a href="insert">회원 가입</a>
		</td>  
	</tr>
</table>
</form:form>

<span id="findpw">
	<a href="/findpw">
		<button>비밀번호 찾기</button>
	</a>
</span>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
	
</script>	
</body>
</html>