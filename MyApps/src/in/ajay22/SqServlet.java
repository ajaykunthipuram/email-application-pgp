package in.ajay22;

import java.io.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;

@WebServlet("/sq")
public class SqServlet extends HttpServlet {
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException{
		/*int k=(int)req.getAttribute("k");
		PrintWriter out=res.getWriter();
		out.println("result is "+k);
		System.out.println("sq is called");*/
		
		/*int k=Integer.parseInt(req.getParameter("k"));
		k*=k;
		PrintWriter out=res.getWriter();
		out.println("Result is "+k);*/
		
		/*HttpSession session = req.getSession();
		int k=(int)session.getAttribute("k");
		k*=k;
		PrintWriter out=res.getWriter();
		out.println("result is "+k);
		System.out.println("sq is called");*/
		//session.removeAttribute("k");
		
		int k=0;
		Cookie cookies[] = req.getCookies();
		for(Cookie c : cookies){
			if(c.getName().equals("k")){
				k=Integer.parseInt(c.getValue());
				break;
			}
		}
		k*=k;
		PrintWriter out=res.getWriter();
		out.println("result is "+k);
		
		
	}
}
