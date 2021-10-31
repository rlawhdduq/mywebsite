package mywebsite.servlet.point;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mywebsite.beans.HistoryDao;
import mywebsite.beans.HistoryDto;
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
			
			//히스토리에 내역을 등록한다.
			HistoryDto historyDto = new HistoryDto();
			historyDto.setMemberId(memberId);
			historyDto.setHistoryMemo("포인트 구입");
			historyDto.setHistoryAmount(pointAmount);
			
			HistoryDao historyDao = new HistoryDao();
			historyDao.historyInsert(historyDto);
			
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
