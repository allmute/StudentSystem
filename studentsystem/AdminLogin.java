package studentsystem;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.sql.*;
import java.sql.Date;
import java.util.*;

import studentsystem.SQLConnection;

public class AdminLogin extends JFrame implements ActionListener {

	// 定义组件
	JFrame frame = new JFrame();
	JPanel JPanel1, JPanel2, JPanel3, JPanel4, JPanel5, JPanel6, JPanel7,
			JPanel8 ,JPanel9;// 面板
	JLabel Label_a, Label_b;// 标签
	JButton button_inquiry, button_statistics, button_user, button_student,
			button_course, button_return, button_exit;// 按钮

	// 构造函数
	public AdminLogin() {
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
		Label_a = new JLabel("欢迎登录学生系统管理员账户!");
		Label_b = new JLabel("请选择你要进行的操作!");

		// 创建按钮
		button_inquiry = new JButton("信息查询");
		button_statistics = new JButton("成绩统计");
		button_user = new JButton("用户管理");
		button_student = new JButton("学生管理");
		button_course = new JButton("课程管理");
		button_return = new JButton("返回");
		button_exit = new JButton("退出");

		// 设置布局管理
		frame.setLayout(new GridLayout(9, 1)); // 网格式布局

		// 加入各个组件
		JPanel1.add(Label_a);
		JPanel2.add(Label_b);
		JPanel3.add(button_inquiry);
		JPanel4.add(button_statistics);
		JPanel5.add(button_user);
		JPanel6.add(button_student);
		JPanel7.add(button_course);
		JPanel8.add(button_return);
		JPanel8.add(button_exit);

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
		Label_a.setFont(new Font("微软雅黑", 1, 21));
		Label_b.setFont(new Font("微软雅黑", 1, 13));
		button_inquiry.setFont(font);
		button_statistics.setFont(font);
		button_user.setFont(font);
		button_student.setFont(font);
		button_course.setFont(font);
		button_return.setFont(font);
		button_exit.setFont(font);
		// 设置按钮大小
		button_inquiry.setPreferredSize(new Dimension(200, 30));
		button_statistics.setPreferredSize(new Dimension(200, 30));
		button_user.setPreferredSize(new Dimension(200, 30));
		button_student.setPreferredSize(new Dimension(200, 30));
		button_course.setPreferredSize(new Dimension(200, 30));
		button_return.setPreferredSize(new Dimension(97, 30));
		button_exit.setPreferredSize(new Dimension(97, 30));

		// 取消按钮焦点框
		button_inquiry.setFocusPainted(false);
		button_statistics.setFocusPainted(false);
		button_user.setFocusPainted(false);
		button_student.setFocusPainted(false);
		button_course.setFocusPainted(false);
		button_return.setFocusPainted(false);
		button_exit.setFocusPainted(false);

		// 设置窗体
		frame.setTitle("管理员系统");// 窗体标签
		frame.setSize(595, 471);// 窗体大小
		frame.setLocationRelativeTo(null);// 在屏幕中间显示(居中显示)
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 退出关闭JFrame
		frame.setVisible(true);// 显示窗体

		button_inquiry.addActionListener(new ActionListener() {  //信息查询
			public void actionPerformed(ActionEvent e) {
				new InfoQuery();
				frame.setVisible(false);

			}

		});
		
		button_statistics.addActionListener(new ActionListener() {  //成绩统计
			public void actionPerformed(ActionEvent e) {
//				frame.setVisible(false);  //隐藏界面
				new GradecourseQuery();
			}

		});
		
		button_user.addActionListener(new ActionListener() {  //用户管理
			public void actionPerformed(ActionEvent e) {
				new UserChange();
				frame.setVisible(false);

			}

		});
		
		button_student.addActionListener(new ActionListener() {  //学生管理
			public void actionPerformed(ActionEvent e) {
				new StudentChange();
				frame.setVisible(false);

			}

		});
		
		button_course.addActionListener(new ActionListener() {  //课程管理
			public void actionPerformed(ActionEvent e) {
				new CourseChange();
				frame.setVisible(false);

			}

		});
		button_return.addActionListener(new ActionListener() {  //返回
			public void actionPerformed(ActionEvent e) {
				new SystemLogin();
				frame.setVisible(false);

			}

		});
		button_exit.addActionListener(new ActionListener() {  //退出
			public void actionPerformed(ActionEvent e) {
				frame.dispatchEvent(new WindowEvent(frame,
						WindowEvent.WINDOW_CLOSING)); // 退出程序
			}
		});
	}

	public void actionPerformed(ActionEvent e) {

	}

	public static void main(String[] args) {
		new AdminLogin();
	}
}