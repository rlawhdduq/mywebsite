<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/template/top.jsp"></jsp:include>
<h2>로그인 페이지</h2>
<form action="login.mws" method="post">
	<table>
		<tbody align="center">
			<tr>
				<td>아이디 : </td>
				<td><input type="text" name="memberId" required></td>
			</tr>
			<tr>
				<td>비밀번호 : </td>
				<td><input type="password" name="memberPw" required></td>
			</tr>
			<tr>
				<td colspan="2" align="right">
					<input type="submit" value="로그인">
				</td>
			</tr>
		</tbody>
		
	</table>
</form>
<%if(request.getParameter("error") != null){%>
<h4><font color="red">정보가 일치하지 않습니다!</font></h4>
<%} %>
<jsp:include page="/template/bot.jsp"></jsp:include>