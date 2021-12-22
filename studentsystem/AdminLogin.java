package studentsystem;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.sql.*;
import java.sql.Date;
import java.util.*;

import studentsystem.SQLConnection;

public class AdminLogin extends JFrame implements ActionListener {

	// �������
	JFrame frame = new JFrame();
	JPanel JPanel1, JPanel2, JPanel3, JPanel4, JPanel5, JPanel6, JPanel7,
			JPanel8 ,JPanel9;// ���
	JLabel Label_a, Label_b;// ��ǩ
	JButton button_inquiry, button_statistics, button_user, button_student,
			button_course, button_return, button_exit;// ��ť

	// ���캯��
	public AdminLogin() {
		// �������
		JPanel1 = new JPanel();
		JPanel2 = new JPanel();
		JPanel3 = new JPanel();
		JPanel4 = new JPanel();
		JPanel5 = new JPanel();
		JPanel6 = new JPanel();
		JPanel7 = new JPanel();
		JPanel8 = new JPanel();
		JPanel9 = new JPanel();
		// ������ǩ
		Label_a = new JLabel("��ӭ��¼ѧ��ϵͳ����Ա�˻�!");
		Label_b = new JLabel("��ѡ����Ҫ���еĲ���!");

		// ������ť
		button_inquiry = new JButton("��Ϣ��ѯ");
		button_statistics = new JButton("�ɼ�ͳ��");
		button_user = new JButton("�û�����");
		button_student = new JButton("ѧ������");
		button_course = new JButton("�γ̹���");
		button_return = new JButton("����");
		button_exit = new JButton("�˳�");

		// ���ò��ֹ���
		frame.setLayout(new GridLayout(9, 1)); // ����ʽ����

		// ����������
		JPanel1.add(Label_a);
		JPanel2.add(Label_b);
		JPanel3.add(button_inquiry);
		JPanel4.add(button_statistics);
		JPanel5.add(button_user);
		JPanel6.add(button_student);
		JPanel7.add(button_course);
		JPanel8.add(button_return);
		JPanel8.add(button_exit);

		// ���뵽JFrame
		frame.add(JPanel1);
		frame.add(JPanel2);
		frame.add(JPanel3);
		frame.add(JPanel4);
		frame.add(JPanel5);
		frame.add(JPanel6);
		frame.add(JPanel7);
		frame.add(JPanel8);
		frame.add(JPanel9);

		// ��������
		Font font = new Font("΢���ź�", 1, 14);
		Label_a.setFont(new Font("΢���ź�", 1, 21));
		Label_b.setFont(new Font("΢���ź�", 1, 13));
		button_inquiry.setFont(font);
		button_statistics.setFont(font);
		button_user.setFont(font);
		button_student.setFont(font);
		button_course.setFont(font);
		button_return.setFont(font);
		button_exit.setFont(font);
		// ���ð�ť��С
		button_inquiry.setPreferredSize(new Dimension(200, 30));
		button_statistics.setPreferredSize(new Dimension(200, 30));
		button_user.setPreferredSize(new Dimension(200, 30));
		button_student.setPreferredSize(new Dimension(200, 30));
		button_course.setPreferredSize(new Dimension(200, 30));
		button_return.setPreferredSize(new Dimension(97, 30));
		button_exit.setPreferredSize(new Dimension(97, 30));

		// ȡ����ť�����
		button_inquiry.setFocusPainted(false);
		button_statistics.setFocusPainted(false);
		button_user.setFocusPainted(false);
		button_student.setFocusPainted(false);
		button_course.setFocusPainted(false);
		button_return.setFocusPainted(false);
		button_exit.setFocusPainted(false);

		// ���ô���
		frame.setTitle("����Աϵͳ");// �����ǩ
		frame.setSize(595, 471);// �����С
		frame.setLocationRelativeTo(null);// ����Ļ�м���ʾ(������ʾ)
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// �˳��ر�JFrame
		frame.setVisible(true);// ��ʾ����

		button_inquiry.addActionListener(new ActionListener() {  //��Ϣ��ѯ
			public void actionPerformed(ActionEvent e) {
				new InfoQuery();
				frame.setVisible(false);

			}

		});
		
		button_statistics.addActionListener(new ActionListener() {  //�ɼ�ͳ��
			public void actionPerformed(ActionEvent e) {
//				frame.setVisible(false);  //���ؽ���
				new GradecourseQuery();
			}

		});
		
		button_user.addActionListener(new ActionListener() {  //�û�����
			public void actionPerformed(ActionEvent e) {
				new UserChange();
				frame.setVisible(false);

			}

		});
		
		button_student.addActionListener(new ActionListener() {  //ѧ������
			public void actionPerformed(ActionEvent e) {
				new StudentChange();
				frame.setVisible(false);

			}

		});
		
		button_course.addActionListener(new ActionListener() {  //�γ̹���
			public void actionPerformed(ActionEvent e) {
				new CourseChange();
				frame.setVisible(false);

			}

		});
		button_return.addActionListener(new ActionListener() {  //����
			public void actionPerformed(ActionEvent e) {
				new SystemLogin();
				frame.setVisible(false);

			}

		});
		button_exit.addActionListener(new ActionListener() {  //�˳�
			public void actionPerformed(ActionEvent e) {
				frame.dispatchEvent(new WindowEvent(frame,
						WindowEvent.WINDOW_CLOSING)); // �˳�����
			}
		});
	}

	public void actionPerformed(ActionEvent e) {

	}

	public static void main(String[] args) {
		new AdminLogin();
	}
}