package connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Rank;

public class TranscriptDao {
	public List<Rank> getAllRank(String idTest){
		List<Rank> ranks = new ArrayList<>();
		Connection con = JDBCConnection.getJDBCConnection();
		
		String sql = "select name, school, yOB, score, usedTime\r\n"
				+ "from (student join transcript on student.idStudent = transcript.idStudent and transcript.idTest = ?)\r\n"
				+ "order by score desc, usedTime asc;";
		
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			
			preparedStatement.setString(1, idTest);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				Rank r = new Rank();
				
				r.setName(rs.getNString("name"));
				r.setSchool(rs.getNString("school"));
				r.setyOB(rs.getInt("yOB"));
				r.setScore(rs.getDouble("score"));
				r.setUsedTime(rs.getString("usedTime"));
				
				ranks.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ranks;
	}
}
