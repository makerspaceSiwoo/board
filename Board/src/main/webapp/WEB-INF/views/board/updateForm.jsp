<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
<form method="post" id="updateform" action="/board/update" >
<input type="hidden" name="_method" value="put">
	<table border="1">
		<tr>	
			<td class="orange">제목</td>
			<td><input name="title" value="${dto.title }"/>
				<input name="no" value="${dto.no}" type="hidden"> 
			</td>
		</tr>
		<tr>
			<td class="orange">작성자</td>
			<td><input name="id" value="${dto.id}" readonly></td>
		</tr>
		<tr>
			<td class="orange">내용</td>
			<td><textarea name="content" cols="40" rows="10">${dto.content}</textarea></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="submit" value="글 수정 완료"> 
			</td>
		</tr>
	</table>

</form>
</body>
</html>