package studentsystem;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

import studentsystem.SQLConnection;

public class Register extends JFrame implements ActionListener {

	// 定义组件
	JFrame frame = new JFrame();
	JPanel JPanel1, JPanel2, JPanel3, JPanel4, JPanel5, JPanel6, JPanel7;// 面板
	JLabel Label_a, Label_b, Label_user, Label_passworda, Label_passwordb;// 标签
	JButton button_register, button_return;// 按钮
	JTextField textfield;
	JPasswordField passwordfield_a, passwordfield_b;

	// 构造函数
	public Register() {
		// 创建面板
		JPanel1 = new JPanel();
		JPanel2 = new JPanel();
		JPanel3 = new JPanel();
		JPanel4 = new JPanel();
		JPanel5 = new JPanel();
		JPanel6 = new JPanel();
		JPanel7 = new JPanel();
		// 创建标签
		Label_a = new JLabel("欢迎使用学生信息管理系统!");
		Label_user = new JLabel("请输入注册账号: ");
		Label_passworda = new JLabel("请输入注册密码: ");
		Label_passwordb = new JLabel("请再次输入密码: ");
		Label_b = new JLabel("请选择你的身份信息: ");

		String[] list = new String[] { "学生", "教师", "管理员" };
		final JComboBox<String> comboBox = new JComboBox<String>(list);
		comboBox.setSelectedIndex(0);
		// 创建按钮
		button_register = new JButton("注册");
		button_return = new JButton("返回");
		// 创建文本框
		textfield = new JTextField(12);
		passwordfield_a = new JPasswordField(12);
		passwordfield_b = new JPasswordField(12);
		// passwordfield.setEchoChar('*'); // 将密码显示为*

		// 设置布局管理
		frame.setLayout(new GridLayout(7, 1)); // 网格式布局

		// 加入各个组件
		JPanel1.add(Label_a);

		JPanel2.add(Label_user);
		JPanel2.add(textfield);

		JPanel3.add(Label_passworda);
		JPanel3.add(passwordfield_a);

		JPanel4.add(Label_passwordb);
		JPanel4.add(passwordfield_b);

		JPanel5.add(Label_b);
		JPanel5.add(comboBox);

		JPanel6.add(button_register);
		JPanel6.add(button_return);

		// 加入到JFrame
		frame.add(JPanel1);
		frame.add(JPanel2);
		frame.add(JPanel3);
		frame.add(JPanel4);
		frame.add(JPanel5);
		frame.add(JPanel6);
		frame.add(JPanel7);

		// 设置字体
		Font font = new Font("微软雅黑", 1, 14);
		Label_user.setFont(font);
		Label_passworda.setFont(font);
		Label_passwordb.setFont(font);
		Label_a.setFont(new Font("微软雅黑", 1, 21));
		Label_b.setFont(new Font("微软雅黑", 1, 13));
		button_register.setFont(font);
		button_return.setFont(font);
		comboBox.setFont(font);
		// 设置按钮大小
		button_register.setPreferredSize(new Dimension(200, 30));
		button_return.setPreferredSize(new Dimension(200, 30));
		// 取消按钮焦点框
		button_register.setFocusPainted(false);
		button_return.setFocusPainted(false);

		// 设置窗体
		frame.setTitle("用户注册");// 窗体标签
		frame.setSize(595, 471);// 窗体大小
		frame.setLocationRelativeTo(null);// 在屏幕中间显示(居中显示)
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 退出关闭JFrame
		frame.setVisible(true);// 显示窗体
		;

		button_register.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent e) {
				Statement stmt = null;
				int index = comboBox.getSelectedIndex();
				int level = 0;
				String username = textfield.getText();
				char[] password = passwordfield_a.getPassword();
				char[] confirmpassword = passwordfield_b.getPassword();
				String pwd = String.valueOf(password); // 转换密码类型为String

				if (index == 0) {
					level = 3;
				}
				if (index == 1) {
					level = 2;
				}
				if (index == 2) {
					level = 1;
				}
				if (username.equals("")) {
					JOptionPane.showMessageDialog(null, "账号是必填项!", "错误",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (String.valueOf(password).equals("")) {
					JOptionPane.showMessageDialog(null, "密码是必填项!", "错误",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (!String.valueOf(password).equals(
						String.valueOf(confirmpassword))) {
					JOptionPane.showMessageDialog(null, "两次密码输入不一致!", "错误",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				try {
					Connection conn = SQLConnection.getConn(); // 数据库连接
					stmt = conn.createStatement();
					String check = "select count(*) AS num FROM userinfo WHERE username='"
							+ username + "'";
					ResultSet rs = stmt.executeQuery(check);
					int count = 0;
					while(rs.next()){
						count = rs.getInt(1);
					}
					if (count != 0) {  //判断账号是否存在
						JOptionPane.showMessageDialog(null, "此账号已存在!", "错误",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					String sql = "INSERT INTO userinfo (uid,username,password,level) VALUES(?,?,?,?)";
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setString(1, "");
					ps.setString(2, username);
					ps.setString(3, pwd);
					ps.setInt(4, level);
					int res = ps.executeUpdate();

					if (res > 0) {
						JOptionPane.showMessageDialog(null, "账号注册成功!", "成功",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "账号注册失败!", "失败",
								JOptionPane.INFORMATION_MESSAGE);
					}
					conn.close();
					frame.setVisible(false);
					new SystemLogin();
				} catch (SQLException ex) {
					System.err.println("SQLException: " + ex.getMessage());
				}
			}

		});

		button_return.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new SystemLogin();

			}
		});
	}

	public void actionPerformed(ActionEvent e) {

	}

	public static void main(String[] args) {
		new Register();
	}
}