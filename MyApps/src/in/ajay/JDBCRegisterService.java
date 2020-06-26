package in.ajay;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

//import com.mysql.cj.xdevapi.Statement;

public class JDBCRegisterService {
	
	/*private static String driver = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://125.564.12.1:3306/test/";
	private static String username = "mysql";
	private static String password = "";*/
	
	/*static String url = "jdbc:mysql://localhost:3306/";
	static String username = "root";
	static String password = "";*/
	
	public static void registerUser(User user) throws Exception{
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");//.newInstance();
			//Connection con = DriverManager.getConnection(url,username,password);
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");
			
			System.out.println("connected");
			String sql = "INSERT INTO public (USERNAME,PASSWORD,EMAIL,PUBKEY,PRIKEY) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			System.out.println("prepared");
			
			ps.setString(1,user.getUsername());
			System.out.println("ok username");
			ps.setString(2,user.getPassword());
			System.out.println("ok password");
			ps.setString(3,user.getEmail());
			System.out.println("ok email");
			ps.setString(4,user.getPu());
			System.out.println("ok pu");
			ps.setString(5,user.getPr());
			System.out.println("ok pr");
			
			ps.executeUpdate();
			System.out.println("executed");
			return;
			
			/*Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection con = DriverManager.getConnection(url, username, password);

            Statement stt = (Statement) con.createStatement();

            
            stt.execute("CREATE DATABASE IF NOT EXISTS test");
            stt.execute("USE test");

            
            stt.execute("DROP TABLE IF EXISTS people");
            stt.execute("CREATE TABLE people (" +
                    "id BIGINT NOT NULL AUTO_INCREMENT,"
                    + "fname VARCHAR(25),"
                    + "lname VARCHAR(25),"
                    + "PRIMARY KEY(id)"
                    + ")");
            stt.execute("INSERT INTO people (fname, lname) VALUES" + 
                    "('Joe', 'Bloggs'), ('Mary', 'Bloggs'), ('Jill', 'Hill')");

            
            ResultSet res = stt.executeQuery("SELECT * FROM people WHERE lname = 'Bloggs'");

            
            while (res.next())
            {
                System.out.println(res.getString("fname") + " " + res.getString("lname"));
            }
            System.out.println("");*/
			
		}catch(Exception e){
			throw e;
		}
	}
}
/*
 /**
 * Imports all entries from the java.sql library. 
 * Improve on this by only importing needed classes. 
 * E.g.:
 * import java.sql.Connection
 * import java.sql.Statement
 * ...
 */
/*import java.sql.*;

public class MainApp
{
    public static void main(String[] args)
    {

        *//**
         * 3306 is the default port for MySQL in XAMPP. Note both the 
         * MySQL server and Apache must be running. 
         *//*
        String url = "jdbc:mysql://localhost:3306/";

        *//**
         * The MySQL user.
         *//*
        String user = "root";

        *//**
         * Password for the above MySQL user. If no password has been 
         * set (as is the default for the root user in XAMPP's MySQL),
         * an empty string can be used.
         *//*
        String password = "";

        try
        {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection con = DriverManager.getConnection(url, user, password);

            Statement stt = con.createStatement();

            *//**
             * Create and select a database for use. 
             *//*
            stt.execute("CREATE DATABASE IF NOT EXISTS test");
            stt.execute("USE test");

            *//**
             * Create an example table
             *//*
            stt.execute("DROP TABLE IF EXISTS people");
            stt.execute("CREATE TABLE people (" +
                    "id BIGINT NOT NULL AUTO_INCREMENT,"
                    + "fname VARCHAR(25),"
                    + "lname VARCHAR(25),"
                    + "PRIMARY KEY(id)"
                    + ")");

            *//**
             * Add entries to the example table
             *//*
            stt.execute("INSERT INTO people (fname, lname) VALUES" + 
                    "('Joe', 'Bloggs'), ('Mary', 'Bloggs'), ('Jill', 'Hill')");

            *//**
             * Query people entries with the lname 'Bloggs'
             *//*
            ResultSet res = stt.executeQuery("SELECT * FROM people WHERE lname = 'Bloggs'");

            *//**
             * Iterate over the result set from the above query
             *//*
            while (res.next())
            {
                System.out.println(res.getString("fname") + " " + res.getString("lname"));
            }
            System.out.println("");

            *//**
             * Same as the last query, but using a prepared statement. 
             * Prepared statements should be used when building query strings
             * from user input as they protect against SQL injections
             *//*
            PreparedStatement prep = con.prepareStatement("SELECT * FROM people WHERE lname = ?");
            prep.setString(1, "Bloggs");

            res = prep.executeQuery();
            while (res.next())
            {
                System.out.println(res.getString("fname") + " " + res.getString("lname"));
            }

            *//**
             * Free all opened resources
             *//*
            res.close();
            stt.close();
            prep.close();
            con.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}*/

 
