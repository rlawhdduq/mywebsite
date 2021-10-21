package mywebsite.beans;

import java.sql.DriverManager;

import java.sql.Connection;

public class JdbcUtils {
	
	//자바와 오라클을 연결하기 위한 메소드 생성
	public static Connection connect(String username, String pw) throws Exception{
		Class.forName("oracle.jdbc.OracleDriver");
		Connection con = DriverManager.getConnection(
				"jdbc:oracle:thin:@localhost:1521:orcl",username, pw);
		
		return con;
	}
}
