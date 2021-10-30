package mywebsite.beans;

import java.sql.DriverManager;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import java.sql.Connection;

public class JdbcUtils {
	
	//[1] 연결을 직접 하는 방식
	// 단점 : 사용자가 과하게 몰릴 경우 부하가 일어나기 쉽다 => 서버에 오류를 발생시킴
	//자바와 오라클을 연결하기 위한 메소드 생성
	public static Connection connect(String username, String pw) throws Exception{
		Class.forName("oracle.jdbc.OracleDriver");
		Connection con = DriverManager.getConnection(
				"jdbc:oracle:thin:@localhost:1521:orcl",username, pw);
		
		return con;
	}
	
	//[2] 라이브러리를 통해서 연결하고 그걸 렌탈하는 방식(DBCP 방식)
	// 장점 : 사용자가 몰려도 처리가 가능하며 서버에 부하가 많이 가지 않는다 => 연결 생성시간을 줄여서 안정성을 향상시키고 성능도 향상시킨다
	// 단점 : 설정파일을 초기에 불러와야 한다(여기서 등장하는 static 으로만 이루어진 메소드)
	
	private static DataSource ds;
	
	static {//이 클래스가 실행될 때 초기에 실행되는 메소드
					try {
						//Context.xml에서 jdbc/oracle이라는 자원의 참조 정보를 가져와야 한다.
						Context ctx = new InitialContext();
						ds = (DataSource)ctx.lookup("java:comp/env/jdbc/oracle");
					}catch(Exception e) {
						e.printStackTrace();
						System.out.println("무언가 잘못되었습니다...");
					}
	}
	
	public static Connection connect2() throws Exception{
		return ds.getConnection();
	}
}
