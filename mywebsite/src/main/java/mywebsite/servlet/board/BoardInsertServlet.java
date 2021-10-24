package mywebsite.servlet.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mywebsite.beans.BoardDao;
import mywebsite.beans.BoardDto;

@WebServlet(urlPatterns = "/board/insert.mws")
public class BoardInsertServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			//입력 : boardDto(id, nick - session / title, content)
			BoardDto board = new BoardDto();
			board.setMemberId((String)req.getSession().getAttribute("loginId"));
			board.setMemberNick((String)req.getSession().getAttribute("loginNick"));
			board.setBoardTitle(req.getParameter("boardTitle"));
			board.setBoardContent(req.getParameter("boardContent"));
			
			//처리 : boardDao.boardInsert메소드
			BoardDao boardDao = new BoardDao();
			boardDao.boardInsert(board);
			
			resp.sendRedirect("./list.jsp");
			
		}catch(Exception e) {
			e.printStackTrace();
			resp.sendError(500);
		}
		
	}
}
