<%@page import="mywebsite.beans.ReplyDto"%>
<%@page import="java.util.List"%>
<%@page import="mywebsite.beans.ReplyDao"%>
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
	
	//댓글 목록 조회 메소드
	ReplyDao replyDao = new ReplyDao();
	List<ReplyDto> replyList = replyDao.replyList(boardNo);
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
<h5><a href="./insert.jsp?boardNo=<%=boardDto.getBoardNo()%>&superNo=<%=boardDto.getBoardNo()%>">답글쓰기</a></h5>

<!-- 댓글 영역 -->
<!-- 댓글 작성 영역 -->
<form action="../reply/insert.mws" method="post">
	<table>
		<tbody>
			<tr>
				<td>작성자 : <%=session.getAttribute("loginId")%>
					<input type="hidden" value="<%=boardNo%>" name="boardNo">
				</td>
				<td><textarea rows="4" cols="40" name="replyContent"></textarea></td>
				<td><input type="number" name="rating" min="0" max="5">별점</td>
				<td><input type="submit" value="등록"></td>
			</tr>
		</tbody>
	</table>
</form>
<!-- 댓글 목록 영역 -->
<%if(replyList.isEmpty()) {%>
<h3>댓글이 없습니다.</h3>
<%} else{%>
	<table width="600" border="1">
		<thead>
			<tr>
				<th>작성자, 별점</th>
				<th>내용</th>
				<th>Up/Down</th>
				<th>상태</th>
			</tr>
		</thead>
		<tbody>
			<%for(ReplyDto reply : replyList){ %>
			<tr>
				<td>
				<%if(boardDto.getMemberId().equals(reply.getMemberId())){//게시글작성자와 댓글 작성자가 같으면 %>
				[작성자]<%=reply.getMemberId()%>
				<%} else {%>
					<%=reply.getMemberId()%>
				 <%} %>
				/ <%=reply.getRating()%></td>
				<td><%=reply.getReplyContent()%></td>
				<td><%=reply.getReplyLike()%>/<%=reply.getReplyUnLike()%></td>
				<td><a href="#">수정</a> / <a href="#">삭제</a> / <a href="#">좋아요</a> / <a href="#">싫어요</a></td>
			</tr>
			<%} %>
		</tbody>
	</table>
<%} %>

<jsp:include page="/template/bot.jsp"></jsp:include>  