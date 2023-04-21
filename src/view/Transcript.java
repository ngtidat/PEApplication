package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import connection.TranscriptDao;
import model.Rank;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Transcript extends JPanel {
	private JTable table;
	public Transcript(String idTest) {
		
		List<Rank> ranks = new TranscriptDao().getAllRank(idTest);
		
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Bảng xếp hạng");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 10, 881, 75);
		add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 95, 881, 388);
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
		
	}
}
