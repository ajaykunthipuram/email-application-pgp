package in.ajay;

import java.io.IOException;
import java.util.Base64;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register-servlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try{
			String username = request.getParameter("username");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String[] keyPair = KeyPairGenerate.generateKeyPair();
			
				
			
			
			
			User user=new User();
			user.setUsername(username);
			user.setEmail(email);
			user.setPassword(password);
			user.setPu(keyPair[0]);
			user.setPr(keyPair[1]);
			
			//DB CALL
			JDBCRegisterService.registerUser(user);
			System.out.println("got it");
			//HibernateRegisterService.registerUser(user);
			
			System.out.println("returned");
			response.sendRedirect("login.jsp");
			//response.getWriter().append("Served at: ").append(request.getContextPath());
		}catch(Exception e){
			e.printStackTrace();
			session.setAttribute("ERROR","User already exist");
			response.sendRedirect("register.jsp");
		}
		
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
