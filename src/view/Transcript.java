package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import connection.TranscriptDao;
import model.Rank;
import java.awt.Font;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Transcript extends JPanel {
	private JTable table;
	
	public Transcript() {
		
		Frame[] frames = Frame.getFrames();
		
		List<Rank> ranks = new TranscriptDao().getAllRank(ListExams.getIdTestSelection());
		
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Bảng xếp hạng");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 10, 881, 75);
		add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 95, 881, 330);
		add(scrollPane);
		
		String[] columnNames = {"STT", "Họ tên", "Trường học", "Năm sinh", "Điểm", "Thời gian làm bài"};
		List<Object[]> dataList = new ArrayList<>();
		for (int i = 0; i < ranks.size(); i++) {
			dataList.add(new Object[] {i+1, ranks.get(i).getName(), ranks.get(i).getSchool(),ranks.get(i).getyOB(),ranks.get(i).getScore(),ranks.get(i).getUsedTime()});
		}
		Object[][] data = dataList.toArray(new Object[0][0]);
		DefaultTableModel model = new DefaultTableModel(data, columnNames);
		
		table = new JTable(model);
		scrollPane.setViewportView(table);
		
		// Return HomePage
		JButton btnBackHomePage = new JButton("Về trang chủ");
		btnBackHomePage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frames[4].dispose();
				HomePage p = new HomePage();
				p.setVisible(true);
			}
		});
		btnBackHomePage.setBackground(new Color(255, 255, 255));
		btnBackHomePage.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBackHomePage.setBounds(747, 435, 144, 48);
		add(btnBackHomePage);
		
	}
}
