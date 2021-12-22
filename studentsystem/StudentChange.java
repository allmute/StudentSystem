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

public class StudentChange extends JFrame implements ActionListener {

	// 定义组件
	JFrame frame = new JFrame();
	JPanel JPanel1, JPanel2, JPanel3, JPanel4, JPanel5, JPanel6, JPanel7,
			JPanel8, JPanel9, JPanel10, JPanel11;// 面板
	JLabel Label_a, Label_b, Label_id, Label_name, Label_gender, Label_classid;// 标签
	JButton button_insert, button_update, button_delete, button_return;// 按钮
	JTextField textfield_id, textfield_name, textfield_gender,
			textfield_classid;

	// 构造函数
	public StudentChange() {
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
		Label_a = new JLabel("欢迎使用学生信息修改功能!");
		Label_b = new JLabel("请输入学号进行相关操作");
		Label_id = new JLabel("请输入学生学号: ");
		Label_name = new JLabel("请输入学生姓名:");
		Label_gender = new JLabel("请输入学生性别:");
		Label_classid = new JLabel("请输入班级编号:");

		// 创建按钮
		button_insert = new JButton("插入");
		button_update = new JButton("更新");
		button_delete = new JButton("删除");
		button_return = new JButton("返回");
		// 创建文本框
		textfield_id = new JTextField(12);
		textfield_name = new JTextField(12);
		textfield_gender = new JTextField(12);
		textfield_classid = new JTextField(12);

		// 设置布局管理
		frame.setLayout(new GridLayout(11, 1)); // 网格式布局

		// 加入各个组件
		JPanel1.add(Label_a);
		JPanel2.add(Label_b);

		JPanel3.add(Label_id);
		JPanel3.add(textfield_id);

		JPanel4.add(Label_name);
		JPanel4.add(textfield_name);

		JPanel5.add(Label_gender);
		JPanel5.add(textfield_gender);

		JPanel6.add(Label_classid);
		JPanel6.add(textfield_classid);

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
		Label_id.setFont(font);
		Label_name.setFont(font);
		Label_gender.setFont(font);
		Label_classid.setFont(font);
		Label_a.setFont(new Font("微软雅黑", 1, 21));
		Label_b.setFont(new Font("微软雅黑", 1, 13));

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
				String id = textfield_id.getText();
				String name = textfield_name.getText();
				String gender = textfield_gender.getText();
				String classid = textfield_classid.getText();

				if (id.equals("")) {
					JOptionPane.showMessageDialog(null, "学号是必填项!", "错误",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				try {
					Connection conn = SQLConnection.getConn(); // 数据库连接
					stmt = conn.createStatement();
					String sql = "INSERT INTO student (id,name,gender,classid) VALUES(?,?,?,?)";
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setString(1, id);
					ps.setString(2, name);
					ps.setString(3, gender);
					ps.setString(4, classid);
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
					new StudentChange();
				} catch (SQLException ex) {
					System.err.println("SQLException: " + ex.getMessage());
				}
			}

		});

		button_update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Statement stmt = null;
				String id = textfield_id.getText();
				String name = textfield_name.getText();
				String gender = textfield_gender.getText();
				String classid = textfield_classid.getText();

				if (id.equals("")) {
					JOptionPane.showMessageDialog(null, "学号是必填项!", "错误",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				try {
					Connection conn = SQLConnection.getConn(); // 数据库连接
					stmt = conn.createStatement();
					String sql = "UPDATE student SET name=?, gender=?,classid=? WHERE id=?";
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setString(1, name);
					ps.setString(2, gender);
					ps.setString(3, classid);
					ps.setString(4, id);
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
					new StudentChange();
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
				String id = textfield_id.getText();
				if (id.equals("")) {
					JOptionPane.showMessageDialog(null, "学号是必填项!", "错误",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				try {
					Connection conn = SQLConnection.getConn(); // 数据库连接
					stmt = conn.createStatement();
					String sql = "DELETE FROM student WHERE id='" + id
							+ "'";
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
					new StudentChange();
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
		new StudentChange();
	}
}