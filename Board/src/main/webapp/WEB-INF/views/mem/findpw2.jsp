<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<title>findpw</title>
<style>
	#send{display:none}
</style>
</head>
<body>
<h3>비밀번호 찾기</h3>
<h4>아이디, 이름, 이메일 주소를 입력하시면 해당 메일로 새로운 비밀번호를 전송합니다.</h4>
<form:form action="/findpwresult" method="get" modelAttribute="command">
<form:errors element="div" /> <%--글로벌 에러 출력 --%>
	<table>
		<tr>
			<td>아이디</td><td><input name="id"></td>
		</tr>
		<tr>
			<td>이름</td><td><input name="name"></td>
		</tr>
		<tr>
			<td>이메일</td><td><input name="email"></td>
		</tr>
		<tr><td colspan="2"><input type="submit" value="비번찾기"></td>
	</table>
</form:form>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	
</body>
</html>