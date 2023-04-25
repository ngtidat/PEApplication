package view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import connection.UpdateStatement;
import model.CountdownTimer;
import model.Transcript;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Results extends JPanel {

	public Results() {
		
		Frame[] frames = Frame.getFrames();
		
		setLayout(null);
		
		JTextPane txtpnSCuTr = new JTextPane();
		txtpnSCuTr.setEditable(false);
		txtpnSCuTr.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtpnSCuTr.setText("Số câu trả lời đúng: "+ countCorrectAnswers() + "\r\n" + "Điểm thi: " + getScore());
		txtpnSCuTr.setBounds(10, 10, 840, 70);
		add(txtpnSCuTr);
		
		//
		String[] columnNames = {"STT", "Câu trả lời", "Đáp án đúng"};
		List<Object[]> dataList = new ArrayList<>();
		for (int i = 1; i <= DoExams.getAllQuestions().size(); i++) {
			dataList.add(new Object[] {i, DoExams.getMark()[i], DoExams.getAllQuestions().get(i-1).getCorrectAnswer()});
		}
		Object[][] data = dataList.toArray(new Object[0][0]);
		DefaultTableModel model = new DefaultTableModel(data, columnNames);
		JTable table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        JScrollPane pane = new JScrollPane(table);
        pane.setLocation(10, 90);
        pane.setSize(840, 338);
        add(pane, BorderLayout.CENTER);
        
        // Rank
        JButton btnRank = new JButton("Bảng xếp hạng");
        btnRank.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Transcript transcript = new Transcript();
        		transcript.setIdStudent(Login.getCurrentStudent().getIdStudent());
        		transcript.setIdTest(ListExams.getIdTestSelection());
        		transcript.setScore(getScore());
        		transcript.setUsedTime(CountdownTimer.getUsedTime());
        		
        		UpdateStatement.insertTranscript(transcript);
        		
        		if (frames.length >= 5)
        			frames[4].dispose();
        		HomePage p = new HomePage();
        		p.setVisible(true);
				p.setContentPane(new view.Transcript());
				p.setSize(930, 540);
        	}
        });
        btnRank.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnRank.setBounds(654, 438, 196, 42);
        add(btnRank);
        
	}

	public double getScore() {
		double scoreEachQuetion = Math.round(10.0/DoExams.getAllQuestions().size()*100.0)/100.0;
		
		return Math.round(this.countCorrectAnswers()*scoreEachQuetion*100.0)/100.0;
	}

	public int countCorrectAnswers() {
		int cnt = 0;
		
		for (int i = 0; i < DoExams.getAllQuestions().size(); i++) {
			if (DoExams.getMark()[i+1].equals(DoExams.getAllQuestions().get(i).getCorrectAnswer())) cnt++;
		}
		return cnt;
	}
}
