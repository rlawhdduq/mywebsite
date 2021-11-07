package mywebsite.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CancelDao {
	
	//1. 포인트 충전 내역 철회 시 cancel 테이블에 내역을 등록하도록 설정(여기에 데이터가 있으면 충전 취소 된 것으로 간주)
	public void pointCancel(int historyNo, String memberId) throws Exception{
		Connection con = JdbcUtils.connect2();
		
		String query = "insert into cancel values(?, ?)";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, historyNo);
		ps.setString(2, memberId);
		ps.execute();
		
		con.close();
	}
	
	//2. 포인트 철회 내역 출력 메소드
	public List<CancelDto> cancelList() throws Exception{
		Connection con = JdbcUtils.connect2();
		
		String query = "select * from cancel";
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		
		List<CancelDto> cancelList = new ArrayList<>();
		
		while(rs.next()) {
			CancelDto cancelDto = new CancelDto();
			cancelDto.setHistoryNo(rs.getInt("history_no"));
			cancelDto.setMemberId(rs.getString("member_id"));
			
			cancelList.add(cancelDto);
		}
		con.close();
		
		return cancelList;
	}
	
	//3. 포인트 철회 내역이 있는지 확인하는 메소드(굳이 따지자면 키워드 검색?)
	public CancelDto cancelSearch(int hisotryNo) throws Exception{
		Connection con = JdbcUtils.connect2();
		
		String query = "select * from cancel where history_no = ? order by history_no desc";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, hisotryNo);
		ResultSet rs = ps.executeQuery();
		
		CancelDto cancelDto;
		if(rs.next()) {
			cancelDto = new CancelDto();
			cancelDto.setHistoryNo(rs.getInt("history_no"));
			cancelDto.setMemberId(rs.getString("member_id"));
		} else {
			cancelDto = null;
		}
		
		con.close();
		
		return cancelDto;
	}
}
