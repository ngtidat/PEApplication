package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {
	
    static final String url = "jdbc:mysql://localhost:3306/peapplication";
    static final String user = "root";
    static final String password = "dat0975776721@";
    
    public static Connection getJDBCConnection(){
            try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				return DriverManager.getConnection(url, user, password);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        return null;
    }
    
    public static void main(String[] args){
        Connection con = getJDBCConnection();
        
        if (con != null){
            System.out.println("Thanh cong");
        }
        else System.out.println("That bai");
    }
}
