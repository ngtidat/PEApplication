package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import connection.StudentDao;
import connection.UpdateStatement;
import model.PasswordEncryption;
import model.Student;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Login extends JFrame {
	
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JTextField textFieldName;
	private JTextField textFieldSchool;
	private JTextField textFieldYOB;
	private JTextField textFieldUserName;
	private JTextField textFieldPassword;
	private static Student currentStudent;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Login() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Login");
		setResizable(false);
		setLocationRelativeTo(null);
		setBounds(100, 100, 604, 422);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(40, 28, 525, 347);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Đăng Nhập", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblUser = new JLabel("Tài khoản:");
		lblUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblUser.setBackground(new Color(128, 255, 255));
		lblUser.setBounds(10, 113, 126, 33);
		panel.add(lblUser);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(146, 113, 198, 33);
		panel.add(textField);
		
		JLabel lblPassword = new JLabel("Mật khẩu:");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setBackground(new Color(128, 255, 255));
		lblPassword.setBounds(10, 156, 126, 33);
		panel.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(146, 156, 198, 33);
		panel.add(passwordField);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Hiển thị mật khẩu");
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxNewCheckBox.isSelected()) passwordField.setEchoChar((char)0);
				else passwordField.setEchoChar('*');
			}
		});
		chckbxNewCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxNewCheckBox.setBounds(180, 195, 126, 21);
		panel.add(chckbxNewCheckBox);
		
		JComboBox cbUser = new JComboBox();
		cbUser.setBackground(new Color(255, 255, 255));
		cbUser.setModel(new DefaultComboBoxModel(new String[] {"Học Sinh", "Admin"}));
		cbUser.setSelectedIndex(0);
		cbUser.setBounds(146, 70, 198, 33);
		panel.add(cbUser);
		
		// Log in
		
		JButton btnNewButton = new JButton("Đăng nhập");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				List<Student> students = new StudentDao().getAllStudent();
				
				// Encrypt password
				String password = new String();
				try {
					password = new PasswordEncryption().getPasswordEncryption(passwordField.getText());
				} catch (NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				// Check
				// User is student
				if (cbUser.getSelectedIndex() == 0)
				{	
					for (Student s : students)
					{
						if (textField.getText().equals(s.getUserName()) 
								&& password.equals(s.getPassword())) 
						{
							Login.setCurrentStudent(s);
							setVisible(false);
							new HomePage().setVisible(true);
							return;
						}
					}
				}
				// User is administrators
				else
				{
					//List<Admin> administrators = new AdminDao().getAllAdmin();
				}
				
				// Incorrect user name and password
				JFrame frame = new JFrame();
				JOptionPane.showMessageDialog(frame, 
						"Tên tài khoản hoặc mật khẩu không chính xác!",
						"Lỗi!",
						JOptionPane.ERROR_MESSAGE);
			}
		});
		btnNewButton.setBackground(new Color(128, 255, 255));
		btnNewButton.setBounds(146, 250, 198, 21);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Quên mật khẩu?");
		btnNewButton_1.setBackground(new Color(240, 240, 240));
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_1.setBounds(129, 219, 126, 21);
		panel.add(btnNewButton_1);
		
		JLabel lblOptionUser = new JLabel("Đối tượng:");
		lblOptionUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblOptionUser.setBackground(new Color(240, 240, 240));
		lblOptionUser.setBounds(10, 70, 126, 33);
		panel.add(lblOptionUser);
		
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Đăng ký", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblnName = new JLabel("Họ tên:");
		lblnName.setHorizontalAlignment(SwingConstants.LEFT);
		lblnName.setBackground(new Color(128, 255, 255));
		lblnName.setBounds(10, 29, 126, 33);
		panel_1.add(lblnName);
		
		textFieldName = new JTextField();
		textFieldName.setColumns(10);
		textFieldName.setBounds(146, 29, 198, 33);
		panel_1.add(textFieldName);
		
		JLabel lblSchool = new JLabel("Trường học:");
		lblSchool.setHorizontalAlignment(SwingConstants.LEFT);
		lblSchool.setBackground(new Color(128, 255, 255));
		lblSchool.setBounds(10, 72, 126, 33);
		panel_1.add(lblSchool);
		
		textFieldSchool = new JTextField();
		textFieldSchool.setColumns(10);
		textFieldSchool.setBounds(146, 72, 198, 33);
		panel_1.add(textFieldSchool);
		
		JLabel lblYOB = new JLabel("Năm sinh:");
		lblYOB.setHorizontalAlignment(SwingConstants.LEFT);
		lblYOB.setBackground(new Color(128, 255, 255));
		lblYOB.setBounds(10, 115, 126, 33);
		panel_1.add(lblYOB);
		
		JLabel lblUserName = new JLabel("Tài khoản:");
		lblUserName.setHorizontalAlignment(SwingConstants.LEFT);
		lblUserName.setBackground(new Color(128, 255, 255));
		lblUserName.setBounds(10, 158, 126, 33);
		panel_1.add(lblUserName);
		
		JLabel lblHTn_1_3 = new JLabel("Mật khẩu:");
		lblHTn_1_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblHTn_1_3.setBackground(new Color(128, 255, 255));
		lblHTn_1_3.setBounds(10, 201, 126, 33);
		panel_1.add(lblHTn_1_3);
		
		textFieldYOB = new JTextField();
		textFieldYOB.setColumns(10);
		textFieldYOB.setBounds(146, 115, 198, 33);
		panel_1.add(textFieldYOB);
		
		textFieldUserName = new JTextField();
		textFieldUserName.setColumns(10);
		textFieldUserName.setBounds(146, 158, 198, 33);
		panel_1.add(textFieldUserName);
		
		textFieldPassword = new JTextField();
		textFieldPassword.setColumns(10);
		textFieldPassword.setBounds(146, 201, 198, 33);
		panel_1.add(textFieldPassword);
		
		// Sign up
		JButton btnRegister = new JButton("Đăng ký");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				
				List<Student> students = new StudentDao().getAllStudent();
				
				if (textFieldName.getText().trim().equals("") || 
						textFieldUserName.getText().trim().equals("") ||
						textFieldPassword.getText().trim().equals("") ||
						textFieldSchool.getText().trim().equals("") ||
						textFieldYOB.getText().trim().equals(""))  
				{
					JFrame frame = new JFrame();
					JOptionPane.showMessageDialog(frame,
			                "Vui lòng điền đầy đủ thông tin yêu cầu!",
			                "Lỗi",
			                JOptionPane.ERROR_MESSAGE);
				}
				// Check userName
				// If userName exists, error message
				// If userName doesn't exist, insert into database
				else 
				{
					for (Student s : students) {
						if(textFieldUserName.getText().trim().equals(s.getUserName().trim())) {
							
							System.out.println("Account already existed");
							
							JFrame frame = new JFrame();
							JOptionPane.showMessageDialog(frame,
									"Tài khoản đã tồn tại!",
									"Lỗi",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
					}
					
					Student accRegister = new Student();
					
					String password = new String();
					try {
						password = new PasswordEncryption().getPasswordEncryption(textFieldPassword.getText().trim());
					} catch (NoSuchAlgorithmException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					accRegister.setName(textFieldName.getText());
					accRegister.setSchool(textFieldSchool.getText());
					accRegister.setYearOfBirth(Integer.parseInt(textFieldYOB.getText()));
					accRegister.setUserName(textFieldUserName.getText().trim());
					accRegister.setPassword(password);
					
					UpdateStatement.insertStudent(accRegister);
			
					JFrame frame = new JFrame();
					JOptionPane.showMessageDialog(frame,
							"Đăng ký thành công!",
							"Thông báo",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnRegister.setBounds(146, 254, 198, 21);
		panel_1.add(btnRegister);
	}

	public static Student getCurrentStudent() {
		return currentStudent;
	}

	public static void setCurrentStudent(Student currentStudent) {
		Login.currentStudent = currentStudent;
	}
}
