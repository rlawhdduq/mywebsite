<%@page import="mywebsite.beans.MemberDto"%>
<%@page import="mywebsite.beans.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	//입력 : 아이디(세션으로 처리)
	String memberId = (String)session.getAttribute("loginId");
	
	//처리 : memberDao.lookUp 메소드
	MemberDao memberDao = new MemberDao();
	MemberDto memberDto = memberDao.lookUp(memberId);
	
%>    
<jsp:include page="/template/top.jsp"></jsp:include>
<h2>내 정보 페이지</h2>
<table>
	<tbody>
		<tr>
			<td>아이디 </td>
			<td><%=memberDto.getMemberId()%> </td>
		</tr>
		<tr>
			<td>닉네임 </td>
			<td><%=memberDto.getMemberNick()%> </td>
		</tr>
		<tr>
			<td>이메일 </td>
			<td><%=memberDto.getMemberEmailString()%> </td>
		</tr>
		<tr>
			<td>핸드폰 </td>
			<td><%=memberDto.getMemberPhoneString()%> </td>
		</tr>
		<tr>
			<td>생년월일 </td>
			<td><%=memberDto.getMemberBirth()%> </td>
		</tr>
		<tr>
			<td>가입일 </td>
			<td><%=memberDto.getMemberJoin()%> </td>
		</tr>
		<tr>
			<td>등급 </td>
			<td><%=memberDto.getMemberGrade()%> </td>
		</tr>
	</tbody>
</table>
<jsp:include page="/template/bot.jsp"></jsp:include>