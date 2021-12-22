package studentsystem;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.sql.*;
import java.sql.Date;
import java.util.*;

import studentsystem.SQLConnection;

public class TeacherLogin extends JFrame implements ActionListener {

	// �������
	JFrame frame = new JFrame();
	JPanel JPanel1, JPanel2, JPanel3, JPanel4, JPanel5, JPanel6, JPanel7,
			JPanel8;// ���
	JLabel Label_a, Label_b;// ��ǩ
	JButton button_grademanage, button_student, button_course, button_grade,
			button_return, button_exit;// ��ť

	// ���캯��
	public TeacherLogin() {
		// �������
		JPanel1 = new JPanel();
		JPanel2 = new JPanel();
		JPanel3 = new JPanel();
		JPanel4 = new JPanel();
		JPanel5 = new JPanel();
		JPanel6 = new JPanel();
		JPanel7 = new JPanel();
		JPanel8 = new JPanel();
		// ������ǩ
		Label_a = new JLabel("��ӭ��¼ѧ��ϵͳ��ʦ�˻�!");
		Label_b = new JLabel("��ʾ:��ѡ����Ҫ���еĲ���!");

		// ������ť
		button_grademanage = new JButton("�ɼ�����");
		button_student = new JButton("ѧ����Ϣ��ѯ");
		button_course = new JButton("�γ���Ϣ��ѯ");
		button_grade = new JButton("�ɼ���Ϣ��ѯ");
		button_return = new JButton("����");
		button_exit = new JButton("�˳�");

		// ���ò��ֹ���
		frame.setLayout(new GridLayout(8, 1)); // ����ʽ����

		// ����������
		JPanel1.add(Label_a);
		JPanel2.add(Label_b);

		JPanel3.add(button_grademanage);
		JPanel4.add(button_student);
		JPanel5.add(button_course);
		JPanel6.add(button_grade);
		JPanel7.add(button_return);
		JPanel7.add(button_exit);

		// ���뵽JFrame
		frame.add(JPanel1);
		frame.add(JPanel2);
		frame.add(JPanel3);
		frame.add(JPanel4);
		frame.add(JPanel5);
		frame.add(JPanel6);
		frame.add(JPanel7);
		frame.add(JPanel8);

		// ��������
		Font font = new Font("΢���ź�", 1, 14);
		Label_a.setFont(new Font("΢���ź�", 1, 21));
		Label_b.setFont(new Font("΢���ź�", 1, 13));
		button_grademanage.setFont(font);
		button_student.setFont(font);
		button_course.setFont(font);
		button_grade.setFont(font);
		button_return.setFont(font);
		button_exit.setFont(font);
		// ���ð�ť��С
		button_grademanage.setPreferredSize(new Dimension(200, 30));
		button_student.setPreferredSize(new Dimension(200, 30));
		button_course.setPreferredSize(new Dimension(200, 30));
		button_grade.setPreferredSize(new Dimension(200, 30));
		button_return.setPreferredSize(new Dimension(97, 30));
		button_exit.setPreferredSize(new Dimension(97, 30));

		// ȡ����ť�����
		button_grademanage.setFocusPainted(false);
		button_student.setFocusPainted(false);
		button_course.setFocusPainted(false);
		button_grade.setFocusPainted(false);
		button_return.setFocusPainted(false);
		button_exit.setFocusPainted(false);
		// ���ô���
		frame.setTitle("��ʦҳ��");// �����ǩ
		frame.setSize(595, 471);// �����С
		frame.setLocationRelativeTo(null);// ����Ļ�м���ʾ(������ʾ)
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// �˳��ر�JFrame
		frame.setVisible(true);// ��ʾ����

		button_grademanage.addActionListener(new ActionListener() {  //�ɼ�����(¼����޸�)
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new GradeChange();

			}

		});

		button_student.addActionListener(new ActionListener() {  //ѧ����Ϣ��ѯ
			public void actionPerformed(ActionEvent e) {
//				frame.setVisible(false);  //����ҳ��
				new StudentQuery();
			}

		});

		button_course.addActionListener(new ActionListener() {  //�γ���Ϣ��ѯ
			public void actionPerformed(ActionEvent e) {
//				frame.setVisible(false);  //����ҳ��
				new CourseQuery();

			}

		});

		button_grade.addActionListener(new ActionListener() {  //�ɼ���Ϣ��ѯ
			public void actionPerformed(ActionEvent e) {
//				frame.setVisible(false);  //����ҳ��
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