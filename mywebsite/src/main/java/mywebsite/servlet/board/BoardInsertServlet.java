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
			//주소를 도, 시, 구, 동 각각 받아온다
			String Do = req.getParameter("addrDo");
			String si = req.getParameter("addrSi");
			String goo = req.getParameter("addrGoo");
			String dong = req.getParameter("addrDong");
			String addr = "";
			//계산식 : 만약 도를 입력하지 않았다면 도를 빼고 더한다.
			if(Do == null) {
				addr =si + goo + dong;
			} else {				
				addr = Do + si + goo + dong;
			}
			
			//입력 : boardDto(id, nick - session / title, content)
			BoardDto board = new BoardDto();
			board.setMemberId((String)req.getSession().getAttribute("loginId"));
			board.setMemberNick((String)req.getSession().getAttribute("loginNick"));
			board.setBoardTitle(req.getParameter("boardTitle"));
			board.setBoardContent(req.getParameter("boardContent"));
			board.setBoardAddr(addr);
			
			BoardDao boardDao = new BoardDao();
			//처리[1] : 게시글 등록 전에 시퀀스 번호를 미리 뽑아온다.
			int nowSeq = boardDao.nowSeq();
			board.setBoardNo(nowSeq);
			board.setBoardGroupno(nowSeq);
			
			//처리[2] : boardDao.boardInsert메소드
			boardDao.boardInsert(board);
			
			resp.sendRedirect("./detail.jsp?boardNo="+nowSeq);
			
		}catch(Exception e) {
			e.printStackTrace();
			resp.sendError(500);
		}
		
	}
}
