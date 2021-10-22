<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/template/top.jsp"></jsp:include>

<h2>회원 가입이 완료되었습니다.</h2>
<h4><a href="<%=request.getContextPath()%>/">메인페이지로 이동</a></h4>
<h4><a href="<%=request.getContextPath()%>/member/login.jsp">로그인페이지로 이동</a></h4>
<jsp:include page="/template/bot.jsp"></jsp:include>