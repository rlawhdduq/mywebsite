package mywebsite.member.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mywebsite.beans.MemberDao;
import mywebsite.beans.MemberDto;

@WebServlet(urlPatterns = "/member/login.mws")
public class MemberLoginServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			//입력 : 아이디와 비밀번호(파라미터)
			String memberId = req.getParameter("memberId");
			String memberPw = req.getParameter("memberPw");
			
			//처리 : memberDao.memberLogin메소드
			MemberDao memberDao = new MemberDao();
			MemberDto memberDto = memberDao.memberLogin(memberId, memberPw);
			if(memberDto == null) {
				resp.sendRedirect("login.jsp?error");
			}else {			
				req.getSession().setAttribute("loginId", memberDto.getMemberId());
				req.getSession().setAttribute("loginNick", memberDto.getMemberNick());
				resp.sendRedirect("../index.jsp");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			resp.sendError(500);
		}
	}
}
