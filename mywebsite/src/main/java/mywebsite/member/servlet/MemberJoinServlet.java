package mywebsite.member.servlet;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mywebsite.beans.MemberDao;
import mywebsite.beans.MemberDto;

@WebServlet(urlPatterns = "/member/join.mws")
public class MemberJoinServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			//입력 : MemberDto(id, pw, nick, email, phone, birth)
			MemberDto memberDto = new MemberDto();
			memberDto.setMemberId(req.getParameter("memberId"));
			memberDto.setMemberNick(req.getParameter("memberNick"));
			memberDto.setMemberPw(req.getParameter("memberPw"));
			memberDto.setMemberEmail(req.getParameter("memberEmail"));
			memberDto.setMemberPhone(req.getParameter("memberPhone"));
			memberDto.setMemberBirth(Date.valueOf(req.getParameter("memberBirth")));
			
			//처리 : MemberDao.memberJoin
			MemberDao memberDao = new MemberDao();
			memberDao.memberJoin(memberDto);
			
			//출력 : join_success로 Redirect
			resp.sendRedirect("join_success.jsp");
		}catch(Exception e) {
			e.printStackTrace();
			resp.sendError(500);
		}
	}
}
