package mywebsite.servlet.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mywebsite.beans.BoardDao;

@WebServlet(urlPatterns = "/board/delete.mws")
public class BoardDeleteServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			//입력 : 파라미터(board_no/PK)
			int boardNo = Integer.parseInt(req.getParameter("boardNo"));
			
			//처리 : boardDao.boardDelete()
			BoardDao boardDao = new BoardDao();
			boolean isDelete = boardDao.boardDelete(boardNo);
			
			//출력 : 리스트로 보낸다
			if(isDelete) {
				resp.sendRedirect("./list.jsp");
			} else {
				resp.sendError(500);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			resp.sendError(500);
		}
	}
}
