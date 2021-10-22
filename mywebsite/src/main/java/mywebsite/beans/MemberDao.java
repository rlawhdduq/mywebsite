package mywebsite.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MemberDao {
	private static final String USERNAME = "mws", PASSWORD = "mws";
	
	//1. 회원가입 처리 메소드
	public void memberJoin(MemberDto memberDto) throws Exception {
		Connection con = JdbcUtils.connect(USERNAME, PASSWORD);
		
		String query = "insert into member values(?, ?, ?, ?, ?, ?, to_date(sysdate, 'YYYY-MM-DD'), '준회원')";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, memberDto.getMemberId());
		ps.setString(2, memberDto.getMemberNick());
		ps.setString(3, memberDto.getMemberPw());
		ps.setString(4, memberDto.getMemberEmail());
		ps.setString(5, memberDto.getMemberPhone());
		ps.setDate(6, memberDto.getMemberBirth());
		ps.execute();
		
		con.close();
	}
	
	//2. 로그인 처리 메소드
	public MemberDto memberLogin(String memberId, String memberPw) throws Exception{
		Connection con = JdbcUtils.connect(USERNAME, PASSWORD);
		
		String query = "select * from member where member_id = ? and member_pw = ?";
		PreparedStatement ps = con.prepareStatement(query);
		
		ps.setString(1, memberId);
		ps.setString(2, memberPw);
		
		ResultSet rs = ps.executeQuery();
		MemberDto memberDto;
		if(rs.next()) {
			memberDto = new MemberDto();
			memberDto.setMemberId(rs.getString("member_id"));
			memberDto.setMemberNick(rs.getString("member_nick"));
			memberDto.setMemberPw(rs.getString("member_pw"));
			memberDto.setMemberEmail(rs.getString("member_email"));
			memberDto.setMemberPhone(rs.getString("member_phone"));
			memberDto.setMemberBirth(rs.getDate("member_birth"));
			memberDto.setMemberJoin(rs.getDate("member_join"));
			memberDto.setMemberGrade(rs.getString("member_grade"));
			
		} else {
			memberDto = null;
		}
		
		con.close();
		
		return memberDto;
	}
}
