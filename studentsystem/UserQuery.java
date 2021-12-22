package studentsystem;

import javax.swing.*;
import javax.swing.table.JTableHeader;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import studentsystem.SQLConnection;

public class UserQuery extends JFrame {
	{
		try {
//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());  当前系统
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	private JScrollPane ScrollPane;
	private JTableHeader jth;
	private JTable JTable;
	private JButton btnShow;
	private JButton button_a;
	private JButton button_b;

	public UserQuery() {
		super("一般用户信息查询系统"); // JFrame的标题名称
		this.setSize(660, 600); // 控制窗体大小
		this.setLayout(null); // 自定义布局
		this.setLocation(400, 100); // 点击运行以后，窗体在屏幕的位置
		this.ScrollPane = new JScrollPane();
		this.button_a = new JButton("查询");
		this.button_b = new JButton("取消");
		this.button_a.setBounds(100, 480, 150, 30);
		this.button_b.setBounds(380, 480, 150, 30);
		this.ScrollPane.setBounds(10, 50, 623, 400); // 设置滚动框大小
		Font font = new Font("微软雅黑", 1, 14);
		button_a.setFont(font);
		button_b.setFont(font);
		button_a.setFocusPainted(false);
		button_b.setFocusPainted(false);
		
		
		/******** 按钮“确定”的响应 *******/
		this.button_a.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					btnShow_ActionPerformed(e);
			}
		});
		/****** 按钮 “取消”的响应 *****/
		this.button_b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		/******* 将组件加入到窗体中 ******/
		add(this.ScrollPane);
		add(this.button_a);
		add(this.button_b);
		this.setVisible(true);
		this.setLocationRelativeTo(null);  //居中显示
//		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	/*** 连接数据库并显示到表格中 ***/
	public void btnShow_ActionPerformed(ActionEvent ae) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/management_system";
			String username = "root";
			String passwords = "1201101";
			Connection conn = DriverManager.getConnection(url, username,
					passwords);
			String sql = "SELECT * FROM userinfo";
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			int count = 0;
			while (rs.next()) {
				count++;
			}
			rs = pstm.executeQuery();
			// 将查询获得的记录数据，转换成适合生成JTable的数据形式
			Object[][] info = new Object[count][6];
			String[] title = { "uid", "用户账号", "用户密码", "用户权限" };
			count = 0;
			while (rs.next()) {
				info[count][0] = rs.getString("uid");
				info[count][1] = rs.getString("username");
				info[count][2] = rs.getString("password");
				info[count][3] = rs.getString("level");
				count++;
			}
			// 创建JTable
			this.JTable = new JTable(info, title);
			// 显示表头
			this.jth = this.JTable.getTableHeader();
			// 将JTable加入到带滚动条的面板中
			this.ScrollPane.getViewport().add(JTable);
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
			JOptionPane.showMessageDialog(null, "数据源错误", "错误",
					JOptionPane.ERROR_MESSAGE);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			JOptionPane.showMessageDialog(null, "数据操作错误", "错误",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void main(String[] args) {
		new UserQuery();
	}
}