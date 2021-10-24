package mywebsite.sevlet.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mywebsite.beans.MemberDao;

@WebServlet(urlPatterns = "/member/quit.mws")
public class MemberQuitServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			//입력 : 비밀번호, 아이디(히든 타입으로 세션값을 전송)
			String memberPw = req.getParameter("memberPw");
			String memberId = req.getParameter("memberId");
			
			
			//처리 : memberDao.memberQuit 메소드 / 세션에서 아이디와 닉네임을 삭제
			req.getSession().removeAttribute("loginId");
			req.getSession().removeAttribute("loginNick");
			
			MemberDao memberDao = new MemberDao();
			memberDao.memberQuit(memberPw, memberId);
			
			//출력 : redirect
			resp.sendRedirect(req.getContextPath()+"/index.jsp");
		
		}catch(Exception e) {
			e.printStackTrace();
			resp.sendError(500);
		}
	}
}
