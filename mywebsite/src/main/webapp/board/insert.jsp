<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String boardNo = request.getParameter("superNo");
	String title;
	if(boardNo != null){
		title = "답글";
	}else{
		title = "새글";
	}
%>
    
    
<jsp:include page="/template/top.jsp"></jsp:include>
<h2><%=title%> 작성 페이지</h2>
<form action="insert.mws" method="post">
	<table>
		<tbody>
			<tr>
				<td>작성자 : </td>
				<td><%=session.getAttribute("loginId")%>(<%=session.getAttribute("loginNick") %>)</td>
			</tr>
			<tr>
				<td>제목 : </td>
				<td> <input type="text" name="boardTitle"></td>
			</tr>
			<%if(title.equals("새글")){ %>
				<tr>
					<td>도 : </td>
					<td> <input type="text" name="addrDo" placeholder="선택입력"></td>
					<td>시 : </td>
					<td> <input type="text" name="addrSi" required placeholder="필수입력"></td>
					<td>구 : </td>
					<td> <input type="text" name="addrGoo" required placeholder="필수입력"></td>
					<td>동 : </td>
					<td> <input type="text" name="addrDong" required placeholder="필수입력"></td>
				</tr>
			<%} else {}%>
			<tr>
				<td colspan="2"><textarea name="boardContent" rows="30" cols="50" required></textarea></td>
			</tr>
			<tr>
				<td align="right" colspan="2">
				<input type="hidden" value="<%=boardNo%>" name="superNo">
				<input type="submit" value="작성하기">
				</td>
			</tr>
		</tbody>
	</table>
</form>
<jsp:include page="/template/bot.jsp"></jsp:include>        