<%@page import="mywebsite.beans.BoardDto"%>
<%@page import="java.util.List"%>
<%@page import="mywebsite.beans.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	//파라미터를 통해 항목과 검색어를 입력받는다. (최초엔 둘다 null이므로 리스트페이지 출력)
	String column = request.getParameter("column");
	String keyword = request.getParameter("keyword");
	
	//파라미터를 통해 넘어온 값이 있는지 없는지 확인 / 둘 다 존재하면 검색처리
	boolean isSearch = column != null && keyword != null
								&& !column.equals("") && !keyword.equals("");
	//게시판 목록을 	불러오는 코드
	BoardDao boardDao = new BoardDao();
	List<BoardDto> boardList;
	String title = "";
	if(isSearch){
		boardList = boardDao.boardSearch(column, keyword);
		title = "검색";
	} else {
		boardList = boardDao.boardList();
		title = "목록";
	}
%>
    
<jsp:include page="/template/top.jsp"></jsp:include>
<h2>게시판 <%=title%> 페이지</h2>
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
			<%for(BoardDto boardDto : boardList) {%>
				<tr>
					<td>
						<%=boardDto.getBoardNo()%>
					</td>
					<td align="left">
						<a href="./detail.jsp?boardNo=<%=boardDto.getBoardNo()%>">(<%=boardDto.getBoardAddr()%>)<%=boardDto.getBoardTitle()%></a>
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
<br><br>
[이전] 1 2 3 4 5 6 7 8 9 10 [다음]
<br>
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