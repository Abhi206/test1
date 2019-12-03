package utils;

import java.sql.*;

public class DBUtils {
	public static Connection fetchConnection() throws ClassNotFoundException, SQLException {
		String url = "jdbc:mysql://localhost:3333/prn_162_165";
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection(url, "root", "actscdac");
	}
}
