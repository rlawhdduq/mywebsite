package mywebsite.servlet.reply;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mywebsite.beans.BoardDao;
import mywebsite.beans.ReplyDao;
import mywebsite.beans.ReplyDto;

@WebServlet(urlPatterns = "/reply/insert.mws")
public class ReplyInsertServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			//입력
			//게시글 번호를 받아온다.
			int boardNo = Integer.parseInt(req.getParameter("boardNo"));
			String replyContent = req.getParameter("replyContent");
			int rating = Integer.parseInt(req.getParameter("rating"));
			
			//처리
			//객체를 만들어서 값을 저장하고 등록한다.
			ReplyDto replyDto = new ReplyDto();
			replyDto.setBoardNo(boardNo);
			replyDto.setMemberId((String)req.getSession().getAttribute("loginId"));
			replyDto.setRating(rating);
			replyDto.setReplyContent(replyContent);
			
			ReplyDao replyDao = new ReplyDao();
			replyDao.replyInsert(replyDto);
			//등록이 완료되면 해당 게시글의 댓글수를 갱신한다.
			BoardDao boardDao = new BoardDao();
			boolean isSuccess = boardDao.boardReplyCountUpdate(boardNo);
			
			//출력
			if(isSuccess) {				
				resp.sendRedirect(req.getContextPath()+"/board/detail.jsp?boardNo="+boardNo);
			} else {
				resp.sendError(500);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			resp.sendError(500);
		}
	}
}
