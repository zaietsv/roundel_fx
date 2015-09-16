package zaietsv.complextask.mvc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zaietsv.complextask.mvc.connect.ConnectorTool;
import zaietsv.complextask.mvc.dao.UserDAO;
import zaietsv.complextask.mvc.instance.User;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/UserServlet" })
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		try (ConnectorTool ct = new ConnectorTool("jdbc:mysql://localhost:3306/music_users", "tomcat", "tacmot");) {
			UserDAO udao = new UserDAO(ct.connect());
			ArrayList<User> users = udao.readAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String action = request.getParameter("action");
		switch (action) {
		case "insert":
			
			break;
		case "update":
			
			break;
		case "delete":
			
			break;

		default:
			writer.print("");
			break;
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
