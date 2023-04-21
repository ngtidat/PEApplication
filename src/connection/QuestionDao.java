package connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Questions;

public class QuestionDao {

	public List<Questions> getAllQuestions(String idTest){
		List<Questions> q = new ArrayList<>();
		Connection con = JDBCConnection.getJDBCConnection();
		if (con != null) System.out.println("Connect to questions successfull");
		
		String sql = "Select *from questions Where idTest = ? ORDER BY numQuestion";
		
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			
			preparedStatement.setString(1, idTest);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				Questions t = new Questions();
				
				t.setIdTest(rs.getString("idTest"));
				t.setNumQuestion(rs.getInt("numQuestion"));
				t.setQuestion(rs.getNString("question"));
				t.setImg(rs.getString("img"));
				t.setAnswer1(rs.getNString("answer1"));
				t.setAnswer2(rs.getNString("answer2"));
				t.setAnswer3(rs.getNString("answer3"));
				t.setAnswer4(rs.getNString("answer4"));
				t.setCorrectAnswer(rs.getString("correctAnswer"));
				
				q.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return q;
	}
	
}
