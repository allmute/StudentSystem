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

public class ChangePwd extends JFrame implements ActionListener {
	// �������
	JFrame frame = new JFrame();
	JPanel JPanel1, JPanel2, JPanel3, JPanel4, JPanel5, JPanel6, JPanel7;// ���
	JLabel Label_a, Label_user, Label_passworda, Label_passwordb;// ��ǩ
	JButton button_register, button_return;// ��ť
	JTextField textfield;
	JPasswordField passwordfield_a, passwordfield_b;

	// ���캯��
	public ChangePwd() {
		// �������
		JPanel1 = new JPanel();
		JPanel2 = new JPanel();
		JPanel3 = new JPanel();
		JPanel4 = new JPanel();
		JPanel5 = new JPanel();
		JPanel6 = new JPanel();
		JPanel7 = new JPanel();
		// ������ǩ
		Label_a = new JLabel("��ӭʹ���޸����빦��!");
		Label_user = new JLabel("�������û��˺�: ");
		Label_passworda = new JLabel("�������û�����: ");
		Label_passwordb = new JLabel("���ٴ���������: ");
		// ������ť
		button_register = new JButton("�޸�");
		button_return = new JButton("����");
		// �����ı���
		textfield = new JTextField(12);
		passwordfield_a = new JPasswordField(12);
		passwordfield_b = new JPasswordField(12);
		// passwordfield.setEchoChar('*'); // ��������ʾΪ*

		// ���ò��ֹ���
		frame.setLayout(new GridLayout(6, 1)); // ����ʽ����

		// ����������
		JPanel1.add(Label_a);

		JPanel2.add(Label_user);
		JPanel2.add(textfield);

		JPanel3.add(Label_passworda);
		JPanel3.add(passwordfield_a);

		JPanel4.add(Label_passwordb);
		JPanel4.add(passwordfield_b);


		JPanel5.add(button_register);
		JPanel5.add(button_return);

		// ���뵽JFrame
		frame.add(JPanel1);
		frame.add(JPanel2);
		frame.add(JPanel3);
		frame.add(JPanel4);
		frame.add(JPanel5);
		frame.add(JPanel6);
//		frame.add(JPanel7);

		// ��������
		Font font = new Font("΢���ź�", 1, 14);
		Label_user.setFont(font);
		Label_passworda.setFont(font);
		Label_passwordb.setFont(font);
		Label_a.setFont(new Font("΢���ź�", 1, 21));
		button_register.setFont(font);
		button_return.setFont(font);
		// ���ð�ť��С
		button_register.setPreferredSize(new Dimension(200, 30));
		button_return.setPreferredSize(new Dimension(200, 30));
		// ȡ����ť�����
		button_register.setFocusPainted(false);
		button_return.setFocusPainted(false);

		// ���ô���
		frame.setTitle("�����޸�");// �����ǩ
		frame.setSize(595, 471);// �����С
		frame.setLocationRelativeTo(null);// ����Ļ�м���ʾ(������ʾ)
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// �˳��ر�JFrame
		frame.setVisible(true);// ��ʾ����
		;

		button_register.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent e) {
				Statement stmt = null;
				int level = 0;
				String username = textfield.getText();
				char[] password = passwordfield_a.getPassword();
				char[] confirmpassword = passwordfield_b.getPassword();
				String pwd = String.valueOf(password); // ת����������ΪString
				
				if (username.equals("")) {
					JOptionPane.showMessageDialog(null, "�˺��Ǳ�����!", "����",JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (String.valueOf(password).equals("")) {
					JOptionPane.showMessageDialog(null, "�����Ǳ�����!", "����",JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (!String.valueOf(password).equals(
						String.valueOf(confirmpassword))) {
					JOptionPane.showMessageDialog(null, "�����������벻һ��!", "����",JOptionPane.ERROR_MESSAGE);
					return;
				}

				try {
					Connection conn = SQLConnection.getConn(); // ���ݿ�����
					String sql = "UPDATE userinfo SET password=? WHERE username=?";
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setString(1, pwd);
					ps.setString(2, username);
					int res = ps.executeUpdate();
					if(res > 0){
						JOptionPane.showMessageDialog(null, "�����޸ĳɹ�!", "�ɹ�",
							JOptionPane.INFORMATION_MESSAGE);
					}else{
						JOptionPane.showMessageDialog(null, "�����޸�ʧ��!", "ʧ��",
								JOptionPane.INFORMATION_MESSAGE);
					}
					conn.close();
					frame.setVisible(false);
					new SystemLogin();
				} catch (SQLException ex) {
					System.err.println("SQLException: " + ex.getMessage());
				}
			}

		});

		button_return.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new SystemLogin();
			}
		});
	}

	public void actionPerformed(ActionEvent e) {

	}

	public static void main(String[] args) {
		new ChangePwd();
	}
}