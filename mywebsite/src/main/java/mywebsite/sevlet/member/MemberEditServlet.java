package mywebsite.sevlet.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mywebsite.beans.MemberDao;
import mywebsite.beans.MemberDto;

@WebServlet(urlPatterns = "/member/edit.mws")
public class MemberEditServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			//입력 : MemberDto(변경 할 Nick, Email, Phone / 확인용 id(session), pw)
			MemberDto memberDto = new MemberDto();
			memberDto.setMemberId((String)req.getSession().getAttribute("loginId"));
			memberDto.setMemberPw(req.getParameter("memberPw"));
			memberDto.setMemberNick(req.getParameter("memberNick"));
			memberDto.setMemberEmail(req.getParameter("memberEmail"));
			memberDto.setMemberPhone(req.getParameter("memberPhoen"));
			
			//처리 memberDao.memberEdit 메소드
			MemberDao memberDao = new MemberDao();
			boolean isSuccess = memberDao.memberEdit(memberDto);
			
			if(isSuccess) {
				resp.sendRedirect("edit_success.jsp");
			} else {
				resp.sendRedirect("edit.jsp?error");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			resp.sendError(500);
		}
	}
}
