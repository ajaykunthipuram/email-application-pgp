package in.ajay;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.io.*;

public class JDBCMessage {

	private static final String String = null;
	//private static final String String = null;
	static String privateKey;
	static String publicKey;

	public static boolean message(Message message) throws ClassNotFoundException, SQLException{
		try{
			//System.out.println("got");
			
			Class.forName("com.mysql.cj.jdbc.Driver");//.newInstance();
			//Connection con = DriverManager.getConnection(url,username,password);
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");
			
			System.out.println("connected");
			//System.out.println(LoginServlet.currentUser);
			//GET SENDER'S PRIVATE-KEY
			String sql1 = "SELECT PRIKEY FROM PUBLIC WHERE EMAIL = ?";
			PreparedStatement ps1 = con.prepareStatement(sql1);
			ps1.setString(1,message.getFrom());
			
			ResultSet rs1 = ps1.executeQuery();
			
			//System.out.println("pri query ok");
			
			//SENDER'S PRIVATE-KEY
			
			rs1.next();
			//System.out.println("yep ok");
			privateKey = (String)rs1.getString(1);
			//System.out.println("yep ok");
			System.out.println("PRI--->"+privateKey);
			
				
			
			
			//GET RECEIVER'S PUBLIC-KEY
			String sql2 = "SELECT PUBKEY FROM PUBLIC WHERE EMAIL = ?";
			PreparedStatement ps2 = con.prepareStatement(sql2);
			ps2.setString(1, message.getTo());
			ResultSet rs2 = ps2.executeQuery();
			
			//System.out.println("pub query ok");
			
			//RECEIVER'S PUBLIC-KEY
			rs2.next();
			publicKey = rs2.getString(1);
			System.out.println("PUB---->"+publicKey);
			
			//privateKey = "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3ofZ";
			
			//publicKey = "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dvWL";
			//MAKE AGREEMENT
			ArrayList<byte[]> msg = DH.makeAgreement(publicKey,privateKey,message.getMessage());
			//System.out.println("msg is---> "+msg);
			
			
			String sql = "INSERT INTO MESSAGES (FROMID,TOID,MESSAGE,ENKEY,MD,ML,DL,KL,TIME,SMSG) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,message.getFrom());
            ps.setString(2,message.getTo());
            //System.out.println("ok");
            ps.setBinaryStream(3,new ByteArrayInputStream(msg.get(1)),msg.get(1).length);
            //System.out.println("ok");
            ps.setBinaryStream(4,new ByteArrayInputStream(msg.get(0)),msg.get(0).length);
            //System.out.println("ok");
            ps.setBinaryStream(5,new ByteArrayInputStream(msg.get(2)),msg.get(2).length);
            //System.out.println("ok");
            ps.setInt(6,Integer.parseInt(new String(msg.get(3))));
            //System.out.println("ok");
            
            ps.setInt(7,0);
            //System.out.println("ok");
            ps.setInt(8,0);
            //System.out.println("ok");
            java.util.Date dt = new java.util.Date();

            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            
            String currentTime = sdf.format(dt);
            ps.setString(9,currentTime);
            ps.setBinaryStream(10, new ByteArrayInputStream(message.getMessage().getBytes()), message.getMessage().getBytes().length);
            
            
            ps.executeUpdate();
            //System.out.println("okk");
			System.out.println("executed");
			//Check.check(message.getTo());
			
			/*String msg = PgpDataEncryptor.encryptAndSign(message.getMessage(),publicKey,privateKey);
			System.out.println("msg---->>"+msg);*/
			return true;
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
}
