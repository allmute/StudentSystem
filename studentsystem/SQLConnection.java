package studentsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class SQLConnection {
	private static SQLConnection Information = new SQLConnection();

	public SQLConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "数据库加载失败" + e.getMessage());
		}
	}

	public static Connection getConn() {
		try {
			Connection conn = null;
			String url = "jdbc:mysql://localhost:3306/management_system"; // 127.0.0.1:3306
			conn = DriverManager.getConnection(url, "root", "1201101");
			return conn;
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "数据库连接失败" + e.getMessage());
			return null;
		}
	}

	public void close(ResultSet rs, Statement state, Connection conn) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (state != null) {
				state.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}