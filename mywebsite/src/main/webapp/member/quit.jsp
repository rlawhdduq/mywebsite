<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	String memberId = (String)session.getAttribute("loginId");
    %>
<jsp:include page="/template/top.jsp"></jsp:include>
<h2>정말로 탈퇴하실 건가요?</h2>
<form action="quit.mws" method="post">
	비밀번호 : <input type="password" name="memberPw">
	<input type="submit" value="탈퇴하기">
	<input type="hidden" name="memberId" value=<%=memberId %>>
</form>
<%if(request.getParameter("error") != null){%>
<h4><font color="red">정보가 일치하지 않습니다!</font></h4>
<%} %>
<jsp:include page="/template/bot.jsp"></jsp:include>