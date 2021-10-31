<%@page import="java.util.List"%>
<%@page import="mywebsite.beans.PointDao"%>
<%@page import="mywebsite.beans.PointDto"%>
<%@page import="java.text.Format"%>
<%@page import="java.text.DecimalFormat"%>
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
	Format f = new DecimalFormat("#,##0");
	
	// 포인트 상품 목록을 가져오는 메소드
	PointDao pointDao = new PointDao();
	List<PointDto> pointList = pointDao.pointList();
%>    
<jsp:include page="/template/top.jsp"></jsp:include>
<h2>포인트 충전 페이지</h2>
남은 포인트 : <%=f.format(memberDto.getMemberPoint())%> point
<form action="charge.mws" method="post">
	<table width="50%" border="1">
		<thead>
			<tr>
				<th>번호</th>
				<th>상품명</th>
				<th>상품가격</th>
			</tr>
		</thead>
		<tbody>
			<%for(PointDto pointDto : pointList){ %>
			<tr>
				<td align="center"><input type="radio" required name="pointNo" value="<%=pointDto.getPointNo()%>"></td>
				<td align="center"><%=pointDto.getPointName()%></td>
				<td><%=pointDto.getDecimalFormatPointAmount()%>원</td>
			</tr>
			<%} %>
		</tbody>
	</table>
	<br><br>
	<input type="submit" value="충전하기">
</form>

<jsp:include page="/template/bot.jsp"></jsp:include>