
import java.sql.*;
class TotalVehicle {
	
	static Connection connection;
	static Statement statement;
	static ResultSet resultset; 
	int count;
	
		
		
	public int totalNum(){
		try{
		
			String driverClassName="com.mysql.cj.jdbc.Driver";
			String url="jdbc:mysql://localhost:3306/customer";
			String user = "root";
			String password = "camelCase";
			Class.forName(driverClassName).newInstance();
			System.out.println("Successfully");
			connection = DriverManager.getConnection(url,user,password);
			statement = connection.createStatement();
			resultset = statement.executeQuery("SELECT * FROM customers");
			while(resultset.next()){
				count++;
			}
			resultset.close();
			statement.close();
			connection.close();
		}catch(Exception ae){
			ae.printStackTrace();
		}
		return count;
	}
		
		
	}	
