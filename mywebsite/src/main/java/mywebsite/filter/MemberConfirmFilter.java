package mywebsite.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mywebsite.beans.BoardDao;
import mywebsite.beans.BoardDto;

@WebFilter(urlPatterns = {"/board/delete.mws", "/board/edit.jsp"})
public class MemberConfirmFilter implements Filter{
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		try {
			String memberId = (String)req.getSession().getAttribute("loginId");
			
			int boardNo = Integer.parseInt(req.getParameter("boardNo"));
			BoardDao boardDao = new BoardDao();
			BoardDto boardDto = boardDao.boardDetail(boardNo);
			
			String boardId = boardDto.getMemberId();
			boolean isTrue = memberId.equals(boardId);
			
			if(isTrue) {
				chain.doFilter(req, resp);
			} else {
				resp.sendError(403);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			resp.sendError(500);
		}
		
	}
}
