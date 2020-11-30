package Hospital;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBUtil {
	static String ip = "159.203.59.83";
	static int port = 3306;
	static String database = "Hospital";
	static String encoding = "verifyServerCertificate=false&useSSL=false";
	static String loginName = "Hospital";
	static String password = "78jBX43kYeKxrx74";
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}


	
	public static Connection getConn() {

		String url = String.format("jdbc:mysql://%s:%d/%s?%s", ip, port, database, encoding);
		try {
			return DriverManager.getConnection(url, loginName, password);
			//return DriverManager.getConnection("jdbc:mysql://137.207.76.172/geng115_MobileExpense","geng115_MobileExpense","123456");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
