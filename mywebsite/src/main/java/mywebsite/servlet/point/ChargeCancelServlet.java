package mywebsite.servlet.point;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mywebsite.beans.CancelDao;
import mywebsite.beans.HistoryDao;
import mywebsite.beans.HistoryDto;
import mywebsite.beans.MemberDao;

@WebServlet(urlPatterns = "/point/cancel.mws")
public class ChargeCancelServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			//입력
			int historyNo = Integer.parseInt(req.getParameter("historyNo"));
			String memberId = (String)req.getSession().getAttribute("loginId");
			
			//처리 : 취소 등록
			CancelDao cancelDao = new CancelDao();
			cancelDao.pointCancel(historyNo, memberId);
			
			HistoryDao historyDao = new HistoryDao();
			//취소된 내역을 히스토리에 등록 =>여기서는 No를 저장해줘야 구분이 되니까 저장한다.
			HistoryDto historyDto = historyDao.historyOneSearch(historyNo);
			historyDto.setHistoryNo(historyNo);
			historyDto.setMemberId(memberId);
			historyDto.setHistoryMemo("포인트 구매 취소");

			MemberDao memberDao = new MemberDao();
			//취소 등록 후 음수값으로 다시 업데이트한다.
			boolean isCancel = memberDao.pointRefresh(memberId, -historyDto.getHistoryAmount());
			
			//업데이트 후 히스토리에 등록한다.
			historyDao.historyInsert(historyDto);
			
			//출력
			if(isCancel) {
				resp.sendRedirect("../member/mypage.jsp");
			} else {
				resp.sendError(500);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			resp.sendError(500);
		}
	}
}
