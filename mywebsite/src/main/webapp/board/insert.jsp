<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
    
    
<jsp:include page="/template/top.jsp"></jsp:include>
<h2>게시글 작성 페이지</h2>
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
			<tr>
				<td colspan="2"><textarea name="boardContent" rows="30" cols="50"></textarea></td>
			</tr>
			<tr>
				<td align="right" colspan="2"><input type="submit" value="작성하기"></td>
			</tr>
		</tbody>
	</table>
</form>
<jsp:include page="/template/bot.jsp"></jsp:include>        