<%@page import="mywebsite.beans.MemberDto"%>
<%@page import="mywebsite.beans.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String memberId= (String)session.getAttribute("loginId");
	MemberDao memberDao = new MemberDao();
	MemberDto memberDto = memberDao.lookUp(memberId);
%>
<jsp:include page="/template/top.jsp"></jsp:include>
<h2>내 정보 수정 페이지</h2>
<form action="edit.mws" method="post">
	<table>
		<tbody align="center">
			<tr>
				<td>현재 비밀번호 : </td>
				<td><input type="password" name="memberPw" required></td>
			</tr>
			<tr>
				<td>닉네임 : </td>
				<td><input type="text" name="memberNick" value="<%=memberDto.getMemberNick()%>"></td>
			</tr>
			<tr>
				<td>이메일 : </td>
				<td><input type="email" name="memberEmail" value="<%=memberDto.getMemberEmailString()%>"></td>
			</tr>
			<tr>
				<td>핸드폰 : </td>
				<td><input type="tel" name="memberPhone" value="<%=memberDto.getMemberPhoneString()%>"></td>
			</tr>
			<tr>
				<td colspan="2" align="right">
					<input type="submit" value="변경">
				</td>
			</tr>
		</tbody>
		
	</table>
</form>
<%if(request.getParameter("error") != null){%>
<h4><font color="red">정보가 일치하지 않습니다!</font></h4>
<%} %>
<jsp:include page="/template/bot.jsp"></jsp:include>