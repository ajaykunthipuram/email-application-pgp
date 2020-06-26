package in.ajay;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login-servlet")
public class LoginServlet extends HttpServlet {
	static String currentUser;
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		try{
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			boolean auth = AuthenticationService.validateUserJDBC(email,password);
			if(auth){
				currentUser = email;
				session.setAttribute("AUTH", true);
				session.setAttribute("USER", currentUser);
				
				request.getRequestDispatcher("/home-servlet").forward(request,response);
				
				
				//response.sendRedirect("home.jsp");
			}else{
				session.setAttribute("ERROR","Authentication Failed!!");
				response.sendRedirect("register.jsp");
			}
			response.sendRedirect("home.jsp");
		}catch(Exception e){
			e.printStackTrace();
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
