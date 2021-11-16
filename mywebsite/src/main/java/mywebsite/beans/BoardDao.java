package mywebsite.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BoardDao {
	private static final String USERNAME = "mws", PASSWORD = "mws";
	
	//1. 게시글 등록(새글) 메소드
	public void boardInsert(BoardDto boardDto)throws Exception{
		Connection con = JdbcUtils.connect2();
		
		String query = "insert into board values(?, "
				+ "?, ?, ?, ?,sysdate, 0, 0, 0, 0, null, ?, 0, ?)";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, boardDto.getBoardNo());
		ps.setString(2, boardDto.getMemberId());
		ps.setString(3, boardDto.getMemberNick());
		ps.setString(4, boardDto.getBoardTitle());
		ps.setString(5, boardDto.getBoardContent());
		ps.setInt(6, boardDto.getBoardGroupno());
		ps.setString(7, boardDto.getBoardAddr());
		ps.execute();
			
		con.close();
	}
	//2. 게시글 등록(답글) 메소드
	public void boardReplyInsert(BoardDto boardDto) throws Exception{
		Connection con = JdbcUtils.connect2();
		
		String query = "insert into board values(?, ?, ?, ?, ?, sysdate, 0, 0, 0, 0, ?, ?, ?, ?)";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, boardDto.getBoardNo());
		ps.setString(2, boardDto.getMemberId());
		ps.setString(3, boardDto.getMemberNick());
		ps.setString(4, boardDto.getBoardTitle());
		ps.setString(5, boardDto.getBoardContent());
		ps.setInt(6, boardDto.getBoardSuperno());
		ps.setInt(7, boardDto.getBoardGroupno());
		ps.setInt(8, boardDto.getBoardDepth());
		ps.setString(9, boardDto.getBoardAddr());
		
		ps.execute();
		
		con.close();
	}
	
	//3. 게시글 조회 메소드//트리정렬 적용
	public List<BoardDto> boardList() throws Exception{
		Connection con = JdbcUtils.connect2();
		
		String query = "select * from ("+
				"select rownum rn, tmp.* from ("+
					"select * from board "
					+ "connect by prior board_no = board_superno "
					+ "start with board_superno is null "
					+ "order siblings by board_groupno desc, board_no asc"
				+ ")tmp"+
			")where rn between 0 and 10";
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		
		List<BoardDto> boardList = new ArrayList<>();
		while(rs.next()) {
			BoardDto boardDto = new BoardDto();
			boardDto.setBoardNo(rs.getInt("board_no"));
			boardDto.setMemberId(rs.getString("member_id"));
			boardDto.setMemberNick(rs.getString("member_nick"));
			boardDto.setBoardTitle(rs.getString("board_title"));
			boardDto.setBoardContent(rs.getString("board_content"));
			boardDto.setBoardUploadTime(rs.getDate("board_uploadtime"));
			boardDto.setBoardHit(rs.getInt("board_hit"));
			boardDto.setBoardLike(rs.getInt("board_like"));
			boardDto.setBoardUnlike(rs.getInt("board_unlike"));
			boardDto.setBoardReply(rs.getInt("board_reply"));
			boardDto.setBoardSuperno(rs.getInt("board_superno"));
			boardDto.setBoardGroupno(rs.getInt("board_groupno"));
			boardDto.setBoardDepth(rs.getInt("board_depth"));
			boardDto.setBoardAddr(rs.getString("board_addr"));
			
			boardList.add(boardDto);
		}
		
		con.close();
		
		return boardList;
	}
	
	//4. 현재 시퀀스 번호를 확인하는 메소드
	public int nowSeq() throws Exception{
		Connection con = JdbcUtils.connect2();
		
		String query = "select board_seq.nextval from dual";
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		rs.next();
		
		int nextSeq = rs.getInt("nextval");
		
		con.close();
		
		return nextSeq;
	}
	
	//5. 게시글 단일조회 메소드
	public BoardDto boardDetail(int boardNo) throws Exception{
		Connection con = JdbcUtils.connect2();
		
		String query = "select * from board where board_no = ?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, boardNo);
		ResultSet rs = ps.executeQuery();
		
		BoardDto boardDto;
		if(rs.next()) {
			boardDto = new BoardDto();
			boardDto.setBoardNo(rs.getInt("board_no"));
			boardDto.setMemberId(rs.getString("member_id"));
			boardDto.setMemberNick(rs.getString("member_nick"));
			boardDto.setBoardTitle(rs.getString("board_title"));
			boardDto.setBoardContent(rs.getString("board_content"));
			boardDto.setBoardUploadTime(rs.getDate("board_uploadtime"));
			boardDto.setBoardHit(rs.getInt("board_hit"));
			boardDto.setBoardLike(rs.getInt("board_like"));
			boardDto.setBoardUnlike(rs.getInt("board_unlike"));
			boardDto.setBoardReply(rs.getInt("board_reply"));
			boardDto.setBoardSuperno(rs.getInt("board_superno"));
			boardDto.setBoardGroupno(rs.getInt("board_groupno"));
			boardDto.setBoardDepth(rs.getInt("board_depth"));
			boardDto.setBoardAddr(rs.getString("board_addr"));
		} else {
			boardDto = null;
		}
		
		con.close();
		
		return boardDto;
	}
	
	//6. 게시글 수정 메소드
	public boolean boardEdit(BoardDto boardDto)  throws Exception{
		Connection con = JdbcUtils.connect2();
		
		String query = "update board set board_title = ?, board_content = ?, board_addr = ? where board_no = ?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, boardDto.getBoardTitle());
		ps.setString(2, boardDto.getBoardContent());
		ps.setString(3, boardDto.getBoardAddr());
		ps.setInt(4, boardDto.getBoardNo());
		
		int result = ps.executeUpdate();
		
		con.close();
		
		return result > 0;
	}
	
	//7. 게시글 삭제 메소드
	public boolean boardDelete(int boardNo) throws Exception{
		Connection con = JdbcUtils.connect2();
		
		String query = "delete board where board_no = ?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, boardNo);
		
		int result = ps.executeUpdate();
		
		con.close();
		
		return result > 0;
	}
	
	//8. 게시글 검색 메소드
	public List<BoardDto> boardSearch(String column, String keyword) throws Exception {
		Connection con = JdbcUtils.connect2();
		
		String query = "select * from board where instr(#1, ?) > 0 order by board_no desc";
		query = query.replace("#1", column);
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, keyword);
		ResultSet rs = ps.executeQuery();
		
		List<BoardDto> boardList = new ArrayList<>();
		
		while(rs.next()) {
			BoardDto boardDto = new BoardDto();
			boardDto.setBoardNo(rs.getInt("board_no"));
			boardDto.setMemberId(rs.getString("member_id"));
			boardDto.setMemberNick(rs.getString("member_nick"));
			boardDto.setBoardTitle(rs.getString("board_title"));
			boardDto.setBoardContent(rs.getString("board_content"));
			boardDto.setBoardUploadTime(rs.getDate("board_uploadtime"));
			boardDto.setBoardHit(rs.getInt("board_hit"));
			boardDto.setBoardLike(rs.getInt("board_like"));
			boardDto.setBoardUnlike(rs.getInt("board_unlike"));
			boardDto.setBoardReply(rs.getInt("board_reply"));
			boardDto.setBoardSuperno(rs.getInt("board_superno"));
			boardDto.setBoardGroupno(rs.getInt("board_groupno"));
			boardDto.setBoardDepth(rs.getInt("board_depth"));
			boardDto.setBoardAddr(rs.getString("board_addr"));
			
			boardList.add(boardDto);
		}
		
		con.close();
		
		return boardList;
	}
	
	//9. 댓글개수 업데이트 메소드
	public boolean boardReplyCountUpdate(int boardNo) throws Exception{
		Connection con = JdbcUtils.connect2();
		
		String query = "update board set board_reply = board_reply+1 where board_no = ?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, boardNo);
		int result = ps.executeUpdate();
		
		con.close();
		
		return result > 0;
	}
	
	//페이징에서 마지막 블록 구하는 메소드 - 검색
	public int countBlock(String column, String keyword) throws Exception{
		Connection con = JdbcUtils.connect2();
		
		String query = "select count(*) from board where instr(#1, ?) > 0";
		query = query.replace("#1", column);
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, keyword);
		ResultSet rs = ps.executeQuery();
		
		rs.next();
		
		int count = rs.getInt(1);
		
		con.close();
		
		return count;
	}
	
	//페이징에서 마지막 블록을 구하는 메소드 - 목록
	public int countBlock() throws Exception{
		Connection con = JdbcUtils.connect2();
		
		String query = "select count(*) from board";
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		
		rs.next();
		
		int count = rs.getInt(1);
		
		con.close();
		
		return count;
	}
	
	//페이징 검색 메소드
	public List<BoardDto> listByTreeSortSearch(int begin, int end, String column, String keyword) throws Exception{
		Connection con = JdbcUtils.connect2();
			
		String query = "select * from ("
						+ "select rownum rn, tmp.* from("
						+ "select * from board where instr(#1, ?) > 0 "
						+ "connect by prior board_no = board_superno "
						+ "start with board_superno is null "
						+ "order siblings by board_group desc, board_no asc"
						+ ")tmp"
						+")where rn between ? and ? ";
		query = query.replace("#1", column);
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, keyword);
		ps.setInt(2, begin);
		ps.setInt(3, end);
		ResultSet rs = ps.executeQuery();
		
		List<BoardDto> boardList = new ArrayList<>();
		while(rs.next()) {
			BoardDto board = new BoardDto();
			board.setBoardNo(rs.getInt("board_no"));
			board.setMemberId(rs.getString("member_id"));
			board.setMemberNick(rs.getString("member_nick"));
			board.setBoardTitle(rs.getString("board_title"));
			board.setBoardContent(rs.getString("board_content"));
			board.setBoardUploadTime(rs.getDate("board_uploadtime"));
			board.setBoardHit(rs.getInt("board_hit"));
			board.setBoardLike(rs.getInt("board_like"));
			board.setBoardUnlike(rs.getInt("board_unlike"));
			board.setBoardReply(rs.getInt("board_reply"));
			board.setBoardSuperno(rs.getInt("board_superno"));
			board.setBoardGroupno(rs.getInt("board_groupno"));
			board.setBoardDepth(rs.getInt("board_depth"));
			board.setBoardAddr(rs.getString("board_addr"));
			
			boardList.add(board);
		}
		
		con.close();
		
		return boardList;
	}
	
	//페이징 목록 메소드
	public List<BoardDto> listByTreeSort(int begin, int end) throws Exception{
		Connection con = JdbcUtils.connect2();
		
		String query = "select * from ("
						+ "select rownum rn, tmp.* from("
						+ "select * from board "
						+ "connect by prior board_no = board_superno "
						+ "start with board_superno is null "
						+ "order siblings by board_groupno desc, board_no asc"
						+ ")tmp"
						+")where rn between ? and ? ";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, begin);
		ps.setInt(2, end);
		ResultSet rs = ps.executeQuery();
		
		List<BoardDto> boardList = new ArrayList<>();
		while(rs.next()) {
			BoardDto board = new BoardDto();
			board.setBoardNo(rs.getInt("board_no"));
			board.setMemberId(rs.getString("member_id"));
			board.setMemberNick(rs.getString("member_nick"));
			board.setBoardTitle(rs.getString("board_title"));
			board.setBoardContent(rs.getString("board_content"));
			board.setBoardUploadTime(rs.getDate("board_uploadtime"));
			board.setBoardHit(rs.getInt("board_hit"));
			board.setBoardLike(rs.getInt("board_like"));
			board.setBoardUnlike(rs.getInt("board_unlike"));
			board.setBoardReply(rs.getInt("board_reply"));
			board.setBoardSuperno(rs.getInt("board_superno"));
			board.setBoardGroupno(rs.getInt("board_groupno"));
			board.setBoardDepth(rs.getInt("board_depth"));
			board.setBoardAddr(rs.getString("board_addr"));
			
			boardList.add(board);
		}
		
		con.close();
		
		return boardList;
	}
	
}
