package asDAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtill {
	static final String driver = "oracle.jdbc.driver.OracleDriver";
	static final String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	
public static Connection getConnection() throws Exception{
	Class.forName(driver);
	Connection con = DriverManager.getConnection(url, "sys as sysdba", "oracle");
	System.out.println("DB연결 성공");
	return con;
}
/*DriverManager: 데이터베이스와의 연결을 위해서 드라이버를 로드할 수 있게 해줌
 * Connection: 특정한 데이터베이스와 개발환경 사이의 연결을 제공   */
}