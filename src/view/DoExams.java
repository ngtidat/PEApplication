package view;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Component;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import connection.QuestionDao;
import connection.TestDao;
import model.CountdownTimer;
import model.Questions;
import model.Test;

import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.util.Arrays;
import java.util.List;

import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class DoExams extends JPanel {
	
	private static int defaultTime;
	public static int getDefaultTime() {
		return defaultTime;
	}

	public static void setDefaultTime(int defaultTime) {
		DoExams.defaultTime = defaultTime;
	}

	private static String[] mark;
	private static String usedTime;
	private static List<Questions> allQuestions;

	public static List<Questions> getAllQuestions() {
		return allQuestions;
	}

	public static void setAllQuestions(List<Questions> allQuestions) {
		DoExams.allQuestions = allQuestions;
	}

	public DoExams() {
		
		Frame[] frames = Frame.getFrames();
		
		DoExams.setAllQuestions(new QuestionDao().getAllQuestions(ListExams.getIdTestSelection()));
		List<Test> test = new TestDao().getAllTestWithId(ListExams.getIdTestSelection());
		System.out.println(test.get(0));
		
		mark = new String[DoExams.getAllQuestions().size()+1];
		Arrays.fill(mark, "0");
		
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(190, 10, 588, 74);
		add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel = new JLabel("Đề thi: " + test.get(0).getTitleTest() +"\r\n");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Thời gian: "+ test.get(0).getDefaultTime()  +"\r\n");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Mã đề: " + ListExams.getIdTestSelection().substring(ListExams.getIdTestSelection().length()-3));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(lblNewLabel_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(10, 10, 170, 473);
		add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblListAnswers = new JLabel("Danh sách câu trả lời");
		lblListAnswers.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblListAnswers.setHorizontalAlignment(SwingConstants.CENTER);
		lblListAnswers.setBounds(10, 5, 150, 40);
		lblListAnswers.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		panel_1.add(lblListAnswers);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 55, 150, 371);
		panel_1.add(scrollPane);
		
		// Set number of questions
		int numberOfQuestions = DoExams.getAllQuestions().size();
		String[] itemList = new String[numberOfQuestions];
		for (int i = 0; i < numberOfQuestions; i++) {
			itemList[i] = "Câu " + (i+1);
		}
		
		JList list = new JList();
		list.setModel(new AbstractListModel() {
			String[] values = itemList;
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setFont(new Font("Sitka Small", Font.PLAIN, 14));
		list.setSelectedIndex(0);
		list.setCellRenderer(new MyCellRenderer());
		scrollPane.setViewportView(list);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(190, 94, 687, 389);
		add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblImage = new JLabel();
		if (DoExams.getAllQuestions().get(0).getImg() != null)
			lblImage.setIcon(new ImageIcon(DoExams.getAllQuestions().get(0).getImg()));
		else lblImage.setText("");
		lblImage.setBounds(0, 95, 687, 145);
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblImage);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(0, 250, 687, 129);
		panel_3.setBackground(new Color(255, 255, 255));
		panel_2.add(panel_3);
		panel_3.setLayout(null);
		
		// Select Answer
		JRadioButton A = new JRadioButton(DoExams.getAllQuestions().get(0).getAnswer1());
		A.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mark[list.getSelectedIndex()+1] = "A";
				System.out.println((list.getSelectedIndex()) + 1 + "" + mark[list.getSelectedIndex()+1]);
			}
		});
		A.setBounds(6, 17, 280, 49);
		panel_3.add(A);
		
		JRadioButton B = new JRadioButton(DoExams.getAllQuestions().get(0).getAnswer2());
		B.setBounds(6, 68, 280, 49);
		B.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mark[list.getSelectedIndex()+1] = "B";
				System.out.println((list.getSelectedIndex() + 1) + "" + mark[list.getSelectedIndex()+1]);
			}
		});
		panel_3.add(B);
		
		JRadioButton C = new JRadioButton(DoExams.getAllQuestions().get(0).getAnswer3());
		C.setBounds(401, 17, 280, 49);
		C.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mark[list.getSelectedIndex()+1] = "C";
				System.out.println((list.getSelectedIndex() + 1) + "" + mark[list.getSelectedIndex()+1]);
			}
		});
		panel_3.add(C);
		
		JRadioButton D = new JRadioButton(DoExams.getAllQuestions().get(0).getAnswer4());
		D.setBounds(401, 70, 280, 47);
		D.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mark[list.getSelectedIndex()+1] = "D";
				System.out.println((list.getSelectedIndex() + 1) + "" + mark[list.getSelectedIndex()+1]);
			}
		});
		panel_3.add(D);
		
		ButtonGroup answers = new ButtonGroup();
		answers.add(A);
		answers.add(B);
		answers.add(C);
		answers.add(D);
		
		JTextPane txtpnQuestion = new JTextPane();
		txtpnQuestion.setEditable(false);
		txtpnQuestion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtpnQuestion.setText(DoExams.getAllQuestions().get(0).getQuestion());
		txtpnQuestion.setBounds(0, 0, 687, 85);
		panel_2.add(txtpnQuestion);
		
		// Time
		
		String t = test.get(0).getDefaultTime().substring(0, 2);
		defaultTime = Integer.parseInt(t);
//		System.out.println(defaultTime);
		
		JLabel lblCountDownTimer = new JLabel("90:00");
		lblCountDownTimer.setHorizontalAlignment(SwingConstants.CENTER);
		lblCountDownTimer.setOpaque(true);
		lblCountDownTimer.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCountDownTimer.setBackground(new Color(255, 255, 255));
		lblCountDownTimer.setBounds(788, 10, 89, 74);
		CountdownTimer timer = new CountdownTimer(lblCountDownTimer, defaultTime);
        timer.start();
		add(lblCountDownTimer);
		
		list.addListSelectionListener(e -> {
			    if (!e.getValueIsAdjusting()) {
			    	for (Questions q : DoExams.getAllQuestions()) {
			    		if (list.getSelectedIndex() + 1 == q.getNumQuestion()) {
			    			txtpnQuestion.setText("Câu " + q.getNumQuestion() + ": " + q.getQuestion());
			    			A.setText(q.getAnswer1());
			    			B.setText(q.getAnswer2());
			    			C.setText(q.getAnswer3());
			    			D.setText(q.getAnswer4());
			    			if (q.getImg() == "") lblImage.setText("");
			    			else {
			    				lblImage.setIcon(new ImageIcon(q.getImg()));
			    			}
			    		}
			    	}
			    	
			    	// Reset Button Answer
			    	if (mark[list.getSelectedIndex()+1].equals("0")) {
		    			answers.clearSelection();
		    		}
			    	else {
			    		if (mark[list.getSelectedIndex()+1].equals("A")) A.setSelected(true);
			    		if (mark[list.getSelectedIndex()+1].equals("B")) B.setSelected(true);
			    		if (mark[list.getSelectedIndex()+1].equals("C")) C.setSelected(true);
			    		if (mark[list.getSelectedIndex()+1].equals("D")) D.setSelected(true);
			    	}	
			}
		});
		
		//Submit
		JButton btnSubmit = new JButton("Nộp bài");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				boolean c = true;

				for (int i = 1; i <= DoExams.getAllQuestions().size(); i++) 
					if (mark[i].equals("0")) {
						c = false;
						System.out.println("The exam is not done yet");
						
						JFrame frame = new JFrame();
						JOptionPane.showMessageDialog(frame, 
								"Bạn vẫn chưa hoàn thành bài!",
								"Thông báo",
								JOptionPane.INFORMATION_MESSAGE);
	
						break;
					}
				// Result
				if (c) {
					int input = 0;
					JFrame frame = new JFrame();
					input = JOptionPane.showConfirmDialog(frame,
								"Bạn có chắc chắn nộp bài không?",
								"Nộp bài", JOptionPane.YES_NO_OPTION);
					if (input == 0) {
						timer.stop();
						DoExams.setUsedTime(lblCountDownTimer.getText());
						frames[2].dispose();
						HomePage p = new HomePage();
						p.setVisible(true);
						p.setContentPane(new Results());
						p.setSize(900,540);
					}
				}
			}
		});
		btnSubmit.setBackground(new Color(0, 255, 0));
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSubmit.setBounds(75, 436, 85, 27);
		panel_1.add(btnSubmit);
		
	}

	public static String[] getMark() {
		return mark;
	}

	public static void setMark(String[] mark) {
		DoExams.mark = mark;
	}

	public static String getUsedTime() {
		return usedTime;
	}

	public static void setUsedTime(String usedTime) {
		DoExams.usedTime = usedTime;
	}
	
	public class MyCellRenderer extends DefaultListCellRenderer {
	    @Override
	    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
	        Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
	        if (!mark[index+1].equals("0")) {
	            c.setBackground(Color.GREEN);
	        }else {
                setBackground(java.awt.Color.WHITE);
                setForeground(java.awt.Color.BLACK);
            }
	        return c;
	    }
	}
	
}
