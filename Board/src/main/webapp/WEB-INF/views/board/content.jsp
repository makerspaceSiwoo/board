<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<title>글 상세보기</title>
</head>
<body>
<table border="1">
	<tr><td>제목</td><td>${dto.title}</td>
	<tr><td>작성자</td><td>${dto.id}</td>
	<tr><td>내용</td><td>${dto.content}</td>
	<tr><td>등록일</td><td><fmt:formatDate value="${dto.regdate }" dateStyle="long"/></td>
	<tr><td>조회수</td><td>${dto.readcount}</td>
	<tr><td colspan="2" align="right">
	<c:if test="${ user.id == dto.id }"><!-- 세션에 user 로그인 한 사람과 글 쓴 사람이 같을 때만 -->
	<a href="/board/update/${dto.no}">글 수정 </a> 
	<a id="${dto.no}" href="#">글 삭제</a>
	</c:if>
	<a href="../list">목록 이동</a> <!-- board/content/no -> board/list -->
	</td></tr>
</table>

<!-- comment list -->
<div id="comm">
	<c:forEach items="${clist }" var="comm">
		<div>${comm.id} / <fmt:formatDate value="${comm.regdate }" dateStyle="short"/></div>
		<div>${comm.content} 
		<c:if test="${comm.id == user.id }">
			<button class="dbtn" id="${comm.cno}">삭제</button>
		</c:if>
		</div>
		<hr>
	</c:forEach>
	댓글<input name="content" id="content"><button id="add">등록</button>

</div>




<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
	$(function(){
		$("a[id]").click(function(){
			let no = $(this).attr("id");
			$.ajax({url:"/board/delete", data:"no="+no, method:"delete"}
			).done(function(){
				location.href="/board/list";
			})
			return false;// 하이퍼링크 이동 x return false로 막아둠
		})//click
		
		$("#add").click(function(){
			let id = '${user.id}';
			let content = $("#content").val();
			let no = '${dto.no}'; // 글번호
			if(id==""){
				alert("먼저 로그인 하세요");
				location.href="/main";
			}else if(content==""){
				alert("내용을 입력하세요");
				location.reload();	
			}else{
				console.log(id,content);
				$.ajax({url:"/comm/insert",
						data:"no="+no+"&id="+id+"&content="+content,
						method:"get"
				}).done(function(data){
						location.reload();		
					});
			}
			
			
		})//click
		$("#comm").on("click",".dbtn", function(){
			let cno = $(this).attr("id");
			$.ajax({url:"/comm/delete/"+cno,
				method:"get"
		}).done(function(){
				location.reload();		
			});
			
		})//click
		
		
		
		
	})//ready

</script>
</body>
</html>