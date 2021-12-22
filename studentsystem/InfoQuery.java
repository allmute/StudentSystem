package studentsystem;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.sql.*;
import java.sql.Date;
import java.util.*;

import studentsystem.SQLConnection;

public class InfoQuery extends JFrame implements ActionListener {

	// �������
	JFrame frame = new JFrame();
	JPanel JPanel1, JPanel2, JPanel3, JPanel4, JPanel5, JPanel6, JPanel7,
			JPanel8;// ���
	JLabel Label_a, Label_b;// ��ǩ
	JButton button_stuinfo, button_courseinfo, button_classinfo, button_userinfo, button_return, button_exit;// ��ť

	// ���캯��
	public InfoQuery() {
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
		Label_a = new JLabel("��ӭʹ����Ϣ��ѯ����!");
		Label_b = new JLabel("��ѡ����Ҫ��ѯ����Ϣ����!");

		// ������ť
		button_stuinfo = new JButton("ѧ����Ϣ��ѯ");
		button_courseinfo = new JButton("�γ���Ϣ��ѯ");
		button_classinfo = new JButton("�༶��Ϣ��ѯ");
		button_userinfo = new JButton("һ���û���ѯ");
		button_return = new JButton("����");
		button_exit = new JButton("�˳�");

		// ���ò��ֹ���
		frame.setLayout(new GridLayout(8, 1)); // ����ʽ����

		// ����������
		JPanel1.add(Label_a);
		JPanel2.add(Label_b);
		JPanel3.add(button_stuinfo);
		JPanel4.add(button_courseinfo);
		JPanel5.add(button_classinfo);
		JPanel6.add(button_userinfo);
		JPanel7.add(button_return);
//		JPanel7.add(button_exit);

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
		button_stuinfo.setFont(font);
		button_courseinfo.setFont(font);
		button_classinfo.setFont(font);
		button_userinfo.setFont(font);
		button_return.setFont(font);
//		button_exit.setFont(font);
		// ���ð�ť��С
		button_stuinfo.setPreferredSize(new Dimension(200, 30));
		button_courseinfo.setPreferredSize(new Dimension(200, 30));
		button_classinfo.setPreferredSize(new Dimension(200, 30));
		button_userinfo.setPreferredSize(new Dimension(200, 30));
		button_return.setPreferredSize(new Dimension(200, 30));
//		button_exit.setPreferredSize(new Dimension(97, 30));

		// ȡ����ť�����
		button_stuinfo.setFocusPainted(false);
		button_courseinfo.setFocusPainted(false);
		button_classinfo.setFocusPainted(false);
		button_userinfo.setFocusPainted(false);
		button_return.setFocusPainted(false);
//		button_exit.setFocusPainted(false);

		// ���ô���
		frame.setTitle("��Ϣ��ѯ");// �����ǩ
		frame.setSize(595, 471);// �����С
		frame.setLocationRelativeTo(null);// ����Ļ�м���ʾ(������ʾ)
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// �˳��ر�JFrame
		frame.setVisible(true);// ��ʾ����

		button_stuinfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				frame.setVisible(false);  //����ҳ��
				new StudentQuery();

			}

		});

		button_courseinfo.addActionListener(new ActionListener() {  //�γ���Ϣ��ѯ
			public void actionPerformed(ActionEvent e) {
//				frame.setVisible(false);  //����ҳ��
				new CourseQuery();
			}

		});
		
		button_classinfo.addActionListener(new ActionListener() {  //�༶��Ϣ��ѯ
			public void actionPerformed(ActionEvent e) {
//				frame.setVisible(false);  //����ҳ��
				new ClassQuery();

			}

		});
		
		button_userinfo.addActionListener(new ActionListener() {  //һ���û���Ϣ��ѯ
			public void actionPerformed(ActionEvent e) {
//				frame.setVisible(false);  //����ҳ��
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
//						WindowEvent.WINDOW_CLOSING)); // �˳�����
//			}
//		});
	}

	public void actionPerformed(ActionEvent e) {

	}

	public static void main(String[] args) {
		new InfoQuery();
	}
}