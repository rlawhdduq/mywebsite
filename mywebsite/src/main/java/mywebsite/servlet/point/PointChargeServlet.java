package mywebsite.servlet.point;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mywebsite.beans.MemberDao;
import mywebsite.beans.PointDao;

@WebServlet(urlPatterns = "/point/charge.mws")
public class PointChargeServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			//입력
			int pointNo = Integer.parseInt(req.getParameter("pointNo"));
			String memberId = (String)req.getSession().getAttribute("loginId");
			
			//처리
			PointDao pointDao = new PointDao();
			int pointAmount = pointDao.pointAmount(pointNo);
			MemberDao memberDao = new MemberDao();
			boolean isCharge = memberDao.pointRefresh(memberId, pointAmount);
			
			//출력
			if(isCharge) {
				resp.sendRedirect("../member/mypage.jsp");
			}else {
				resp.sendError(500);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			resp.sendError(500);
		}
	}
}
