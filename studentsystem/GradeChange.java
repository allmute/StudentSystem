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

	// �������
	JFrame frame = new JFrame();
	JPanel JPanel1, JPanel2, JPanel3, JPanel4, JPanel5, JPanel6, JPanel7,
			JPanel8, JPanel9;// ���
	JLabel Label_a, Label_b, Label_id, Label_classid, Label_gender;// ��ǩ
	JButton button_insert, button_update, button_return;// ��ť
	JTextField textfield_id, textfield_classid, textfield_grade;

	// ���캯��
	public GradeChange() {
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
		Label_a = new JLabel("��ӭʹ�óɼ��޸Ĺ���!");
		Label_b = new JLabel("������ѧ�Ž�����ز���");
		Label_id = new JLabel("������ѧ��ѧ��: ");
		Label_classid = new JLabel("������γ̱��:");
		Label_gender = new JLabel("������ѧ���ɼ�:");

		// ������ť
		button_insert = new JButton("�ɼ�¼��");
		button_update = new JButton("�ɼ��޸�");
		button_return = new JButton("����");
		// �����ı���
		textfield_id = new JTextField(12);
		textfield_classid = new JTextField(12);
		textfield_grade = new JTextField(12);

		// ���ò��ֹ���
		frame.setLayout(new GridLayout(9, 1)); // ����ʽ����

		// ����������
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
		Label_id.setFont(font);
		Label_classid.setFont(font);
		Label_gender.setFont(font);
		Label_a.setFont(new Font("΢���ź�", 1, 21));
		Label_b.setFont(new Font("΢���ź�", 1, 13));

		button_insert.setFont(font);
		button_update.setFont(font);
		button_return.setFont(font);
		// ���ð�ť��С
		button_insert.setPreferredSize(new Dimension(243, 30));
		button_update.setPreferredSize(new Dimension(243, 30));
		button_return.setPreferredSize(new Dimension(243, 30));
		// ȡ����ť�����
		button_insert.setFocusPainted(false);
		button_update.setFocusPainted(false);
		button_return.setFocusPainted(false);

		// ���ô���
		frame.setTitle("�û��޸�");// �����ǩ
		frame.setSize(595, 471);// �����С
		frame.setLocationRelativeTo(null);// ����Ļ�м���ʾ(������ʾ)
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// �˳��ر�JFrame
		frame.setVisible(true);// ��ʾ����

		button_insert.addActionListener(new ActionListener() {  //�ɼ�¼��
			public void actionPerformed(ActionEvent e) {
				Statement stmt = null;
				String id = textfield_id.getText();
				String classid = textfield_classid.getText();
				String grade = textfield_grade.getText();

				if (id.equals("")) {
					JOptionPane.showMessageDialog(null, "ѧ���Ǳ�����!", "����",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (classid.equals("")) {
					JOptionPane.showMessageDialog(null, "�γ̱���Ǳ�����!", "����",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				try {
					Connection conn = SQLConnection.getConn(); // ���ݿ�����
					stmt = conn.createStatement();
					String sql = "INSERT INTO sc (id,classid,grade) VALUES(?,?,?)";
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setString(1, id);
					ps.setString(2, classid);
					ps.setString(3, grade);
					int res = ps.executeUpdate();
					if(res > 0){
						JOptionPane.showMessageDialog(null, "�ɼ�¼��ɹ�!", "�ɹ�",
							JOptionPane.INFORMATION_MESSAGE);
					}else{
						JOptionPane.showMessageDialog(null, "�ɼ�¼��ʧ��!", "ʧ��",
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

		button_update.addActionListener(new ActionListener() {  //�ɼ��޸�
			public void actionPerformed(ActionEvent e) {
				Statement stmt = null;
				String id = textfield_id.getText();
				String classid = textfield_classid.getText();
				String grade = textfield_grade.getText();

				if (id.equals("")) {
					JOptionPane.showMessageDialog(null, "ѧ���Ǳ�����!", "����",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (classid.equals("")) {
					JOptionPane.showMessageDialog(null, "�γ̱���Ǳ�����!", "����",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				try {
					Connection conn = SQLConnection.getConn(); // ���ݿ�����
					stmt = conn.createStatement();
					String sql = "UPDATE sc SET grade=? WHERE id=? and classid =?";
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setString(1, grade);
					ps.setString(2, id);
					ps.setString(3, classid);
					int res = ps.executeUpdate();
					if(res > 0){
						JOptionPane.showMessageDialog(null, "���ݸ��³ɹ�!", "�ɹ�",
							JOptionPane.INFORMATION_MESSAGE);
					}else{
						JOptionPane.showMessageDialog(null, "���ݸ���ʧ��!", "ʧ��",
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
		button_return.addActionListener(new ActionListener() { //����
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