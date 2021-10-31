package mywebsite.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CancelDao {
	
	//1. 포인트 충전 내역 철회 시 cancel 테이블에 내역을 등록하도록 설정(여기에 데이터가 있으면 충전 취소 된 것으로 간주)
	public void pointCancel(int historyNo) throws Exception{
		Connection con = JdbcUtils.connect2();
		
		String query = "insert into cancel values(?)";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, historyNo);
		ps.execute();
		
		con.close();
	}
	
	//2. 포인트 철회 내역이 있는지 확인(테이블 필드가 1개이므로 int값만 나오니까 그걸 비교해서 있으면 철회한거고 없으면 철회한적 없는걸로 처리)
	public int cancelList() throws Exception{
		Connection con = JdbcUtils.connect2();
		
		String query = "select * from cancel";
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		
		rs.next();
		
		int cancel = rs.getInt("history_no");		
		con.close();
		
		return cancel;
	}
}
