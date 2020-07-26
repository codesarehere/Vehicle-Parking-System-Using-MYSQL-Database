import java.sql.*;
class test{
	public static void main(String args[]){
		
		try{
			String str1,str2,str3,str4,str5,str6;
			str1 = "Nouman Ali";
			str5 = "123-a";
			str3 = "253765347";
			str4 = "723847289378-9";
			str2 = "car";
		
			String driverClassName="com.mysql.cj.jdbc.Driver";
			String url="jdbc:mysql://localhost:3306/customer";
			String user = "root";
			String password = "camelCase";
			Class.forName(driverClassName).newInstance();
			System.out.println("Successfully");
			Connection connection = DriverManager.getConnection(url,user,password);
			Statement statement = connection.createStatement();
			String sql  = "INSERT INTO customers(Name,Vehicle, Mobile,CNIC,Reg,Fee) VALUE ('"+str1+"','"+str2+"','"+str3+"','"+str4+"','"+str5+"',400)";
			statement.executeUpdate(sql);
			statement.close();
			connection.close();
		}catch(Exception ae){
			System.out.println(ae);
		
		}
		
	}
}