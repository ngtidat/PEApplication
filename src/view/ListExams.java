package view;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.DefaultComboBoxModel;
import connection.QuestionDao;
import connection.StudentDao;
import connection.TestDao;
import model.Questions;
import model.Student;
import model.Test;

import javax.swing.JTabbedPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.ImageIcon;
import javax.swing.JScrollBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;

public class ListExams extends JPanel {
	
//	private String[] listTests;
	String name;

	public ListExams(int idStudent, String Subject) {
		
		Frame[] frames = Frame.getFrames();
		
		System.out.println(frames.length);
		
		List<Test> test = new TestDao().getAllTest();
		List<Student> students = new StudentDao().getAllStudent();
		
		setBackground(new Color(255, 255, 255));
		setSize(940, 503);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 940, 50);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(Subject);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setBounds(55, 0, 155, 50);
		panel.add(lblNewLabel);
		
		JComboBox comboBoxReleaseYear = new JComboBox();
		comboBoxReleaseYear.setOpaque(true);
		comboBoxReleaseYear.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBoxReleaseYear.setModel(new DefaultComboBoxModel(new String[] {"2023", "2022", "2021", "2020", "2019", "2018"}));
		comboBoxReleaseYear.setSelectedIndex(2);
		comboBoxReleaseYear.setBackground(new Color(255, 255, 255));
		comboBoxReleaseYear.setBounds(830, 0, 100, 51);
		comboBoxReleaseYear.setBorder(null);
		panel.add(comboBoxReleaseYear);
		
		// Return HomePage
		for (Student s:students) {
			if (s.getIdStudent() == idStudent) {
				name = s.getName();
				break;
			}
		}
		JButton btnBackHomePage = new JButton("");
		btnBackHomePage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HomePage p = new HomePage(idStudent, name);
				p.setVisible(true);
				frames[1].dispose();
			}
		});
		btnBackHomePage.setIcon(new ImageIcon("C:\\Users\\NTDat\\eclipse-workspace\\PEApp\\src\\img\\back.png"));
		btnBackHomePage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnBackHomePage.setBackground(Color.LIGHT_GRAY);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnBackHomePage.setBackground(new Color(255,255,255));
			}
		});
		btnBackHomePage.setBackground(new Color(255, 255, 255));
		btnBackHomePage.setBounds(0, 0, 45, 50);
		btnBackHomePage.setBorderPainted(false);
		panel.add(btnBackHomePage);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 48, 940, 455);
		add(tabbedPane);
		
		JPanel panelExams = new JPanel();
		tabbedPane.addTab("Đề thi", null, panelExams, null);
		tabbedPane.setEnabledAt(0, true);
		panelExams.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 915, 408);
		panelExams.add(scrollPane);
		
		
		String[] listTests = new String[1000];
		int i = 0;
		
		for (Test t : test) {
			listTests[i] = new String(t.getTitleTest());
			i++;
		}
				
		JList list = new JList();
		list.setModel(new AbstractListModel() {
			
			String[] values = listTests;
						
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		scrollPane.setViewportView(list);
		
		// Do exam
		
		list.addListSelectionListener(e -> {
		    if (!e.getValueIsAdjusting()) {
		    	int c = 0;
		    	for (Test t : test) {
		    		List<Questions> allQuestions = new QuestionDao().getAllQuestions(t.getIdTest());
		    		if (list.getSelectedValue().equals(t.getTitleTest()) && allQuestions.size() == 50) {
		    			frames[1].dispose();
			    		HomePage p = new HomePage(idStudent, Subject);
			   			p.setVisible(true);
			   			p.setContentPane(new DoExams(idStudent, t.getIdTest()));
			   			p.setSize(900,530);
			   			c = 1;
			   			break;
			   		}
		    	}
		    	if (c == 0) {
		    		JFrame frame = new JFrame();
					JOptionPane.showMessageDialog(frame, 
							"Đề vẫn chưa được cập nhập!",
							"Thông báo",
							JOptionPane.INFORMATION_MESSAGE);
		    	}
		    }	
		});
		
		JScrollBar scrollBar = new JScrollBar();
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Yêu thích", null, panel_2, null);
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
