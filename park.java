
import javax.sound.sampled.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

class park extends JFrame implements ActionListener{
	static JTextField txt1;
	static JTextField txt2;
	static JTextField txt3;
	static JTextField txt4;
	static JLabel lebel1;
	static JLabel lebel2;
	static JLabel lebel3;
	static JLabel lebel4;
	static String name;
	static String number;
	static String cnic;
	static String mobile;
	static String vehicle;
	static int	fee=0;
	static JButton btn1;
	static JLabel veh;
	static JButton back;
	static File btn;
	
	
	
	
	park(String title){
		
		super(title);
		park.vehicle = title;
		btn = new File("btn.wav");
		if (title.equals("Bike"))
		{
			ImageIcon pVeh = new ImageIcon("bike.png");
			veh = new JLabel(pVeh);
			
			park.fee = 20;
		}
		else if (title.equals("Car"))
		{
			ImageIcon pVeh = new ImageIcon("car.png");
			veh = new JLabel(pVeh);
			
			park.fee = 50;
		}
		else if (title.equals("Riksha"))
		{
			ImageIcon pVeh = new ImageIcon("riksha.png");
			veh = new JLabel(pVeh);

			park.fee = 40;
		}
		else if (title.equals("Bus"))
		{
			ImageIcon pVeh = new ImageIcon("bus.png");
			veh = new JLabel(pVeh);
	
			park.fee = 80;
		}
		veh.setBounds	(130,80,110,100);
		add(veh);
		
		
		ImageIcon icon = new ImageIcon("icon.png");
		setIconImage(icon.getImage());	
		
		
		
		setSize(800,600);
		setLayout(null);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		lebel1 = new JLabel ("Enter Driver Name");
		lebel1.setBounds(150,210,200,30);
		lebel1.setForeground (Color.white);
		
		txt1 = new JTextField();
		txt1.setBounds(330,210,200,30);
		
		lebel2 = new JLabel ("Enter Vehicle Reg.Number");
		lebel2.setBounds(150,260,200,30);
		lebel2.setForeground (Color.white);
		
		txt2 = new JTextField();
		txt2.setBounds(330,260,200,30);
		
		lebel3 = new JLabel ("Enter CNIC");
		lebel3.setBounds(150,310,200,30);
		lebel3.setForeground (Color.white);
		
		txt3 = new JTextField();
		txt3.setBounds(330,310,200,30);
		
		lebel4 = new JLabel ("Enter Mobile Number (11 digits)");
		lebel4.setBounds(150,360,200,30);
		lebel4.setForeground (Color.white);
		
		txt4 = new JTextField();
		txt4.setBounds(330,360,200,30);
		
		JLabel background;
		
		ImageIcon pback = new ImageIcon("back.png");
		back = new JButton("back",pback);
		back.setBounds	(20,20,70,60);
		back.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
		back.setContentAreaFilled(false);
		
		ImageIcon img = new ImageIcon("screen.jpg");
		background = new JLabel("",img,JLabel.CENTER);
		background.setBounds(0,0,800,600);
		btn1 = new JButton("Park It");
		btn1.setBounds(305,410,200,30);
		btn1.setBorderPainted(false);
		btn1.setContentAreaFilled(false);
		btn1.setOpaque(true);
		
		back.addActionListener(this);
		btn1.addActionListener(this);
		
		
		add(btn1);
		add(txt1);
		add(txt2);
		add(txt3);
		add(txt4);
		add(lebel1);
		add(lebel2);
		add(lebel3);
		add(lebel4);
		add(back);
		add(background);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		playSound(btn);
		name = park.txt1.getText();
		number = park.txt2.getText();
		cnic = park.txt3.getText();
		mobile = park.txt4.getText();
		JButton obj = (JButton)ae.getSource();
		String im = obj.getText();
		if(im.equals("Park It")){
			if (txt1.getText().equals("") || txt2.getText().equals("") || txt3.getText().equals("")|| txt4.getText().equals(""))
			{
				JOptionPane.showMessageDialog(this,"All fields must be filled");
			}
			else if(mobile.length()!=11){
				JOptionPane.showMessageDialog(this,"Mobile numbers should be 11 characters");
			}
			else
			{
				database();
				this.setVisible(false);
				new Screen("Main");
			}
		}
		else if(im.equals("back")){
			this.setVisible(false);
			new Screen("Main");
		}
		
	}
	
	

	

	
	public static void database(){
			String driverClassName,url,user,password;
			Connection connection;
			Statement statement;
			ResultSet rs;
			JFrame obj = new JFrame();
			
			
		try{
			driverClassName="com.mysql.cj.jdbc.Driver";
			url="jdbc:mysql://localhost:3306/customer";
			user = "root";
			password = "camelCase";
			Class.forName(driverClassName).newInstance();
			connection = DriverManager.getConnection(url,user,password);
			statement = connection.createStatement();
			rs = statement.executeQuery("select reg,cnic from customers where cnic ='"+cnic+"' or reg = '"+number+"'");
			if(rs.next()){
				JOptionPane.showMessageDialog(obj,"Registration and Cnic may already exists in database \n or may be mobile number contain more or less characters");
				
			}
			else{
				statement.executeUpdate("INSERT INTO customers(name,vehicle, mobile,cnic,reg,fee) VALUES ('"+name+"','"+park.vehicle+"','"+mobile+"','"+cnic+"','"+number+"','"+park.fee+"')");
				JOptionPane.showMessageDialog(obj,"Successfully Parked");
			}
			rs.close();
			statement.close();
			connection.close();
			
						
			}
			catch(Exception ae){
				System.out.println(ae);
				//ae.printStackTrace();
			}
	}
	
	public void playSound(File sound)
	{
		try
		{
				Clip clip = AudioSystem.getClip();
				clip.open(AudioSystem.getAudioInputStream(sound));
				clip.start();
				Thread.sleep(250);
		}
		catch(Exception e)
		{
			
		}
	}
		
}

	
