package mywebsite.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BoardDao {
	private static final String USERNAME = "mws", PASSWORD = "mws";
	
	//1. 게시판 등록 메소드
	public void boardInsert(BoardDto boardDto)throws Exception{
		Connection con = JdbcUtils.connect(USERNAME,PASSWORD);
		
		String query = "insert into board values(board_seq.nextval, "
				+ "?, ?, ?, ?, to_date(sysdate, 'YYYY-MM-DD HH24:MI:SS'), 0, 0, 0)";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, boardDto.getMemberId());
		ps.setString(2, boardDto.getMemberNick());
		ps.setString(3, boardDto.getBoardTitle());
		ps.setString(4, boardDto.getBoardContent());
		ps.execute();
			
		con.close();
	}
}
