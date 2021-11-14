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
			//답글일 경우 superNo는 null이 아니다.
			String superNo = req.getParameter("superNo");
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
			
			BoardDto board = new BoardDto();
			BoardDao boardDao = new BoardDao();
			//처리[1] : 게시글 등록 전에 시퀀스 번호를 미리 뽑아온다.
			int nowSeq = boardDao.nowSeq();
			
			
			if(superNo == null) {//새글 일 경우
				//입력 : boardDto(id, nick - session / title, content)

				board.setMemberId((String)req.getSession().getAttribute("loginId"));
				board.setMemberNick((String)req.getSession().getAttribute("loginNick"));
				board.setBoardTitle(req.getParameter("boardTitle"));
				board.setBoardContent(req.getParameter("boardContent"));
				board.setBoardAddr(addr);
				
				board.setBoardNo(nowSeq);
				board.setBoardGroupno(nowSeq);
				
				//처리[2] : boardDao.boardInsert메소드
				boardDao.boardInsert(board);
				
			}else {//답글 일 경우
				board.setBoardNo(nowSeq);
				board.setMemberId((String)req.getSession().getAttribute("loginId"));
				board.setMemberNick((String)req.getSession().getAttribute("loginNick"));
				board.setBoardTitle(req.getParameter("boardTitle"));
				board.setBoardContent(req.getParameter("boardContent"));
				
				//여기서부터 상위글의 정보를 가져와서 superNo, groupNo, depth, addr을 설정한다.
				BoardDto boardDto = boardDao.boardDetail(Integer.parseInt(superNo));
				board.setBoardSuperno(boardDto.getBoardNo());
				board.setBoardGroupno(boardDto.getBoardGroupno());
				board.setBoardDepth(boardDto.getBoardDepth()+1);
				board.setBoardAddr(boardDto.getBoardAddr());
				
				boardDao.boardReplyInsert(board);
				
			}
			
			resp.sendRedirect("./detail.jsp?boardNo="+board.getBoardNo());
			
		}catch(Exception e) {
			e.printStackTrace();
			resp.sendError(500);
		}
		
	}
}
