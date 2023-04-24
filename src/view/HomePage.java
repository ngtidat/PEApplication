package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.GridLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class HomePage extends JFrame {

	private static JPanel contentPane;
	private JTextField txtQuestions;

	public HomePage() {
		
		setResizable(false);
		setTitle("PEApp");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 940, 503);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 255));
		panel.setBounds(0, 0, 165, 466);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblLogo = new JLabel("PEApplication");
		lblLogo.setIcon(new ImageIcon("D:\\Java\\Project\\PEApplication\\src\\img\\logo.png"));
		lblLogo.setForeground(new Color(255, 255, 255));
		lblLogo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLogo.setBounds(0, 0, 165, 70);
		panel.add(lblLogo);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 255));
		panel_1.setBounds(0, 71, 165, 300);
		panel_1.setOpaque(true);
		panel.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 1, 0, 10));
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(0, 0, 255));
		menuBar.setBorderPainted(false);
		
		menuBar.setLayout(new GridLayout(0,1));
		panel_1.add(menuBar);
		
		JMenu mnName = new JMenu(Login.getCurrentStudent().getName());
		mnName.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnName.setForeground(new Color(255, 255, 255));
		menuBar.add(mnName);
		
		JMenu mnNewMenu_1 = new JMenu("Lịch sử làm bài");
		mnNewMenu_1.setForeground(new Color(255, 255, 255));
		mnNewMenu_1.setBackground(new Color(240, 240, 240));
		mnNewMenu_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuBar.add(mnNewMenu_1);
		
		JMenu mnNewMenu_2 = new JMenu("New menu");
		mnNewMenu_2.setForeground(new Color(255, 255, 255));
		mnNewMenu_2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		menuBar.add(mnNewMenu_2);
		
		JMenu mnNewMenu_3 = new JMenu("New menu");
		mnNewMenu_3.setForeground(new Color(255, 255, 255));
		mnNewMenu_3.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuBar.add(mnNewMenu_3);
		
		JMenu mnNewMenu_4 = new JMenu("Đổi mật khẩu");
		mnNewMenu_4.setForeground(new Color(255, 255, 255));
		mnNewMenu_4.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuBar.add(mnNewMenu_4);
		
		JMenu mnLogin = new JMenu("Đăng xuất");
		mnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int input = 0;
				if (mnLogin.isSelected()) {
					JFrame frame = new JFrame();
					input = JOptionPane.showConfirmDialog(frame,
							"Bạn có muốn đăng xuất không",
							"Đăng xuất", JOptionPane.YES_NO_OPTION);
				}
				if (input == 0) {
					setVisible(false);
					new Login().setVisible(true); 
				}
			}
		});
		mnLogin.setForeground(new Color(255, 255, 255));
		mnLogin.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		menuBar.add(mnLogin);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(164, 0, 762, 466);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblCountDownDay = new JLabel("Ngày thi còn lại");
		lblCountDownDay.setForeground(new Color(255, 0, 0));
		lblCountDownDay.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblCountDownDay.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCountDownDay.setBounds(72, 10, 680, 44);
		setCountDown(lblCountDownDay);
		panel_2.add(lblCountDownDay);
		
		txtQuestions = new JTextField();
		txtQuestions.setBackground(new Color(255, 255, 255));
		txtQuestions.setEditable(false);
		txtQuestions.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtQuestions.setText("Nhập vào đây để tìm kiếm");
		txtQuestions.setBounds(72, 64, 680, 50);
		panel_2.add(txtQuestions);
		txtQuestions.setColumns(10);
		txtQuestions.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtQuestions.setEditable(true);
				
				String defaultText = "Nhập vào đây để tìm kiếm";
				
				if (txtQuestions.getText().equals(defaultText)) 
					txtQuestions.setText("");
			}
		});
		txtQuestions.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (txtQuestions.getText().trim().equals(""));
				txtQuestions.setText("Nhập vào đây để tìm kiếm");
			}
		});
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(255,255,255));
		panel_3.setBounds(72, 124, 680, 332);
		panel_2.add(panel_3);
		panel_3.setLayout(new GridLayout(3, 4, 20, 20));
		
		String subjects[] = {"Toán học","Ngữ văn","Tiếng anh","Lịch sử","Vật lý",
	                            "Địa lý","GDCD","Tin học","Hóa học","Sinh học","Công nghệ","TKB"};
		for (String s:subjects) {
			JButton btnSubject = new JButton(s);
			btnSubject.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setContentPane(new ListExams(s));
				}
			});
			btnSubject.setBackground(new Color(255, 255, 255));
			btnSubject.setFont(new Font("Tahoma", Font.PLAIN, 14));
			panel_3.add(btnSubject);
		}
	}

	private void setCountDown(JLabel lblCountDownDay) {
		Date today = new Date();
		String sTestDay = "28/06/2023";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			Date testDay = simpleDateFormat.parse(sTestDay);
			
			long startTime = today.getTime();
	        long endTime = testDay.getTime();
	        
	        long countDownTime = (endTime - startTime)/(24*60*60*1000);
	        
	        while (countDownTime < 0) countDownTime += 365;
	        
	        if (countDownTime == 0) {
	        	lblCountDownDay.setText("Chúc các bạn thành công!");
            }
            else lblCountDownDay.setText("Ngày thi dự kiến còn " + countDownTime + " ngày");
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setContentPane(Container contentPane) {
		super.setContentPane(contentPane);
	}
}
