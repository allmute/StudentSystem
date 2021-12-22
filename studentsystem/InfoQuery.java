package studentsystem;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.sql.*;
import java.sql.Date;
import java.util.*;

import studentsystem.SQLConnection;

public class InfoQuery extends JFrame implements ActionListener {

	// 定义组件
	JFrame frame = new JFrame();
	JPanel JPanel1, JPanel2, JPanel3, JPanel4, JPanel5, JPanel6, JPanel7,
			JPanel8;// 面板
	JLabel Label_a, Label_b;// 标签
	JButton button_stuinfo, button_courseinfo, button_classinfo, button_userinfo, button_return, button_exit;// 按钮

	// 构造函数
	public InfoQuery() {
		// 创建面板
		JPanel1 = new JPanel();
		JPanel2 = new JPanel();
		JPanel3 = new JPanel();
		JPanel4 = new JPanel();
		JPanel5 = new JPanel();
		JPanel6 = new JPanel();
		JPanel7 = new JPanel();
		JPanel8 = new JPanel();
		// 创建标签
		Label_a = new JLabel("欢迎使用信息查询功能!");
		Label_b = new JLabel("请选择你要查询的信息类型!");

		// 创建按钮
		button_stuinfo = new JButton("学生信息查询");
		button_courseinfo = new JButton("课程信息查询");
		button_classinfo = new JButton("班级信息查询");
		button_userinfo = new JButton("一般用户查询");
		button_return = new JButton("返回");
		button_exit = new JButton("退出");

		// 设置布局管理
		frame.setLayout(new GridLayout(8, 1)); // 网格式布局

		// 加入各个组件
		JPanel1.add(Label_a);
		JPanel2.add(Label_b);
		JPanel3.add(button_stuinfo);
		JPanel4.add(button_courseinfo);
		JPanel5.add(button_classinfo);
		JPanel6.add(button_userinfo);
		JPanel7.add(button_return);
//		JPanel7.add(button_exit);

		// 加入到JFrame
		frame.add(JPanel1);
		frame.add(JPanel2);
		frame.add(JPanel3);
		frame.add(JPanel4);
		frame.add(JPanel5);
		frame.add(JPanel6);
		frame.add(JPanel7);
		frame.add(JPanel8);

		// 设置字体
		Font font = new Font("微软雅黑", 1, 14);
		Label_a.setFont(new Font("微软雅黑", 1, 21));
		Label_b.setFont(new Font("微软雅黑", 1, 13));
		button_stuinfo.setFont(font);
		button_courseinfo.setFont(font);
		button_classinfo.setFont(font);
		button_userinfo.setFont(font);
		button_return.setFont(font);
//		button_exit.setFont(font);
		// 设置按钮大小
		button_stuinfo.setPreferredSize(new Dimension(200, 30));
		button_courseinfo.setPreferredSize(new Dimension(200, 30));
		button_classinfo.setPreferredSize(new Dimension(200, 30));
		button_userinfo.setPreferredSize(new Dimension(200, 30));
		button_return.setPreferredSize(new Dimension(200, 30));
//		button_exit.setPreferredSize(new Dimension(97, 30));

		// 取消按钮焦点框
		button_stuinfo.setFocusPainted(false);
		button_courseinfo.setFocusPainted(false);
		button_classinfo.setFocusPainted(false);
		button_userinfo.setFocusPainted(false);
		button_return.setFocusPainted(false);
//		button_exit.setFocusPainted(false);

		// 设置窗体
		frame.setTitle("信息查询");// 窗体标签
		frame.setSize(595, 471);// 窗体大小
		frame.setLocationRelativeTo(null);// 在屏幕中间显示(居中显示)
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 退出关闭JFrame
		frame.setVisible(true);// 显示窗体

		button_stuinfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				frame.setVisible(false);  //隐藏页面
				new StudentQuery();

			}

		});

		button_courseinfo.addActionListener(new ActionListener() {  //课程信息查询
			public void actionPerformed(ActionEvent e) {
//				frame.setVisible(false);  //隐藏页面
				new CourseQuery();
			}

		});
		
		button_classinfo.addActionListener(new ActionListener() {  //班级信息查询
			public void actionPerformed(ActionEvent e) {
//				frame.setVisible(false);  //隐藏页面
				new ClassQuery();

			}

		});
		
		button_userinfo.addActionListener(new ActionListener() {  //一般用户信息查询
			public void actionPerformed(ActionEvent e) {
//				frame.setVisible(false);  //隐藏页面
				new UserQuery();

			}

		});
		
		button_return.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AdminLogin();
				frame.setVisible(false);

			}

		});
//		button_exit.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				frame.dispatchEvent(new WindowEvent(frame,
//						WindowEvent.WINDOW_CLOSING)); // 退出程序
//			}
//		});
	}

	public void actionPerformed(ActionEvent e) {

	}

	public static void main(String[] args) {
		new InfoQuery();
	}
}