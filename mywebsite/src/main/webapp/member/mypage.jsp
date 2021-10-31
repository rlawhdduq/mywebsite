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
<table border="1" width="250">
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
			<td>포인트 </td>
			<td><%=memberDto.getMemberPoint()%> point</td>
		</tr>
		<tr>
			<td>등급 </td>
			<td><%=memberDto.getMemberGrade()%> </td>
		</tr>
	</tbody>
</table>
<h4><a href="edit.jsp">내정보 수정하기</a></h4>
<h4><a href="quit.jsp">회원 탈퇴하기</a></h4>
<h4><a href="../point/charge.jsp">포인트 충전하기</a></h4>
<jsp:include page="/template/bot.jsp"></jsp:include>