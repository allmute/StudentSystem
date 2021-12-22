package studentsystem;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.sql.*;

import studentsystem.SQLConnection;

public class SystemLogin extends JFrame implements ActionListener {
	// {
	// try
	// {
	// UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	// //��ǰϵͳ
	// }
	// catch(Exception e)
	// {
	// System.out.println(e);
	// }
	// }
	// �������
	JFrame frame = new JFrame();
	JPanel JPanel1, JPanel2, JPanel3, JPanel4, JPanel5, JPanel6;// ���
	JLabel Label_a, Label_b, Label_user, Label_password;// ��ǩ
	JButton button_register, button_login, button_change, button_exit;// ��ť
	JTextField textfield;
	JPasswordField passwordfield;
	String sqluser, sqlpwd;
	int level, sqllevel;

	// ���캯��
	public SystemLogin() {
		// �������
		JPanel1 = new JPanel();
		JPanel2 = new JPanel();
		JPanel3 = new JPanel();
		JPanel4 = new JPanel();
		JPanel5 = new JPanel();
		JPanel6 = new JPanel();
		// ������ǩ
		Label_a = new JLabel("��ӭʹ��ѧ����Ϣ����ϵͳ!");
		Label_user = new JLabel("�������˺�: ");
		Label_password = new JLabel("����������: ");
		Label_b = new JLabel("��ѡ�������Ϣ: ");

		String[] list = new String[] { "ѧ��", "��ʦ", "����Ա" };
		final JComboBox<String> comboBox = new JComboBox<String>(list);
		comboBox.setSelectedIndex(0);
		// ������ť
		button_login = new JButton("��¼");
		button_register = new JButton("ע��");
		button_change = new JButton("�޸�����");
		button_exit = new JButton("�˳�");
		// �����ı���
		textfield = new JTextField(12);
		passwordfield = new JPasswordField(12);
		// passwordfield.setEchoChar('*'); // ��������ʾΪ*

		// ���ò��ֹ���
		frame.setLayout(new GridLayout(6, 1)); // ����ʽ����

		// ����������
		JPanel1.add(Label_a);

		JPanel2.add(Label_user);
		JPanel2.add(textfield);
		JPanel3.add(Label_password);
		JPanel3.add(passwordfield);
		JPanel4.add(Label_b);
		JPanel4.add(comboBox);
		JPanel5.add(button_login);
		JPanel5.add(button_register);
		JPanel5.add(button_change);
		JPanel5.add(button_exit);

		// ���뵽JFrame
		frame.add(JPanel1);
		frame.add(JPanel2);
		frame.add(JPanel3);
		frame.add(JPanel4);
		frame.add(JPanel5);
		frame.add(JPanel6);

		// ��������
		Font font = new Font("΢���ź�", 1, 14);
		Label_user.setFont(font);
		Label_password.setFont(font);
		Label_a.setFont(new Font("΢���ź�", 1, 21));
		Label_b.setFont(new Font("΢���ź�", 1, 13));
		button_login.setFont(font);
		button_register.setFont(font);
		button_change.setFont(font);
		button_exit.setFont(font);
		comboBox.setFont(font);
		// ���ð�ť��С
		button_login.setPreferredSize(new Dimension(200, 30));
		button_register.setPreferredSize(new Dimension(200, 30));
		button_change.setPreferredSize(new Dimension(200, 30));
		button_exit.setPreferredSize(new Dimension(200, 30));
		// ȡ����ť�����
		button_login.setFocusPainted(false);
		button_register.setFocusPainted(false);
		button_change.setFocusPainted(false);
		button_exit.setFocusPainted(false);

		// ���ô���
		frame.setTitle("��¼ҳ��");// �����ǩ
		frame.setSize(595, 471);// �����С
		frame.setLocationRelativeTo(null);// ����Ļ�м���ʾ(������ʾ)
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// �˳��ر�JFrame
		frame.setVisible(true);// ��ʾ����

		button_login.addActionListener(new ActionListener() {
			private boolean flag;

			public void actionPerformed(ActionEvent e) {
				int index = comboBox.getSelectedIndex(); // ��ݵȼ��±�
				Statement stmt = null;
				String username = textfield.getText().toString();
				char[] pwd = passwordfield.getPassword();
				String password = String.valueOf(pwd); // ת��Ϊ�ַ�����
				try {
					Connection conn = SQLConnection.getConn(); // ���ݿ�����
					stmt = conn.createStatement();
					String sql = "SELECT * FROM userinfo WHERE username= '"
							+ username + "'";
					ResultSet rs = stmt.executeQuery(sql);
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

					if (!rs.next()) {
						JOptionPane.showMessageDialog(null, "�˺Ų�����,����ע��!",
								"����", JOptionPane.ERROR_MESSAGE);
						return;
					} else {
						rs.beforeFirst();
					}
					while (rs.next()) {
						sqlpwd = rs.getString("password");
						sqllevel = rs.getInt("level");
						if (!password.equals(sqlpwd)) {
							JOptionPane.showMessageDialog(null, "�������!", "����",
									JOptionPane.ERROR_MESSAGE);
						}
						if (index == 0) {
							level = 3;
						}
						if (index == 1) {
							level = 2;
						}
						if (index == 2) {
							level = 1;
						}
						if (level != sqllevel) {
							JOptionPane.showMessageDialog(null,
									"��ѡ��������Ϣ��ע��ʱ����!", "����",
									JOptionPane.ERROR_MESSAGE);
						} else {
							if (level == 1) {
								JOptionPane.showMessageDialog(null,
										"����Ա�û���¼�ɹ�!", "�ɹ�",
										JOptionPane.INFORMATION_MESSAGE);
								frame.setVisible(false);
								new AdminLogin();
							}
							if (level == 2) {
								JOptionPane.showMessageDialog(null,
										"��ʦ�û���¼�ɹ�!", "�ɹ�",
										JOptionPane.INFORMATION_MESSAGE);
								frame.setVisible(false);
								new TeacherLogin();
							}
							if (level == 3) {
								JOptionPane.showMessageDialog(null, "ѧ���û���¼�ɹ�!",
										"�ɹ�", JOptionPane.INFORMATION_MESSAGE);
								frame.setVisible(false);
								new UserLogin();
							}
						}
					}
				} catch (SQLException ex) {
					System.err.println("SQLException: " + ex.getMessage());
				}
			}

		});

		button_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new Register();
			}
		});

		button_change.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new ChangePwd();
			}
		});

		button_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispatchEvent(new WindowEvent(frame,
						WindowEvent.WINDOW_CLOSING)); // �˳�����
			}
		});

	}

	public void actionPerformed(ActionEvent e) {

	}

	public static void main(String[] args) {
		new SystemLogin();
	}
}