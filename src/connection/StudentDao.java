package connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Student;

public class StudentDao {
	
	public List<Student> getAllStudent(){
		List<Student> students = new ArrayList<>();
		Connection con = JDBCConnection.getJDBCConnection();
		if (con != null) System.out.println("Successfull");
		
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
}
