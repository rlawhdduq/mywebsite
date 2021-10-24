package mywebsite.sevlet.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/member/logout.mws")
public class MemberLogoutServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			//입력 : 없음
			
			//처리 : 세션에 담긴 아이디와 닉네임
			req.getSession().removeAttribute("loginId");
			req.getSession().removeAttribute("loginNick");
			
			//출력 : redirect
			resp.sendRedirect("../index.jsp");
			
		}catch(Exception e) {
			e.printStackTrace();
			resp.sendError(500);
		}
		
	}
}
