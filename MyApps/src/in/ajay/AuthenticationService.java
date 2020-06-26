package in.ajay;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AuthenticationService {
	public static boolean validateUserJDBC(String email,String password){
	   try{
		   Class.forName("com.mysql.cj.jdbc.Driver");//.newInstance();
			//Connection con = DriverManager.getConnection(url,username,password);
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");
			
			System.out.println("connected");
		 
		        
	        String sql = "SELECT * FROM PUBLIC WHERE email = ? and password = ?";
	        PreparedStatement statement = con.prepareStatement(sql);
	        statement.setString(1, email);
	        statement.setString(2, password);
	 
	        ResultSet result = statement.executeQuery();
	 
	       // User user = null;
	        
	        if (result.next()) {
	        	return true;
	           /* user = new User();
	            user.setFullname(result.getString(""));
	            user.setEmail(email);*/
	        }
	 
	        //con.close();
	   }catch(Exception e){
		   e.printStackTrace();
	   }
		
	 
	        return false;
	}
	public static boolean validateUserHBM(){
		return false;
	}
	
}
