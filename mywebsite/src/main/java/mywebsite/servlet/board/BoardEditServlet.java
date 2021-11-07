package mywebsite.servlet.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mywebsite.beans.BoardDao;
import mywebsite.beans.BoardDto;

@WebServlet(urlPatterns = "/board/edit.mws")
public class BoardEditServlet extends HttpServlet{
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
			
			//입력 : BoardDto(title, content), 파라미터(no), 세션(id, nick)
			BoardDto boardDto = new BoardDto();
			boardDto.setBoardNo(Integer.parseInt(req.getParameter("boardNo")));
			boardDto.setBoardTitle(req.getParameter("boardTitle"));
			boardDto.setBoardContent(req.getParameter("boardContent"));
			boardDto.setBoardAddr(addr);
			
			//처리 : boardDao.boardEdit()
			BoardDao boardDao = new BoardDao();
			boolean isEdit = boardDao.boardEdit(boardDto);
			
			//출력 : 상세보기 페이지로 redirect
			if(isEdit) {				
				resp.sendRedirect("detail.jsp?boardNo="+boardDto.getBoardNo());
			} else {
				resp.sendError(500);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			resp.sendError(500);
		}
	}
}
