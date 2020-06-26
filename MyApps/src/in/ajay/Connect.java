package in.ajay;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {
	public static boolean connect(){
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");//.newInstance();
			//Connection con = DriverManager.getConnection(url,username,password);
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
}
