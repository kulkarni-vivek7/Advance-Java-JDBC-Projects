package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connection4 {

	Connection c = null;
	
	public Connection4() {
		// TODO Auto-generated constructor stub
		
		try {

			// Add your Database name in Database_name field
			// Add Your MySql username in mysql_username field
			// Add your MySql password in mysql_password field
			
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Database_name", "mysql_username", "mysql_password");
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
