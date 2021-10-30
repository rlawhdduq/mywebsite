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
	
	//현재 게시글의 작성자가 세션 아이디와 일치하는지 확인
	String memberId = (String)session.getAttribute("loginId");
	boolean isMine = boardDto.getMemberId().equals(memberId);
%>
<h2>게시글 상세보기 페이지</h2>
<table width="50%">
	<tbody>
		<tr>
			<td>
				작성자 : <%=boardDto.getMemberNick()%>
			</td>
		</tr>
		<tr>
			<td>
				제목 : <%=boardDto.getBoardTitle()%> / 조회수 : <%=boardDto.getBoardHit()%>
			</td>
		</tr>
		<tr>
			<td>
				좋아요 : <%=boardDto.getBoardLike()%> / 싫어요 : <%=boardDto.getBoardUnlike()%>
			</td>
		</tr>
		<tr>
			<td>
				내용 : <%=boardDto.getBoardContent()%>
			</td>
		</tr>
	</tbody>
</table>
<%if(isMine){ %>
<h5><a href="./edit.jsp?boardNo=<%=boardDto.getBoardNo()%>">수정</a> / 
	<a href="./delete.mws?boardNo=<%=boardDto.getBoardNo()%>">삭제</a>
</h5>
<%} %>
<jsp:include page="/template/bot.jsp"></jsp:include>  