<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	//로그인을 한 상태인지 아닌지 판정
	String memberId = (String)session.getAttribute("loginId");
	String memberNick = (String)session.getAttribute("loginNick");
	boolean isLogin = memberId != null;
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>어디갈까?</title>
</head>
<body>
	<table border="1" width="1000">
		<tbody align="center">
			<tr>
				<td>흐흫 어디갈랭?</td>
			</tr>
				<%if(!isLogin) {%>
			<tr align="right">
				<td><a href="<%=request.getContextPath()%>/member/login.jsp">로그인</a></td>
				<%} else{%>
				<td><%=memberNick%>님 환영합니다~</td>
				<%} %>
			</tr>
			<tr>
				<td>
		