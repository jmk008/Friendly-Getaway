package friendlyGetaways.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import friendlyGetaways.dao.MethodsImpl;
import friendlyGetaways.dto.UserLogin;
import friendlyGetaways.dto.UserRegisteration;

/**
 * Servlet implementation class ControllerServelet
 */
@WebServlet(name = "ControllerServlet", urlPatterns = { "/ControllerServlet" })
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//HttpSession session = request.getSession();
		String username = request.getParameter("uname");
		String password = request.getParameter("passw");
		MethodsImpl mi = new MethodsImpl();
		String loginOrReg = request.getParameter("loginOrRegis");
		System.out.println(loginOrReg);
		response.setContentType("text/html");
		UserLogin l = new UserLogin(username, password);
		l.setName(username);
		l.setPassword(password);
		PrintWriter out = response.getWriter();
	   if(loginOrReg.equals("login")){
	   if(mi.validateUser(l)){
	   RequestDispatcher rd = request.getRequestDispatcher("http://localhost:8080/friendlyGetaways/home.jsp");
	   rd.forward(request, response);
	   }else 
		   out.println("<h1 style='color:red'>Invalid Credentials</h1>");
	   }else if (loginOrReg.equals("regis")){
		   UserRegisteration u = new UserRegisteration();
		   u.setUsername(request.getParameter("username"));
		   u.setPassword(request.getParameter("password"));
		   u.setEmail(request.getParameter("email"));
		   u.setPhone(request.getParameter("phone"));
		   u.setInterests(request.getParameter("interests"));
		   System.out.println(u);
		   if(mi.insertNewUser(u)==true) { 
			   //request.setAttribute("status", "Registration Successful!");
			   //response.sendRedirect("http://localhost:8080/friendlyGetaways/home.jsp");
			   RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
			   rd.forward(request, response);
			   //out.println("<h1 style='color:red'>Registration Succesful</h1>");
		   } else {
//			   RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
//			   rd.forward(request, response);
			   request.setAttribute("status", "Registration Failed! Please try again.");
			   //request.getRequestDispatcher("login.jsp").forward(request, response);
			   
			   response.sendRedirect("register.jsp?success=2");
			   //out.println("<h1 style='color:red'>Registartion failed. Please try again!</h1>");
		   }
		   }
	   }
	}

