package studentsystem;

import javax.swing.*;
import javax.swing.table.JTableHeader;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import studentsystem.SQLConnection;

public class GradecourseQuery extends JFrame {
	{
		try {
			// UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			// 当前系统
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
	private JLabel label;
	private JTextField textfield;

	public GradecourseQuery() {
		super("成绩信息查询系统"); // JFrame的标题名称
		this.setSize(660, 600); // 控制窗体大小
		this.setLayout(null); // 自定义布局
		this.setLocation(400, 100); // 点击运行以后，窗体在屏幕的位置
		this.ScrollPane = new JScrollPane();
		this.label = new JLabel("请输入你的课程编号:");
		this.textfield = new JTextField(15);
		this.button_a = new JButton("查询");
		this.button_b = new JButton("取消");
		this.label.setBounds(10, 10, 135, 30);
		this.textfield.setBounds(150, 15, 120, 20);
		this.button_a.setBounds(100, 480, 150, 30);
		this.button_b.setBounds(380, 480, 150, 30);
		this.ScrollPane.setBounds(10, 50, 623, 400); // 设置滚动框大小
		Font font = new Font("微软雅黑", 1, 14);
		label.setFont(font);
		button_a.setFont(font);
		button_b.setFont(font);
		button_a.setFocusPainted(false);
		button_b.setFocusPainted(false);

		/******** 按钮“确定”的响应 *******/
		this.button_a.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Statement stmt = null;
				Connection conn = SQLConnection.getConn(); // 数据库连接
				String id = textfield.getText();

				if (id.equals("")) {
					JOptionPane.showMessageDialog(null, "请输入完整信息!", "错误",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				try { // 判断课程编号信息是否存在
					stmt = conn.createStatement();
					String sql = "select count(*) AS num FROM sc WHERE courseid=?";
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setString(1, id);
					ResultSet jug = ps.executeQuery();
					jug.next();
					int num = jug.getInt("num");
					if (num == 0) {
						JOptionPane.showMessageDialog(null, "此课程编号不存在!",
								"错误", JOptionPane.ERROR_MESSAGE);
						return;
					} else {
						btnShow_ActionPerformed(e);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		/****** 按钮 “取消”的响应 *****/
		this.button_b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		/******* 将组件加入到窗体中 ******/
		add(this.label);
		add(this.textfield);
		add(this.ScrollPane);
		add(this.button_a);
		add(this.button_b);
		this.setVisible(true);
		this.setLocationRelativeTo(null); // 居中显示
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
			String courseid = textfield.getText();
			String sql = "SELECT * FROM sc WHERE courseid ='" + courseid + "'";
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			int count = 0;
			while (rs.next()) {
				count++;
			}
			rs = pstm.executeQuery();
			// 将查询获得的记录数据，转换成适合生成JTable的数据形式
			Object[][] info = new Object[count][6];
			String[] title = { "学号", "班级编号", "课程编号", "成绩" };
			count = 0;
			while (rs.next()) {
				info[count][0] = Integer.valueOf(rs.getInt("id"));
				info[count][1] = rs.getString("classid");
				info[count][2] = rs.getString("courseid");
				info[count][3] = rs.getString("grade");
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
		new GradecourseQuery();
	}
}