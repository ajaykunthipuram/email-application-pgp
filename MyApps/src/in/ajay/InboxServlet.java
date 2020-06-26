package in.ajay;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Servlet implementation class InboxServlet
 */
@WebServlet("/inbox-servlet")
public class InboxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");//.newInstance();
			//Connection con = DriverManager.getConnection(url,username,password);
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");
			ArrayList<Format> inList = new ArrayList<>();
			String sql1 = "SELECT * FROM MESSAGES WHERE TOID = ?" ;
			PreparedStatement ps = con.prepareStatement(sql1);
			ps.setString(1,LoginServlet.currentUser);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Format f = new Format();
				f.setId(rs.getInt("id"));
				f.setFrom(rs.getString("fromid"));
				f.setTo(rs.getString("toid"));
				f.setMessage(rs.getBytes("message"));
				f.setEnKey(rs.getBytes("enkey"));
				f.setMd(rs.getString("md"));
				f.setMl(rs.getInt("ml"));
				f.setDl(rs.getInt("dl"));
				f.setKl(rs.getInt("kl"));
				f.setTime(rs.getString("time"));
				inList.add(f);
			}
			session.setAttribute("inList",inList);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
