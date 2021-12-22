package studentsystem;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.sql.*;
import java.sql.Date;
import java.util.*;

import studentsystem.SQLConnection;

public class TeacherLogin extends JFrame implements ActionListener {

	// 定义组件
	JFrame frame = new JFrame();
	JPanel JPanel1, JPanel2, JPanel3, JPanel4, JPanel5, JPanel6, JPanel7,
			JPanel8;// 面板
	JLabel Label_a, Label_b;// 标签
	JButton button_grademanage, button_student, button_course, button_grade,
			button_return, button_exit;// 按钮

	// 构造函数
	public TeacherLogin() {
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
		Label_a = new JLabel("欢迎登录学生系统教师账户!");
		Label_b = new JLabel("提示:请选择你要进行的操作!");

		// 创建按钮
		button_grademanage = new JButton("成绩管理");
		button_student = new JButton("学生信息查询");
		button_course = new JButton("课程信息查询");
		button_grade = new JButton("成绩信息查询");
		button_return = new JButton("返回");
		button_exit = new JButton("退出");

		// 设置布局管理
		frame.setLayout(new GridLayout(8, 1)); // 网格式布局

		// 加入各个组件
		JPanel1.add(Label_a);
		JPanel2.add(Label_b);

		JPanel3.add(button_grademanage);
		JPanel4.add(button_student);
		JPanel5.add(button_course);
		JPanel6.add(button_grade);
		JPanel7.add(button_return);
		JPanel7.add(button_exit);

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
		button_grademanage.setFont(font);
		button_student.setFont(font);
		button_course.setFont(font);
		button_grade.setFont(font);
		button_return.setFont(font);
		button_exit.setFont(font);
		// 设置按钮大小
		button_grademanage.setPreferredSize(new Dimension(200, 30));
		button_student.setPreferredSize(new Dimension(200, 30));
		button_course.setPreferredSize(new Dimension(200, 30));
		button_grade.setPreferredSize(new Dimension(200, 30));
		button_return.setPreferredSize(new Dimension(97, 30));
		button_exit.setPreferredSize(new Dimension(97, 30));

		// 取消按钮焦点框
		button_grademanage.setFocusPainted(false);
		button_student.setFocusPainted(false);
		button_course.setFocusPainted(false);
		button_grade.setFocusPainted(false);
		button_return.setFocusPainted(false);
		button_exit.setFocusPainted(false);
		// 设置窗体
		frame.setTitle("教师页面");// 窗体标签
		frame.setSize(595, 471);// 窗体大小
		frame.setLocationRelativeTo(null);// 在屏幕中间显示(居中显示)
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 退出关闭JFrame
		frame.setVisible(true);// 显示窗体

		button_grademanage.addActionListener(new ActionListener() {  //成绩管理(录入和修改)
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new GradeChange();

			}

		});

		button_student.addActionListener(new ActionListener() {  //学生信息查询
			public void actionPerformed(ActionEvent e) {
//				frame.setVisible(false);  //隐藏页面
				new StudentQuery();
			}

		});

		button_course.addActionListener(new ActionListener() {  //课程信息查询
			public void actionPerformed(ActionEvent e) {
//				frame.setVisible(false);  //隐藏页面
				new CourseQuery();

			}

		});

		button_grade.addActionListener(new ActionListener() {  //成绩信息查询
			public void actionPerformed(ActionEvent e) {
//				frame.setVisible(false);  //隐藏页面
				new GradeQuery();

			}

		});
		
		button_return.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SystemLogin();
				frame.setVisible(false);

			}

		});
		
		button_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}

	public void actionPerformed(ActionEvent e) {

	}

	public static void main(String[] args) {
		new TeacherLogin();
	}
}