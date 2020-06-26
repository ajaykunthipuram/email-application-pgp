package in.ajay22;

import java.io.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/add")
public class S extends HttpServlet{
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException{
		/*int i=Integer.parseInt(req.getParameter("num1"));
		int j=Integer.parseInt(req.getParameter("num2"));
		int k=i+j;
		PrintWriter out=res.getWriter();
		out.println("<html><body bgcolor='cyan'>");
		out.println("result is " + k);
		out.println("</body></html>");
		*/
		
		//k*=k;
		//req.setAttribute("k", k);
		//RequestDispatcher rd=req.getRequestDispatcher("sq");
		//rd.forward(req, res);
		//PrintWriter out=res.getWriter();
		//out.println("result is " + k);
		
		//res.sendRedirect("sq");
		//res.sendRedirect("sq?k="+k);//url rewriting
		
		/*HttpSession session=req.getSession();
		session.setAttribute("k",k);
		res.sendRedirect("sq");*/
		
		/*Cookie cookie = new Cookie("k",k+"");
		res.addCookie(cookie);
		res.sendRedirect("sq");*/
		
		/*PrintWriter out=res.getWriter();
		out.println("Hi<br>");
		ServletContext context=getServletContext();
		String str=context.getInitParameter("name");
		out.println(str);
		str = context.getInitParameter("Phone");
		out.println(str);*/
		
		/*PrintWriter out=res.getWriter();
		out.println("Hi<br>");
		ServletConfig config=getServletConfig();
		String str = config.getInitParameter("name");
		out.println(str);*/
		
	}
}
