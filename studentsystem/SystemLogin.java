package studentsystem;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.sql.*;

import studentsystem.SQLConnection;

public class SystemLogin extends JFrame implements ActionListener {
	// {
	// try
	// {
	// UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	// //当前系统
	// }
	// catch(Exception e)
	// {
	// System.out.println(e);
	// }
	// }
	// 定义组件
	JFrame frame = new JFrame();
	JPanel JPanel1, JPanel2, JPanel3, JPanel4, JPanel5, JPanel6;// 面板
	JLabel Label_a, Label_b, Label_user, Label_password;// 标签
	JButton button_register, button_login, button_change, button_exit;// 按钮
	JTextField textfield;
	JPasswordField passwordfield;
	String sqluser, sqlpwd;
	int level, sqllevel;

	// 构造函数
	public SystemLogin() {
		// 创建面板
		JPanel1 = new JPanel();
		JPanel2 = new JPanel();
		JPanel3 = new JPanel();
		JPanel4 = new JPanel();
		JPanel5 = new JPanel();
		JPanel6 = new JPanel();
		// 创建标签
		Label_a = new JLabel("欢迎使用学生信息管理系统!");
		Label_user = new JLabel("请输入账号: ");
		Label_password = new JLabel("请输入密码: ");
		Label_b = new JLabel("请选择身份信息: ");

		String[] list = new String[] { "学生", "教师", "管理员" };
		final JComboBox<String> comboBox = new JComboBox<String>(list);
		comboBox.setSelectedIndex(0);
		// 创建按钮
		button_login = new JButton("登录");
		button_register = new JButton("注册");
		button_change = new JButton("修改密码");
		button_exit = new JButton("退出");
		// 创建文本框
		textfield = new JTextField(12);
		passwordfield = new JPasswordField(12);
		// passwordfield.setEchoChar('*'); // 将密码显示为*

		// 设置布局管理
		frame.setLayout(new GridLayout(6, 1)); // 网格式布局

		// 加入各个组件
		JPanel1.add(Label_a);

		JPanel2.add(Label_user);
		JPanel2.add(textfield);
		JPanel3.add(Label_password);
		JPanel3.add(passwordfield);
		JPanel4.add(Label_b);
		JPanel4.add(comboBox);
		JPanel5.add(button_login);
		JPanel5.add(button_register);
		JPanel5.add(button_change);
		JPanel5.add(button_exit);

		// 加入到JFrame
		frame.add(JPanel1);
		frame.add(JPanel2);
		frame.add(JPanel3);
		frame.add(JPanel4);
		frame.add(JPanel5);
		frame.add(JPanel6);

		// 设置字体
		Font font = new Font("微软雅黑", 1, 14);
		Label_user.setFont(font);
		Label_password.setFont(font);
		Label_a.setFont(new Font("微软雅黑", 1, 21));
		Label_b.setFont(new Font("微软雅黑", 1, 13));
		button_login.setFont(font);
		button_register.setFont(font);
		button_change.setFont(font);
		button_exit.setFont(font);
		comboBox.setFont(font);
		// 设置按钮大小
		button_login.setPreferredSize(new Dimension(200, 30));
		button_register.setPreferredSize(new Dimension(200, 30));
		button_change.setPreferredSize(new Dimension(200, 30));
		button_exit.setPreferredSize(new Dimension(200, 30));
		// 取消按钮焦点框
		button_login.setFocusPainted(false);
		button_register.setFocusPainted(false);
		button_change.setFocusPainted(false);
		button_exit.setFocusPainted(false);

		// 设置窗体
		frame.setTitle("登录页面");// 窗体标签
		frame.setSize(595, 471);// 窗体大小
		frame.setLocationRelativeTo(null);// 在屏幕中间显示(居中显示)
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 退出关闭JFrame
		frame.setVisible(true);// 显示窗体

		button_login.addActionListener(new ActionListener() {
			private boolean flag;

			public void actionPerformed(ActionEvent e) {
				int index = comboBox.getSelectedIndex(); // 身份等级下标
				Statement stmt = null;
				String username = textfield.getText().toString();
				char[] pwd = passwordfield.getPassword();
				String password = String.valueOf(pwd); // 转换为字符串型
				try {
					Connection conn = SQLConnection.getConn(); // 数据库连接
					stmt = conn.createStatement();
					String sql = "SELECT * FROM userinfo WHERE username= '"
							+ username + "'";
					ResultSet rs = stmt.executeQuery(sql);
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

					if (!rs.next()) {
						JOptionPane.showMessageDialog(null, "账号不存在,请先注册!",
								"错误", JOptionPane.ERROR_MESSAGE);
						return;
					} else {
						rs.beforeFirst();
					}
					while (rs.next()) {
						sqlpwd = rs.getString("password");
						sqllevel = rs.getInt("level");
						if (!password.equals(sqlpwd)) {
							JOptionPane.showMessageDialog(null, "密码错误!", "错误",
									JOptionPane.ERROR_MESSAGE);
						}
						if (index == 0) {
							level = 3;
						}
						if (index == 1) {
							level = 2;
						}
						if (index == 2) {
							level = 1;
						}
						if (level != sqllevel) {
							JOptionPane.showMessageDialog(null,
									"你选择的身份信息与注册时不符!", "错误",
									JOptionPane.ERROR_MESSAGE);
						} else {
							if (level == 1) {
								JOptionPane.showMessageDialog(null,
										"管理员用户登录成功!", "成功",
										JOptionPane.INFORMATION_MESSAGE);
								frame.setVisible(false);
								new AdminLogin();
							}
							if (level == 2) {
								JOptionPane.showMessageDialog(null,
										"教师用户登录成功!", "成功",
										JOptionPane.INFORMATION_MESSAGE);
								frame.setVisible(false);
								new TeacherLogin();
							}
							if (level == 3) {
								JOptionPane.showMessageDialog(null, "学生用户登录成功!",
										"成功", JOptionPane.INFORMATION_MESSAGE);
								frame.setVisible(false);
								new UserLogin();
							}
						}
					}
				} catch (SQLException ex) {
					System.err.println("SQLException: " + ex.getMessage());
				}
			}

		});

		button_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new Register();
			}
		});

		button_change.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new ChangePwd();
			}
		});

		button_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispatchEvent(new WindowEvent(frame,
						WindowEvent.WINDOW_CLOSING)); // 退出程序
			}
		});

	}

	public void actionPerformed(ActionEvent e) {

	}

	public static void main(String[] args) {
		new SystemLogin();
	}
}