package connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Admin;
import model.Questions;
import model.Rank;
import model.Student;
import model.Test;

public class DbManager {
	public List<Admin> getAllAdmin(){
		List<Admin> administrators = new ArrayList<>();
		Connection con = JDBCConnection.getJDBCConnection();
		if (con != null) System.out.println("Connect to admin successfull");
		
		String sql = "Select *from admin";
		
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				Admin a = new Admin();
				
				a.setIdAdmin(rs.getString("idAdmin"));
				a.setUserName(rs.getString("userName"));
				a.setPassword(rs.getString("password"));
				
				administrators.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return administrators;
	}
	
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
	
	public List<Student> getAllStudent(){
		List<Student> students = new ArrayList<>();
		Connection con = JDBCConnection.getJDBCConnection();
		
		String sql = "Select *from student";
		
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				Student student = new Student();
				
				student.setIdStudent(rs.getInt("idStudent"));
				student.setName(rs.getNString("name"));
				student.setSchool(rs.getNString("school"));
				student.setYearOfBirth(rs.getInt("yOB"));
				student.setUserName(rs.getString("userName"));
				student.setPassword(rs.getString("password"));
				
				students.add(student);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return students;
	}
	
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
				t.setDefaultTime(rs.getString("defaultTime"));
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
				t.setDefaultTime(rs.getString("defaultTime"));
				t.setNumberOfQuestions(rs.getInt("numberOfQuestions"));
				t.setIdAdmin(rs.getString("idAdmin"));
				
				test.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return test;
	}
	
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
