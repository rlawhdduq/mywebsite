package mywebsite.beans;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PointDao {
	
	//1. 포인트 상품 목록 출력 메소드
	public List<PointDto> pointList() throws Exception{
		Connection con = JdbcUtils.connect2();
			
		String query = "select * from point";
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		
		
		List<PointDto> pointList = new ArrayList<>();
		while(rs.next()) {
			PointDto point = new PointDto();
			point.setPointNo(rs.getInt("point_no"));
			point.setPointName(rs.getString("point_name"));
			point.setPointAmount(rs.getInt("point_amount"));
			
			pointList.add(point);
		}
		
		con.close();
		
		return pointList;
	}
	
	//2. 포인트 상품 가격을 구하는 메소드
	public int pointAmount(int pointNo) throws Exception{
		Connection con = JdbcUtils.connect2();
		
		String query = "select point_amount from point where point_no = ?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, pointNo);
		ResultSet rs = ps.executeQuery();
		rs.next();
		
		int pointAmount = rs.getInt(1);
		
		con.close();
		
		return pointAmount;
	}
}
