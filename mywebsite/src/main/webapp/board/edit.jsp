<%@page import="mywebsite.beans.BoardDto"%>
<%@page import="mywebsite.beans.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/template/top.jsp"></jsp:include>
<%
	int boardNo = Integer.parseInt(request.getParameter("boardNo"));
	//게시글 단일조회 코드 작성
	BoardDao boardDao = new BoardDao();
	BoardDto boardDto = boardDao.boardDetail(boardNo);
%>
<h2>게시글 수정 페이지</h2>
<form action="edit.mws" method="post">
<table width="50%">
	<tbody>
		<tr>
			<td>
				작성자 : <%=boardDto.getMemberNick()%>
			</td>
		</tr>
		<tr>
			<td>
				제목 : <input type="text" name="boardTitle" value="<%=boardDto.getBoardTitle()%>"> / 조회수 : <%=boardDto.getBoardHit()%>
			</td>
		</tr>
		<tr>
			<td>
				좋아요 : <%=boardDto.getBoardLike()%> / 싫어요 : <%=boardDto.getBoardUnlike()%>
			</td>
		</tr>
		<tr>
			<td>
				내용 : <textarea name="boardContent" rows="20" cols="50"><%=boardDto.getBoardContent()%></textarea>
			</td>
		</tr>
	</tbody>
</table>
<input type="hidden" value="<%=boardDto.getBoardNo()%>" name="boardNo">
<input type="submit" value="수정">
</form>

<jsp:include page="/template/bot.jsp"></jsp:include>  