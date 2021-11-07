<%@page import="mywebsite.beans.CancelDto"%>
<%@page import="mywebsite.beans.CancelDao"%>
<%@page import="mywebsite.beans.HistoryDto"%>
<%@page import="java.util.List"%>
<%@page import="mywebsite.beans.HistoryDao"%>
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
	
	//히스토리(포인트 충전 내역)를 조회하는 코드
	HistoryDao historyDao = new HistoryDao();
	List<HistoryDto> historyList = historyDao.historyList(memberId);
	
	//취소내역 테이블에 내용이 있는지 확인
	CancelDao cancelDao = new CancelDao();
	
	CancelDto cancelDto;
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
<br><br>
<table width="70%" border="1">
	<thead>
		<tr>
			<th width="10%">번호</th>
			<th width="25%">날짜</th>
			<th width="40%">충전내용</th>
			<th width="15%">충전금액</th>
			<th width="20%">비고</th>
		</tr>
	</thead>
	<tbody align="center">
		<%for(HistoryDto historyDto : historyList){ %>
		<tr>
			<td><%=historyDto.getHistoryNo()%></td>
			<td><%=historyDto.getHistoryTime()%></td>
			<td><%=historyDto.getHistoryMemo()%></td>
			<td><%=historyDto.getHistoryAmount()%></td>
			<%//만약 취소내역이 없으면 철회버튼 활성화
				cancelDto = cancelDao.cancelSearch(historyDto.getHistoryNo());
				if(cancelDto == null){
					if(historyDto.getHistoryMemo().equals("포인트 구입")){%>
					<td><a href="../point/cancel.mws?historyNo=<%=historyDto.getHistoryNo()%>">철회</a></td>					
				<%}
					} else {
					int cancelNo = cancelDto.getHistoryNo();
					int historyNo = historyDto.getHistoryNo();
// 					String cancelNo = String.valueOf(cancelDto.getHistoryNo());
// 					String historyNo = String.valueOf(historyDto.getHistoryNo());
					//철회 한 내역이 있으면?
					boolean isCancel = !(cancelNo == historyNo);
					
					if(isCancel){ 
					} else {%>
					<td><a href="#">ㅎㅎ</a></td>
					<%}
				}
			}%>		
		</tr>
	</tbody>
</table>
<h4><a href="../point/charge.jsp">포인트 충전하기</a></h4>
<jsp:include page="/template/bot.jsp"></jsp:include>