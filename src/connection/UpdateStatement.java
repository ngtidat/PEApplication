package connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Student;
import model.Transcript;

public class UpdateStatement {
	
	public static void insertStudent(Student student) {
		Connection con = JDBCConnection.getJDBCConnection();
		
		String sql = "Insert into student(name, school, yOB, userName, password) values(?,?,?,?,?)";
		
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			
			pst.setNString(1, student.getName());
			pst.setNString(2, student.getSchool());
			pst.setInt(3, student.getYearOfBirth());
			pst.setString(4, student.getUserName());
			pst.setString(5, student.getPassword());
			
			pst.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Insert fail");
		}
	}
	
	public static void insertTranscript(Transcript transcript) {
		Connection con = JDBCConnection.getJDBCConnection();
		
		String sql = "Insert into transcript(idStudent, idTest, score, usedTime) values(?,?,?,?)";
		
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			
			pst.setInt(1, transcript.getIdStudent());
			pst.setString(2, transcript.getIdTest());
			pst.setDouble(3, transcript.getScore());
			pst.setString(4, transcript.getUsedTime());
			
			pst.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Insert fail");
		}
	}
}
