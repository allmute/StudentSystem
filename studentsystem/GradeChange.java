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

public class GradeChange extends JFrame implements ActionListener {

	// 定义组件
	JFrame frame = new JFrame();
	JPanel JPanel1, JPanel2, JPanel3, JPanel4, JPanel5, JPanel6, JPanel7,
			JPanel8, JPanel9;// 面板
	JLabel Label_a, Label_b, Label_id, Label_classid, Label_gender;// 标签
	JButton button_insert, button_update, button_return;// 按钮
	JTextField textfield_id, textfield_classid, textfield_grade;

	// 构造函数
	public GradeChange() {
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

		// 创建标签
		Label_a = new JLabel("欢迎使用成绩修改功能!");
		Label_b = new JLabel("请输入学号进行相关操作");
		Label_id = new JLabel("请输入学生学号: ");
		Label_classid = new JLabel("请输入课程编号:");
		Label_gender = new JLabel("请输入学生成绩:");

		// 创建按钮
		button_insert = new JButton("成绩录入");
		button_update = new JButton("成绩修改");
		button_return = new JButton("返回");
		// 创建文本框
		textfield_id = new JTextField(12);
		textfield_classid = new JTextField(12);
		textfield_grade = new JTextField(12);

		// 设置布局管理
		frame.setLayout(new GridLayout(9, 1)); // 网格式布局

		// 加入各个组件
		JPanel1.add(Label_a);
		JPanel2.add(Label_b);

		JPanel3.add(Label_id);
		JPanel3.add(textfield_id);

		JPanel4.add(Label_classid);
		JPanel4.add(textfield_classid);

		JPanel5.add(Label_gender);
		JPanel5.add(textfield_grade);

		JPanel6.add(button_insert);
		JPanel7.add(button_update);
		JPanel8.add(button_return);

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

		// 设置字体
		Font font = new Font("微软雅黑", 1, 14);
		Label_id.setFont(font);
		Label_classid.setFont(font);
		Label_gender.setFont(font);
		Label_a.setFont(new Font("微软雅黑", 1, 21));
		Label_b.setFont(new Font("微软雅黑", 1, 13));

		button_insert.setFont(font);
		button_update.setFont(font);
		button_return.setFont(font);
		// 设置按钮大小
		button_insert.setPreferredSize(new Dimension(243, 30));
		button_update.setPreferredSize(new Dimension(243, 30));
		button_return.setPreferredSize(new Dimension(243, 30));
		// 取消按钮焦点框
		button_insert.setFocusPainted(false);
		button_update.setFocusPainted(false);
		button_return.setFocusPainted(false);

		// 设置窗体
		frame.setTitle("用户修改");// 窗体标签
		frame.setSize(595, 471);// 窗体大小
		frame.setLocationRelativeTo(null);// 在屏幕中间显示(居中显示)
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 退出关闭JFrame
		frame.setVisible(true);// 显示窗体

		button_insert.addActionListener(new ActionListener() {  //成绩录入
			public void actionPerformed(ActionEvent e) {
				Statement stmt = null;
				String id = textfield_id.getText();
				String classid = textfield_classid.getText();
				String grade = textfield_grade.getText();

				if (id.equals("")) {
					JOptionPane.showMessageDialog(null, "学号是必填项!", "错误",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (classid.equals("")) {
					JOptionPane.showMessageDialog(null, "课程编号是必填项!", "错误",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				try {
					Connection conn = SQLConnection.getConn(); // 数据库连接
					stmt = conn.createStatement();
					String sql = "INSERT INTO sc (id,classid,grade) VALUES(?,?,?)";
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setString(1, id);
					ps.setString(2, classid);
					ps.setString(3, grade);
					int res = ps.executeUpdate();
					if(res > 0){
						JOptionPane.showMessageDialog(null, "成绩录入成功!", "成功",
							JOptionPane.INFORMATION_MESSAGE);
					}else{
						JOptionPane.showMessageDialog(null, "成绩录入失败!", "失败",
								JOptionPane.INFORMATION_MESSAGE);
					}
					conn.close();
					frame.setVisible(false);
					new GradeChange();
				} catch (SQLException ex) {
					System.err.println("SQLException: " + ex.getMessage());
				}
			}

		});

		button_update.addActionListener(new ActionListener() {  //成绩修改
			public void actionPerformed(ActionEvent e) {
				Statement stmt = null;
				String id = textfield_id.getText();
				String classid = textfield_classid.getText();
				String grade = textfield_grade.getText();

				if (id.equals("")) {
					JOptionPane.showMessageDialog(null, "学号是必填项!", "错误",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (classid.equals("")) {
					JOptionPane.showMessageDialog(null, "课程编号是必填项!", "错误",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				try {
					Connection conn = SQLConnection.getConn(); // 数据库连接
					stmt = conn.createStatement();
					String sql = "UPDATE sc SET grade=? WHERE id=? and classid =?";
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setString(1, grade);
					ps.setString(2, id);
					ps.setString(3, classid);
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
					new GradeChange();
				} catch (SQLException ex) {
					System.err.println("SQLException: " + ex.getMessage());
				}
			}
		});
		button_return.addActionListener(new ActionListener() { //返回
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new TeacherLogin();
			}
		});
	}

	public void actionPerformed(ActionEvent e) {

	}

	public static void main(String[] args) {
		new GradeChange();
	}
}