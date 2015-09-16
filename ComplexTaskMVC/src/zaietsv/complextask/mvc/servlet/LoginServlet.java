package zaietsv.complextask.mvc.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@WebServlet(
		description = "Performs login procedures", 
		urlPatterns = { "/Login" }, 
		initParams = { 
				@WebInitParam(name = "logina", value = "2", description = "dfdsf")
		})
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet()");
		String role = request.getParameter("role");
		RequestDispatcher rd = request.getRequestDispatcher(role + ".jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost()");
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String rules = request.getParameter("rules");
		System.out.println("login=" + login);
		System.out.println("password=" + password);
		System.out.println("rules=" + rules);
		RequestDispatcher rd;
		if (login.isEmpty() || password.isEmpty()) {
			System.out.println("IF");
			rd = request.getRequestDispatcher("login.jsp");
		} else {
			System.out.println("ELSE");
			rd = request.getRequestDispatcher(rules + ".jsp");
		}
		rd.forward(request, response);
	}

}
