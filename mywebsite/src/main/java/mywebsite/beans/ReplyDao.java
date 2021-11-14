package mywebsite.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ReplyDao {
	
	//1.댓글 목록 조회 메소드
	public List<ReplyDto> replyList(int boardNo) throws Exception{
		Connection con = JdbcUtils.connect2();
		
		String query = "select * from reply where board_no = ?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, boardNo);
		
		ResultSet rs = ps.executeQuery();
		
		List<ReplyDto> replyList = new ArrayList<>();
		
		while(rs.next()) {
			ReplyDto reply = new ReplyDto();
			reply.setReplyNo(rs.getInt("reply_no"));
			reply.setMemberId(rs.getString("member_id"));
			reply.setReplyContent(rs.getString("reply_content"));
			reply.setBoardNo(rs.getInt("board_no"));
			reply.setRating(rs.getInt("rating_no"));
			reply.setReplyLike(rs.getInt("reply_like"));
			reply.setReplyUnLike(rs.getInt("reply_unlike"));
			reply.setReplyUpload(rs.getDate(("reply_upload")));
			
			replyList.add(reply);
		}
		
		con.close();
		
		return replyList;
	}
	
	//2. 댓글 등록 메소드
	public void replyInsert(ReplyDto replyDto) throws Exception{
		Connection con = JdbcUtils.connect2();
		
		String query = "insert into reply values(reply_seq.nextval, ?, ?, ?, 0, 0, ?, sysdate)";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, replyDto.getMemberId());
		ps.setInt(2, replyDto.getBoardNo());
		ps.setInt(3, replyDto.getRating());
		ps.setString(4, replyDto.getReplyContent());
		ps.execute();
		
		con.close();
	}
	
	//3. 댓글 수정 메소드
	public boolean replyEdit(int replyNo, String replyContent)throws Exception {
		Connection con = JdbcUtils.connect2();
		
		String query = "update reply reply_content = ? where reply_no = ?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, replyContent);
		ps.setInt(2, replyNo);
		int result = ps.executeUpdate();
		
		con.close();
		
		return result > 0;
	}
	
	//4. 댓글 삭제 메소드
	public boolean replyDelete(int replyNo)throws Exception{
		Connection con = JdbcUtils.connect2();
		
		String query = "delete reply where reply_no = ?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, replyNo);
		int result = ps.executeUpdate();
		
		con.close();
		
		return result > 0;
	}
	
}
