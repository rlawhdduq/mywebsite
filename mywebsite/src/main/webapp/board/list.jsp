<%@page import="mywebsite.beans.Pagination"%>
<%@page import="mywebsite.beans.BoardDto"%>
<%@page import="java.util.List"%>
<%@page import="mywebsite.beans.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	//파라미터를 통해 항목과 검색어를 입력받는다. (최초엔 둘다 null이므로 리스트페이지 출력)
	String column = request.getParameter("column");
	String keyword = request.getParameter("keyword");
	

	//Pagination 모듈을 이용하여 계산을 처리하도록 위임
	Pagination pagination = new Pagination(request);
	pagination.calculate();

%>
    
<jsp:include page="/template/top.jsp"></jsp:include>
<h2>게시판</h2>
<h3><a href="./insert.jsp">일단 글쓰러가자</a></h3>
<table width="90%">
	<thead>
		<tr>
			<th>글 번호</th>
			<th width="40%">제목</th>
			<th>작성자</th>
			<th width="8%">조회수</th>
			<th width="15%">좋아요/싫어요</th>
			<th>상위글번호</th>
			<th>그룹번호</th>
			<th width="5%">차수</th>
		</tr>
	</thead>
	<tbody align="center">
			<%for(BoardDto boardDto : pagination.getList()) {%>
				<tr>
					<td>
						<%=boardDto.getBoardNo()%>
					</td>
					<td align="left">
						<%for(int i = 0; i < boardDto.getBoardDepth(); i++){ %>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<%} %>
						<a href="./detail.jsp?boardNo=<%=boardDto.getBoardNo()%>">
							(<%=boardDto.getBoardAddr()%>)<%=boardDto.getBoardTitle()%>
							<%if(boardDto.getBoardReply() > 0) {%>
							[<%=boardDto.getBoardReply()%>]
							<%} %>
						</a>
					</td>
					<td>
						<%=boardDto.getMemberNick()%>
					</td>
					<td>
						<%=boardDto.getBoardHit()%>
					</td>
					<td>
						<%=boardDto.getBoardLike()%>/<%=boardDto.getBoardUnlike()%>
					</td>
					<td>
						<%=boardDto.getBoardSuperno()%>
					</td>
					<td>
						<%=boardDto.getBoardGroupno()%>
					</td>
					<td>
						<%=boardDto.getBoardDepth()%>
					</td>
				</tr>
			<%} %>
	</tbody>
</table>

<!-- 페이지 네비게이터 -->
<br><br>
<%if(pagination.isPreviousAvailable()) {
	if(pagination.isMode()){ %>
	<!-- 검색용 이전 -->
	<a href="list.jsp?column=<%=column%>&keyword=<%=keyword%>&p=<%=pagination.getPreviousPageNumber()%>">[이전]</a>
	<%} else { %>
	<!-- 목록용 이전 -->
	<a href="list.jsp?column=?&p=<%=pagination.getPreviousPageNumber()%>">[이전]</a>
	<%} %>	
<%} else{%>
	<a>[이전]</a>
<%} %>

<%for(int i = pagination.getStartBlock(); i <= pagination.getRealLastBlock(); i++){ %>
	<%if(pagination.isMode()){ %>
	<!-- 검색용 링크 -->
	<a href="list.jsp?column=<%=pagination.getColumn()%>&keyword=<%=pagination.getKeyword()%>&p=<%=i%>"><%=i%></a>
	<%} else { %>
	<!-- 목록용 링크 -->
	<a href="list.jsp?column=&p=<%=i%>"><%=i%></a>
	<%} %>
<%} %>

<%if(pagination.isNextAvailable()){ %>
	<%if(pagination.isMode()){ %>
		<!-- 검색용 다음 -->
		<a href="list.jsp?column=<%=column%>&keyword=<%=keyword%>&p=<%=pagination.getNextPageNumber()%>">[다음]</a>
	<%} else { %>
		<!-- 목록용 다음 -->
		<a href="list.jsp?column=?&p=<%=pagination.getNextPageNumber()%>">[다음]</a>
	<%} %>
<%} else{%>
<a>[다음]</a>
<%} %>
<br><br>


<form action="list.jsp" method="get">
	<select name="column">
		<option value="">전체</option>
		<option value="member_nick">작성자</option>
		<option value="board_title">제목</option>
	</select>
	<input type="search" name="keyword"> 
	<input type="submit" value="검색">
</form>
<jsp:include page="/template/bot.jsp"></jsp:include>    