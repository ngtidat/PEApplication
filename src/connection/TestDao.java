package connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Test;

public class TestDao {
	
	public List<Test> getAllTestWithSubject(String subject, int year){
		List<Test> test = new ArrayList<>();
		Connection con = JDBCConnection.getJDBCConnection();
		if (con != null) System.out.println("Connect to tests successfull");
		
		String sql = "Select *from test where nameSubject = ? and releaseYear = ?";
		
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			
			preparedStatement.setString(1,subject);
			preparedStatement.setInt(2, year);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				Test t = new Test();
				
				t.setIdTest(rs.getString("idTest"));
				t.setTitleTest(rs.getNString("titleTest"));
				t.setNameSubject(rs.getNString("nameSubject"));
				t.setReleaseYear(rs.getInt("releaseYear"));
				t.setSchool(rs.getNString("school"));
				t.setNumberOfQuestions(rs.getInt("numberOfQuestions"));
				t.setIdAdmin(rs.getString("idAdmin"));
				
				test.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return test;
	}
	
	public List<Test> getAllTestWithId(String idTest){
		List<Test> test = new ArrayList<>();
		Connection con = JDBCConnection.getJDBCConnection();
		if (con != null) System.out.println("Connect to tests successfull");
		
		String sql = "Select *from test where idTest = ?";
		
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			
			preparedStatement.setString(1,idTest);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				Test t = new Test();
				
				t.setIdTest(rs.getString("idTest"));
				t.setTitleTest(rs.getNString("titleTest"));
				t.setNameSubject(rs.getNString("nameSubject"));
				t.setReleaseYear(rs.getInt("releaseYear"));
				t.setSchool(rs.getNString("school"));
				t.setNumberOfQuestions(rs.getInt("numberOfQuestions"));
				t.setIdAdmin(rs.getString("idAdmin"));
				
				test.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return test;
	}
}
