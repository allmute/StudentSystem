package studentsystem;

import javax.swing.*;
import javax.swing.table.JTableHeader;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import studentsystem.SQLConnection;

public class GradecourseQuery extends JFrame {
	{
		try {
			// UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			// ��ǰϵͳ
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	private JScrollPane ScrollPane;
	private JTableHeader jth;
	private JTable JTable;
	private JButton btnShow;
	private JButton button_a;
	private JButton button_b;
	private JLabel label;
	private JTextField textfield;

	public GradecourseQuery() {
		super("�ɼ���Ϣ��ѯϵͳ"); // JFrame�ı�������
		this.setSize(660, 600); // ���ƴ����С
		this.setLayout(null); // �Զ��岼��
		this.setLocation(400, 100); // ��������Ժ󣬴�������Ļ��λ��
		this.ScrollPane = new JScrollPane();
		this.label = new JLabel("��������Ŀγ̱��:");
		this.textfield = new JTextField(15);
		this.button_a = new JButton("��ѯ");
		this.button_b = new JButton("ȡ��");
		this.label.setBounds(10, 10, 135, 30);
		this.textfield.setBounds(150, 15, 120, 20);
		this.button_a.setBounds(100, 480, 150, 30);
		this.button_b.setBounds(380, 480, 150, 30);
		this.ScrollPane.setBounds(10, 50, 623, 400); // ���ù������С
		Font font = new Font("΢���ź�", 1, 14);
		label.setFont(font);
		button_a.setFont(font);
		button_b.setFont(font);
		button_a.setFocusPainted(false);
		button_b.setFocusPainted(false);

		/******** ��ť��ȷ��������Ӧ *******/
		this.button_a.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Statement stmt = null;
				Connection conn = SQLConnection.getConn(); // ���ݿ�����
				String id = textfield.getText();

				if (id.equals("")) {
					JOptionPane.showMessageDialog(null, "������������Ϣ!", "����",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				try { // �жϿγ̱����Ϣ�Ƿ����
					stmt = conn.createStatement();
					String sql = "select count(*) AS num FROM sc WHERE courseid=?";
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setString(1, id);
					ResultSet jug = ps.executeQuery();
					jug.next();
					int num = jug.getInt("num");
					if (num == 0) {
						JOptionPane.showMessageDialog(null, "�˿γ̱�Ų�����!",
								"����", JOptionPane.ERROR_MESSAGE);
						return;
					} else {
						btnShow_ActionPerformed(e);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		/****** ��ť ��ȡ��������Ӧ *****/
		this.button_b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		/******* ��������뵽������ ******/
		add(this.label);
		add(this.textfield);
		add(this.ScrollPane);
		add(this.button_a);
		add(this.button_b);
		this.setVisible(true);
		this.setLocationRelativeTo(null); // ������ʾ
//		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	/*** �������ݿⲢ��ʾ������� ***/
	public void btnShow_ActionPerformed(ActionEvent ae) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/management_system";
			String username = "root";
			String passwords = "1201101";
			Connection conn = DriverManager.getConnection(url, username,
					passwords);
			String courseid = textfield.getText();
			String sql = "SELECT * FROM sc WHERE courseid ='" + courseid + "'";
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			int count = 0;
			while (rs.next()) {
				count++;
			}
			rs = pstm.executeQuery();
			// ����ѯ��õļ�¼���ݣ�ת�����ʺ�����JTable��������ʽ
			Object[][] info = new Object[count][6];
			String[] title = { "ѧ��", "�༶���", "�γ̱��", "�ɼ�" };
			count = 0;
			while (rs.next()) {
				info[count][0] = Integer.valueOf(rs.getInt("id"));
				info[count][1] = rs.getString("classid");
				info[count][2] = rs.getString("courseid");
				info[count][3] = rs.getString("grade");
				count++;
			}
			// ����JTable
			this.JTable = new JTable(info, title);
			// ��ʾ��ͷ
			this.jth = this.JTable.getTableHeader();
			// ��JTable���뵽���������������
			this.ScrollPane.getViewport().add(JTable);
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
			JOptionPane.showMessageDialog(null, "����Դ����", "����",
					JOptionPane.ERROR_MESSAGE);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			JOptionPane.showMessageDialog(null, "���ݲ�������", "����",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void main(String[] args) {
		new GradecourseQuery();
	}
}