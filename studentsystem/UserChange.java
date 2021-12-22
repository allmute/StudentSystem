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

public class UserChange extends JFrame implements ActionListener {

	// 定义组件
	JFrame frame = new JFrame();
	JPanel JPanel1, JPanel2, JPanel3, JPanel4, JPanel5, JPanel6, JPanel7,
			JPanel8, JPanel9, JPanel10, JPanel11;// 面板
	JLabel Label_a, Label_b, Label_c, Label_user, Label_passworda,
			Label_passwordb;// 标签
	JButton button_insert, button_update, button_delete, button_return;// 按钮
	JTextField textfield;
	JPasswordField passwordfield_a, passwordfield_b;

	// 构造函数
	public UserChange() {
		// 创建面板
		JPanel1 = new JPanel();
		JPanel2 = new JPanel();
		JPanel3 = new JPanel();
		JPanel4 = new JPanel();
		JPanel5 = new JPanel();
		JPanel6 = new JPanel();
		JPanel7 = new JPanel();
		JPanel8 = new JPanel();
		JPanel9 = new JPanel();
		JPanel10 = new JPanel();
		JPanel11 = new JPanel();

		// 创建标签
		Label_a = new JLabel("欢迎使用用户信息修改功能!");
		Label_b = new JLabel("请输入账号进行相关操作");
		Label_c = new JLabel("请选择身份信息:");
		Label_user = new JLabel("请输入用户账号: ");
		Label_passworda = new JLabel("请输入用户密码:");
		Label_passwordb = new JLabel("请再次输入密码:");

		// 创建下拉菜单
		String[] list = new String[] { "学生", "教师", "管理员" };
		final JComboBox<String> comboBox = new JComboBox<String>(list);
		comboBox.setSelectedIndex(0);
		// 创建按钮
		button_insert = new JButton("插入");
		button_update = new JButton("更新");
		button_delete = new JButton("删除");
		button_return = new JButton("返回");
		// 创建文本框
		textfield = new JTextField(12);
		passwordfield_a = new JPasswordField(12);
		passwordfield_b = new JPasswordField(12);
		// passwordfield.setEchoChar('*'); // 将密码显示为*

		// 设置布局管理
		frame.setLayout(new GridLayout(11, 1)); // 网格式布局

		// 加入各个组件
		JPanel1.add(Label_a);
		JPanel2.add(Label_b);

		JPanel3.add(Label_user);
		JPanel3.add(textfield);

		JPanel4.add(Label_passworda);
		JPanel4.add(passwordfield_a);

		JPanel5.add(Label_passwordb);
		JPanel5.add(passwordfield_b);
		JPanel6.add(Label_c);
		JPanel6.add(comboBox);

		JPanel7.add(button_insert);
		JPanel8.add(button_update);
		JPanel9.add(button_delete);
		JPanel10.add(button_return);

		// 加入到JFrame
		frame.add(JPanel1);
		frame.add(JPanel2);
		frame.add(JPanel3);
		frame.add(JPanel4);
		frame.add(JPanel5);
		frame.add(JPanel6);
		frame.add(JPanel7);
		frame.add(JPanel8);
		frame.add(JPanel9);
		frame.add(JPanel10);
		frame.add(JPanel11);

		// 设置字体
		Font font = new Font("微软雅黑", 1, 14);
		Label_user.setFont(font);
		Label_passworda.setFont(font);
		Label_passwordb.setFont(font);
		Label_a.setFont(new Font("微软雅黑", 1, 21));
		Label_b.setFont(new Font("微软雅黑", 1, 13));
		Label_c.setFont(font);
		button_insert.setFont(font);
		button_update.setFont(font);
		button_delete.setFont(font);
		button_return.setFont(font);
		// 设置按钮大小
		button_insert.setPreferredSize(new Dimension(243, 30));
		button_update.setPreferredSize(new Dimension(243, 30));
		button_delete.setPreferredSize(new Dimension(243, 30));
		button_return.setPreferredSize(new Dimension(243, 30));
		// 取消按钮焦点框
		button_insert.setFocusPainted(false);
		button_update.setFocusPainted(false);
		button_delete.setFocusPainted(false);
		button_return.setFocusPainted(false);

		// 设置窗体
		frame.setTitle("用户修改");// 窗体标签
		frame.setSize(595, 471);// 窗体大小
		frame.setLocationRelativeTo(null);// 在屏幕中间显示(居中显示)
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 退出关闭JFrame
		frame.setVisible(true);// 显示窗体

		button_insert.addActionListener(new ActionListener() {
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
					String sql = "INSERT INTO userinfo (username,password,level) VALUES(?,?,?)";
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setString(1, username);
					ps.setString(2, pwd);
					ps.setInt(3, level);
					int res = ps.executeUpdate();
					if(res > 0){
						JOptionPane.showMessageDialog(null, "数据插入成功!", "成功",
							JOptionPane.INFORMATION_MESSAGE);
					}else{
						JOptionPane.showMessageDialog(null, "数据插入失败!", "失败",
								JOptionPane.INFORMATION_MESSAGE);
					}
					conn.close();
					frame.setVisible(false);
					new UserChange();
				} catch (SQLException ex) {
					System.err.println("SQLException: " + ex.getMessage());
				}
			}

		});

		button_update.addActionListener(new ActionListener() {
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
					String sql = "UPDATE userinfo SET username=?, password=?,level=? WHERE username=?";
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setString(1, username);
					ps.setString(2, pwd);
					ps.setInt(3, level);
					ps.setString(4, username);
					int res = ps.executeUpdate();
					if(res > 0){
						JOptionPane.showMessageDialog(null, "数据更新成功!", "成功",
							JOptionPane.INFORMATION_MESSAGE);
					}else{
						JOptionPane.showMessageDialog(null, "数据更新失败!", "失败",
								JOptionPane.INFORMATION_MESSAGE);
					}
					conn.close();
					frame.setVisible(false);
					new UserChange();
				} catch (SQLException ex) {
					System.err.println("SQLException: " + ex.getMessage());
				}
			}
		});
		button_return.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		button_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Statement stmt = null;
				int level = 0;
				String username = textfield.getText();
				if (username.equals("")) {
					JOptionPane.showMessageDialog(null, "账号是必填项!", "错误",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				try {
					Connection conn = SQLConnection.getConn(); // 数据库连接
					stmt = conn.createStatement();  
					String sql = "DELETE FROM userinfo WHERE username='"+username+"'";
					PreparedStatement ps = conn.prepareStatement(sql);
					int res = ps.executeUpdate();
					if(res > 0){
						JOptionPane.showMessageDialog(null, "数据删除成功!", "成功",
							JOptionPane.INFORMATION_MESSAGE);
					}else{
						JOptionPane.showMessageDialog(null, "数据删除失败!", "失败",
								JOptionPane.INFORMATION_MESSAGE);
					}
					conn.close();
					frame.setVisible(false);
					new UserChange();
				} catch (SQLException ex) {
					System.err.println("SQLException: " + ex.getMessage());
				}
			}
		});

		button_return.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new AdminLogin();
			}
		});
	}

	public void actionPerformed(ActionEvent e) {

	}

	public static void main(String[] args) {
		new UserChange();
	}
}