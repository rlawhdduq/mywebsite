<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/template/top.jsp"></jsp:include>
<h2>회원가입 페이지</h2>
<form action="join.mws" method="post">
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
				<td>닉네임 : </td>
				<td><input type="text" name="memberNick" required></td>
			</tr>
			<tr>
				<td>이메일 : </td>
				<td><input type="email" name="memberEmail"></td>
			</tr>
			<tr>
				<td>전화번호 : </td>
				<td><input type="tel" name="memberPhone"></td>
			</tr>
			<tr>
				<td>생년월일 : </td>
				<td><input type="date" name="memberBirth" required></td>
			</tr>
			<tr>
				<td colspan="2" align="right">
					<input type="submit" value="가입하기">
				</td>
			</tr>
		</tbody>
		
	</table>
</form>
<jsp:include page="/template/bot.jsp"></jsp:include>