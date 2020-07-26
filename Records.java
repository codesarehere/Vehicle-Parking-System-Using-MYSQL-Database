
import javax.swing.*; 
import java.sql.*; 

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime; 
 class Records {  

	static Connection con;
	static Statement st;
	static ResultSet rs; 
	
			String name;
			String vehicle;
			String mobile;
			String cnic;
			String reg;
			int fee;
			JTable jt;
			
	String data[][];
    JFrame f; 
	
    Records(){ 
	TotalVehicle obj = new TotalVehicle();
	int count = obj.totalNum();
	data = new String[count][7];
	name=vehicle=mobile=cnic=reg = "";
	fee = 0;
	
	try{
			
			String driverClassName="com.mysql.cj.jdbc.Driver";
			String url="jdbc:mysql://localhost:3306/customer";
			String user = "root";
			String password = "camelCase";
			Class.forName(driverClassName).newInstance();
			System.out.println("Successfully");
			con = DriverManager.getConnection(url,user,password);
			st = con.createStatement();
			rs = st.executeQuery("select * from customers");
	
			int i, j;
			i = j = 0;
			while(rs.next())
			{
				
				name = rs.getString(2);
				vehicle = rs.getString(3);
				mobile = rs.getString(4);
				cnic = rs.getString(5);
				reg = rs.getString(6);
				fee = rs.getInt(7);
				
				data[i][j++] = name;
				data[i][j++] = vehicle;
				data[i][j++] = mobile;
				data[i][j++] = cnic;
				data[i][j++] = reg;
				data[i][j++] = Integer.toString(fee);
				data[i][j++] = getDateNow();
				i++; j=0;
				
				
			}
			
			
			
			
			st.close();
			con.close();
			}
			catch(Exception ae){
				ae.printStackTrace();
			}
			




	
    f=new JFrame("Records");    
   
    String column[]={"Name","Vehicle","Mobile","CNIC","Reg","Fee","Date & Time"};
    jt= new JTable(data,column);    
    jt.setBounds(30,40,400,400); 
	JScrollPane sp=new JScrollPane(jt);    
    f.add(sp);          
    f.setSize(700,300);  
	f.setLocationRelativeTo(null);
    f.setVisible(true);    
} 


  public static String getDateNow() {  
   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
   LocalDateTime now = LocalDateTime.now();
   return dtf.format(now);
  }   
}  