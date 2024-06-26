<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<title>글쓰기</title>
<style>
	.orange{background-color: orange;}
	table{border-collapse : collapse;}
</style>
</head>
<body>
<form:form method="post" id="writeform" modelAttribute="board">
<form:errors element="div"/>
	<table border="1">
		<tr>
			<td class="orange">제목</td>
			<td><input name="title"/></td>
		</tr>
		<tr>
			<td class="orange">작성자</td>
			<td><input name="id" value="${user.id}" readonly></td>
		</tr>
		<tr>
			<td class="orange">내용</td>
			<td><textarea name="content" cols="40" rows="10"></textarea></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="submit" value="새글 등록"> 
			</td>
		</tr>
	</table>

</form:form>

</body>
</html>