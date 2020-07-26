import javax.swing.*;
import java.sql.*;

class Reset extends JFrame
{
	
	static Connection connection;
	static Statement statement;
	static ResultSet resultset;

	
	Reset()
	{
		database();
		JOptionPane.showMessageDialog(this,"Software is Reseted");
	}
	
	
		public static void database(){
		try{
			String driverClassName="com.mysql.cj.jdbc.Driver";
			String url="jdbc:mysql://localhost:3306/customer";
			String user = "root";
			String password = "camelCase";
			Class.forName(driverClassName).newInstance();
			System.out.println("Successfully");
			Connection connection = DriverManager.getConnection(url,user,password);
			Statement statement = connection.createStatement();
			statement.executeUpdate("truncate table customers");
			statement.close();
			connection.close();
			}
			catch(Exception ae){
				ae.printStackTrace();
			}
	}
		
}



