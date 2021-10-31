package mywebsite.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class HistoryDao {
	
	//1. 히스토리 목록 조회 메소드(memberId)
	public List<HistoryDto> historyList(String memberId) throws Exception {
		Connection con = JdbcUtils.connect2();
		
		String query = "select * from history where member_id = ?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, memberId);
		ResultSet rs = ps.executeQuery();
		
		List<HistoryDto> historyList = new ArrayList<>();
		while(rs.next()) {
			HistoryDto history = new HistoryDto();
			history.setHistoryNo(rs.getInt("history_no"));
			history.setMemberId(rs.getString("member_id"));
			history.setHistoryTime(rs.getDate("history_time"));
			history.setHistoryMemo(rs.getString("history_memo"));
			history.setHistoryAmount(rs.getInt("history_amount"));
			
			historyList.add(history);
		}
		
		con.close();
		
		return historyList;
	}
	
	//2. 포인트 충전 내역 등록 메소드
	public void historyInsert(HistoryDto historyDto) throws Exception{
		Connection con = JdbcUtils.connect2();
		
		String query = "insert into history values(history_seq.nextval, ?, sysdate, ?, ?)";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, historyDto.getMemberId());
		ps.setString(2, historyDto.getHistoryMemo());
		ps.setInt(3, historyDto.getHistoryAmount());
		ps.execute();
				
		con.close();
	}
	
	//3. 가격을 뽑기위한 단일조회 메소드
	public HistoryDto historyOneSearch(int historyNo) throws Exception{
		Connection con = JdbcUtils.connect2();
		
		String query = "select * from history where history_no = ?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, historyNo);
		ResultSet rs = ps.executeQuery();
		
		HistoryDto historyDto;
		if(rs.next()) {
			historyDto = new HistoryDto();
			historyDto.setHistoryNo(rs.getInt("history_no"));
			historyDto.setMemberId(rs.getString("member_id"));
			historyDto.setHistoryTime(rs.getDate("history_time"));
			historyDto.setHistoryMemo(rs.getString("history_memo"));
			historyDto.setHistoryAmount(rs.getInt("history_amount"));
		} else {
			historyDto = null;
		}
		
		con.close();
		
		return historyDto;
	}
}
