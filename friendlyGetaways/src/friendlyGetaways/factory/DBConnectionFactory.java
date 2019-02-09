package friendlyGetaways.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnectionFactory {
public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Connection con = getConnection();
		
		//create statement object
		Statement stmt = con.createStatement();
		
		//String query="CREATE TABLE users(user_id INTEGER PRIMARY KEY,NAME VARCHAR(20) NOT NULL,email VARCHAR(254) NOT NULL,PASSWORD VARCHAR(30) NOT NULL,phone_no VARCHAR(20) NOT NULL,interests VARCHAR(100))";
		String query="select * from activity";
		//execute Query
		ResultSet rs = stmt.executeQuery(query);
		//stmt.executeUpdate(query);
		while(rs.next()){
			System.out.println(rs.getString(2)+" "+rs.getString(3));
		}
//		String query = "insert into users (name,email,password,phone_no,interests) values(?,?,?,?,?)";
//		PreparedStatement pstmt = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
//		System.out.println("yo");
//		pstmt.setString(1, "Emly");
//		pstmt.setString(2, "emly@gmail.com");
//		pstmt.setString(3,"emly123");
//		pstmt.setString(4, "3523481834");
//		pstmt.setString(5, "diving");
//		pstmt.executeUpdate();
		con.close();
	}
	

	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		try{
		//Loading Driver Class
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		//create connection object
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@oracle.cise.ufl.edu:1521:orcl","adhiraj","Rage1061");
		//Connection con = DriverManager.getConnection("jdbc:oracle:thin:@oracle.cise.ufl.edu:1521:orcl","nakhe93","Adhiraj1061.");
		return con;
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
		return null;
	}
}
