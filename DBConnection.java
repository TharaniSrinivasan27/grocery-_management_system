import java.sql.*;
public class DBConnection {
	public static Connection createConnection() {
		Connection connection =null;
		try { 
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/grocery_management_system";
			String username="root";
			String password="Tharani2003";
			connection=DriverManager.getConnection(url,username,password);	
			System.out.println("Database is connected");
			}catch (Exception e) {
					System.out.println(e);
				}
				return connection;
}
}

//path=C:\Program Files\Java\jdk-22\bin
//set classpath=%classpath%;C:\Users\ADMIN\Desktop\grocery management\mysql-connector-j-8.3.0.jar;
