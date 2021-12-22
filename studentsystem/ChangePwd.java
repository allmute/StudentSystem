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

public class ChangePwd extends JFrame implements ActionListener {
	// 定义组件
	JFrame frame = new JFrame();
	JPanel JPanel1, JPanel2, JPanel3, JPanel4, JPanel5, JPanel6, JPanel7;// 面板
	JLabel Label_a, Label_user, Label_passworda, Label_passwordb;// 标签
	JButton button_register, button_return;// 按钮
	JTextField textfield;
	JPasswordField passwordfield_a, passwordfield_b;

	// 构造函数
	public ChangePwd() {
		// 创建面板
		JPanel1 = new JPanel();
		JPanel2 = new JPanel();
		JPanel3 = new JPanel();
		JPanel4 = new JPanel();
		JPanel5 = new JPanel();
		JPanel6 = new JPanel();
		JPanel7 = new JPanel();
		// 创建标签
		Label_a = new JLabel("欢迎使用修改密码功能!");
		Label_user = new JLabel("请输入用户账号: ");
		Label_passworda = new JLabel("请输入用户密码: ");
		Label_passwordb = new JLabel("请再次输入密码: ");
		// 创建按钮
		button_register = new JButton("修改");
		button_return = new JButton("返回");
		// 创建文本框
		textfield = new JTextField(12);
		passwordfield_a = new JPasswordField(12);
		passwordfield_b = new JPasswordField(12);
		// passwordfield.setEchoChar('*'); // 将密码显示为*

		// 设置布局管理
		frame.setLayout(new GridLayout(6, 1)); // 网格式布局

		// 加入各个组件
		JPanel1.add(Label_a);

		JPanel2.add(Label_user);
		JPanel2.add(textfield);

		JPanel3.add(Label_passworda);
		JPanel3.add(passwordfield_a);

		JPanel4.add(Label_passwordb);
		JPanel4.add(passwordfield_b);


		JPanel5.add(button_register);
		JPanel5.add(button_return);

		// 加入到JFrame
		frame.add(JPanel1);
		frame.add(JPanel2);
		frame.add(JPanel3);
		frame.add(JPanel4);
		frame.add(JPanel5);
		frame.add(JPanel6);
//		frame.add(JPanel7);

		// 设置字体
		Font font = new Font("微软雅黑", 1, 14);
		Label_user.setFont(font);
		Label_passworda.setFont(font);
		Label_passwordb.setFont(font);
		Label_a.setFont(new Font("微软雅黑", 1, 21));
		button_register.setFont(font);
		button_return.setFont(font);
		// 设置按钮大小
		button_register.setPreferredSize(new Dimension(200, 30));
		button_return.setPreferredSize(new Dimension(200, 30));
		// 取消按钮焦点框
		button_register.setFocusPainted(false);
		button_return.setFocusPainted(false);

		// 设置窗体
		frame.setTitle("密码修改");// 窗体标签
		frame.setSize(595, 471);// 窗体大小
		frame.setLocationRelativeTo(null);// 在屏幕中间显示(居中显示)
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 退出关闭JFrame
		frame.setVisible(true);// 显示窗体
		;

		button_register.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent e) {
				Statement stmt = null;
				int level = 0;
				String username = textfield.getText();
				char[] password = passwordfield_a.getPassword();
				char[] confirmpassword = passwordfield_b.getPassword();
				String pwd = String.valueOf(password); // 转换密码类型为String
				
				if (username.equals("")) {
					JOptionPane.showMessageDialog(null, "账号是必填项!", "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (String.valueOf(password).equals("")) {
					JOptionPane.showMessageDialog(null, "密码是必填项!", "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (!String.valueOf(password).equals(
						String.valueOf(confirmpassword))) {
					JOptionPane.showMessageDialog(null, "两次密码输入不一致!", "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}

				try {
					Connection conn = SQLConnection.getConn(); // 数据库连接
					String sql = "UPDATE userinfo SET password=? WHERE username=?";
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setString(1, pwd);
					ps.setString(2, username);
					int res = ps.executeUpdate();
					if(res > 0){
						JOptionPane.showMessageDialog(null, "密码修改成功!", "成功",
							JOptionPane.INFORMATION_MESSAGE);
					}else{
						JOptionPane.showMessageDialog(null, "密码修改失败!", "失败",
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
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new SystemLogin();
			}
		});
	}

	public void actionPerformed(ActionEvent e) {

	}

	public static void main(String[] args) {
		new ChangePwd();
	}
}