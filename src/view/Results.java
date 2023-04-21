package view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import connection.QuestionDao;
import connection.UpdateStatement;
import model.Questions;
import model.Score;
import model.Transcript;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Results extends JPanel implements Score {
	
	private String idTest;
	private String []mark;

	public Results(int idStudent,String idTest,String[] mark, String usedTime) {
		this.idTest = idTest;
		this.mark = new String[mark.length];
		System.arraycopy(mark, 0, this.mark, 0, mark.length);
		
		List<Questions> allQuestions = new QuestionDao().getAllQuestions(idTest);
		
		setLayout(null);
		
		JTextPane txtpnSCuTr = new JTextPane();
		txtpnSCuTr.setEditable(false);
		txtpnSCuTr.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtpnSCuTr.setText("Số câu trả lời đúng: "+ countCorrectAnswers() + "\r\n" + "Điểm thi: " + getScore(0.2));
		txtpnSCuTr.setBounds(10, 10, 840, 70);
		add(txtpnSCuTr);
		
		//
		String[] columnNames = {"STT", "Câu trả lời", "Đáp án đúng"};
		List<Object[]> dataList = new ArrayList<>();
		for (int i = 1; i <= 50; i++) {
			dataList.add(new Object[] {i, mark[i], allQuestions.get(i-1).getCorrectAnswer()});
		}
		Object[][] data = dataList.toArray(new Object[0][0]);
		DefaultTableModel model = new DefaultTableModel(data, columnNames);
		JTable table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        JScrollPane pane = new JScrollPane(table);
        pane.setLocation(10, 90);
        pane.setSize(840, 338);
        add(pane, BorderLayout.CENTER);
        
        JButton btnRank = new JButton("Bảng xếp hạng");
        btnRank.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Transcript transcript = new Transcript();
        		transcript.setIdStudent(idStudent);
        		transcript.setIdTest(idTest);
        		transcript.setScore(getScore(0.25));
        		transcript.setUsedTime(usedTime);
        		
        		UpdateStatement.insertTranscript(transcript);
        		
        		HomePage p = new HomePage(idStudent, "");
        		p.setVisible(true);
				p.setContentPane(new view.Transcript(idTest));
        		
        	}
        });
        btnRank.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnRank.setBounds(654, 438, 196, 42);
        add(btnRank);
        
	}

	@Override
	public double getScore(double scoreEachQuetion) {
		
		return this.countCorrectAnswers()*0.20;
	}

	@Override
	public int countCorrectAnswers() {
		List<Questions> allQuestions = new QuestionDao().getAllQuestions(idTest);
		
		int cnt = 0;
		
		for (int i = 0; i < 50; i++) {
			if (mark[i+1].equals(allQuestions.get(i).getCorrectAnswer())) cnt++;
		}
		
		System.out.println(cnt);
		return cnt;
	}
}
