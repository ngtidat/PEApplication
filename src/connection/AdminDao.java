package connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Admin;


public class AdminDao {
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
}
