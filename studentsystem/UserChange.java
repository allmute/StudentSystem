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

public class UserChange extends JFrame implements ActionListener {

	// �������
	JFrame frame = new JFrame();
	JPanel JPanel1, JPanel2, JPanel3, JPanel4, JPanel5, JPanel6, JPanel7,
			JPanel8, JPanel9, JPanel10, JPanel11;// ���
	JLabel Label_a, Label_b, Label_c, Label_user, Label_passworda,
			Label_passwordb;// ��ǩ
	JButton button_insert, button_update, button_delete, button_return;// ��ť
	JTextField textfield;
	JPasswordField passwordfield_a, passwordfield_b;

	// ���캯��
	public UserChange() {
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
		JPanel10 = new JPanel();
		JPanel11 = new JPanel();

		// ������ǩ
		Label_a = new JLabel("��ӭʹ���û���Ϣ�޸Ĺ���!");
		Label_b = new JLabel("�������˺Ž�����ز���");
		Label_c = new JLabel("��ѡ�������Ϣ:");
		Label_user = new JLabel("�������û��˺�: ");
		Label_passworda = new JLabel("�������û�����:");
		Label_passwordb = new JLabel("���ٴ���������:");

		// ���������˵�
		String[] list = new String[] { "ѧ��", "��ʦ", "����Ա" };
		final JComboBox<String> comboBox = new JComboBox<String>(list);
		comboBox.setSelectedIndex(0);
		// ������ť
		button_insert = new JButton("����");
		button_update = new JButton("����");
		button_delete = new JButton("ɾ��");
		button_return = new JButton("����");
		// �����ı���
		textfield = new JTextField(12);
		passwordfield_a = new JPasswordField(12);
		passwordfield_b = new JPasswordField(12);
		// passwordfield.setEchoChar('*'); // ��������ʾΪ*

		// ���ò��ֹ���
		frame.setLayout(new GridLayout(11, 1)); // ����ʽ����

		// ����������
		JPanel1.add(Label_a);
		JPanel2.add(Label_b);

		JPanel3.add(Label_user);
		JPanel3.add(textfield);

		JPanel4.add(Label_passworda);
		JPanel4.add(passwordfield_a);

		JPanel5.add(Label_passwordb);
		JPanel5.add(passwordfield_b);
		JPanel6.add(Label_c);
		JPanel6.add(comboBox);

		JPanel7.add(button_insert);
		JPanel8.add(button_update);
		JPanel9.add(button_delete);
		JPanel10.add(button_return);

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
		frame.add(JPanel10);
		frame.add(JPanel11);

		// ��������
		Font font = new Font("΢���ź�", 1, 14);
		Label_user.setFont(font);
		Label_passworda.setFont(font);
		Label_passwordb.setFont(font);
		Label_a.setFont(new Font("΢���ź�", 1, 21));
		Label_b.setFont(new Font("΢���ź�", 1, 13));
		Label_c.setFont(font);
		button_insert.setFont(font);
		button_update.setFont(font);
		button_delete.setFont(font);
		button_return.setFont(font);
		// ���ð�ť��С
		button_insert.setPreferredSize(new Dimension(243, 30));
		button_update.setPreferredSize(new Dimension(243, 30));
		button_delete.setPreferredSize(new Dimension(243, 30));
		button_return.setPreferredSize(new Dimension(243, 30));
		// ȡ����ť�����
		button_insert.setFocusPainted(false);
		button_update.setFocusPainted(false);
		button_delete.setFocusPainted(false);
		button_return.setFocusPainted(false);

		// ���ô���
		frame.setTitle("�û��޸�");// �����ǩ
		frame.setSize(595, 471);// �����С
		frame.setLocationRelativeTo(null);// ����Ļ�м���ʾ(������ʾ)
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// �˳��ر�JFrame
		frame.setVisible(true);// ��ʾ����

		button_insert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Statement stmt = null;
				int index = comboBox.getSelectedIndex();
				int level = 0;
				String username = textfield.getText();
				char[] password = passwordfield_a.getPassword();
				char[] confirmpassword = passwordfield_b.getPassword();
				String pwd = String.valueOf(password); // ת����������ΪString

				if (index == 0) {
					level = 3;
				}
				if (index == 1) {
					level = 2;
				}
				if (index == 2) {
					level = 1;
				}
				if (username.equals("")) {
					JOptionPane.showMessageDialog(null, "�˺��Ǳ�����!", "����",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (String.valueOf(password).equals("")) {
					JOptionPane.showMessageDialog(null, "�����Ǳ�����!", "����",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (!String.valueOf(password).equals(
						String.valueOf(confirmpassword))) {
					JOptionPane.showMessageDialog(null, "�����������벻һ��!", "����",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				try {
					Connection conn = SQLConnection.getConn(); // ���ݿ�����
					stmt = conn.createStatement();
					String sql = "INSERT INTO userinfo (username,password,level) VALUES(?,?,?)";
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setString(1, username);
					ps.setString(2, pwd);
					ps.setInt(3, level);
					int res = ps.executeUpdate();
					if(res > 0){
						JOptionPane.showMessageDialog(null, "���ݲ���ɹ�!", "�ɹ�",
							JOptionPane.INFORMATION_MESSAGE);
					}else{
						JOptionPane.showMessageDialog(null, "���ݲ���ʧ��!", "ʧ��",
								JOptionPane.INFORMATION_MESSAGE);
					}
					conn.close();
					frame.setVisible(false);
					new UserChange();
				} catch (SQLException ex) {
					System.err.println("SQLException: " + ex.getMessage());
				}
			}

		});

		button_update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Statement stmt = null;
				int index = comboBox.getSelectedIndex();
				int level = 0;
				String username = textfield.getText();
				char[] password = passwordfield_a.getPassword();
				char[] confirmpassword = passwordfield_b.getPassword();
				String pwd = String.valueOf(password); // ת����������ΪString

				if (index == 0) {
					level = 3;
				}
				if (index == 1) {
					level = 2;
				}
				if (index == 2) {
					level = 1;
				}
				if (username.equals("")) {
					JOptionPane.showMessageDialog(null, "�˺��Ǳ�����!", "����",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (String.valueOf(password).equals("")) {
					JOptionPane.showMessageDialog(null, "�����Ǳ�����!", "����",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (!String.valueOf(password).equals(
						String.valueOf(confirmpassword))) {
					JOptionPane.showMessageDialog(null, "�����������벻һ��!", "����",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				try {
					Connection conn = SQLConnection.getConn(); // ���ݿ�����
					stmt = conn.createStatement();
					String sql = "UPDATE userinfo SET username=?, password=?,level=? WHERE username=?";
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setString(1, username);
					ps.setString(2, pwd);
					ps.setInt(3, level);
					ps.setString(4, username);
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
					new UserChange();
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
				int level = 0;
				String username = textfield.getText();
				if (username.equals("")) {
					JOptionPane.showMessageDialog(null, "�˺��Ǳ�����!", "����",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				try {
					Connection conn = SQLConnection.getConn(); // ���ݿ�����
					stmt = conn.createStatement();  
					String sql = "DELETE FROM userinfo WHERE username='"+username+"'";
					PreparedStatement ps = conn.prepareStatement(sql);
					int res = ps.executeUpdate();
					if(res > 0){
						JOptionPane.showMessageDialog(null, "����ɾ���ɹ�!", "�ɹ�",
							JOptionPane.INFORMATION_MESSAGE);
					}else{
						JOptionPane.showMessageDialog(null, "����ɾ��ʧ��!", "ʧ��",
								JOptionPane.INFORMATION_MESSAGE);
					}
					conn.close();
					frame.setVisible(false);
					new UserChange();
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
		new UserChange();
	}
}