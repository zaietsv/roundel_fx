package zaietsv.complextask.mvc.servlet;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zaietsv.complextask.mvc.connect.ConnectorTool;
import zaietsv.complextask.mvc.dao.AddressDAO;
import zaietsv.complextask.mvc.dao.UserDAO;
import zaietsv.complextask.mvc.holder.AddressHolder;
import zaietsv.complextask.mvc.holder.UserHolder;
import zaietsv.complextask.mvc.instance.User;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO udao;
	private AddressDAO adao;
	ConnectorTool ct;
	UserHolder userHolder;
	AddressHolder addressHolder;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        super();
        userHolder = new UserHolder();
        addressHolder = new AddressHolder();
        try {
        	ct = new ConnectorTool("jdbc:mysql://localhost:3306/music_users", "tomcat", "tacmot");
        	udao = new UserDAO(ct.connect());
        	adao = new AddressDAO(ct.connect());
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("DOGET");
		String action = request.getParameter("action");
		action = action == null ? "" : action;
		RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
		switch (action) {
		/*case "insert":
			System.out.println("case 'insert':");
			String login = request.getParameter("login");
			String password = request.getParameter("password");
			String email = request.getParameter("email");;
			User newUser = new User(login, password, email);
			udao.insert(newUser);
			break;*/
		case "edit":
			System.out.println("case 'edit':");
			
			break;
		/*case "update":
			System.out.println("case 'update':");
			break;*/
		case "delete":
			System.out.println("case 'delete':");
			udao.delete(Long.parseLong(request.getParameter("id")));
			
			
			break;

		default:
			System.out.println("default:");
			break;
		}
		
		
		userHolder.setList(udao.readAll());
		addressHolder.setList(adao.readAll());
		request.getSession().setAttribute("userHolder", userHolder);
		request.getSession().setAttribute("addressHolder", addressHolder);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("DOPOST");
		String action = request.getParameter("action");
		action = action == null ? "" : action;

		RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
		switch (action) {
		case "insert":
			System.out.println("case 'insert':");
			String login = request.getParameter("login");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			User newUser = new User(login, password, email);
			udao.insert(newUser);
			
			break;
		case "edit":
			System.out.println("case 'edit':");
			break;
		case "update":
			System.out.println("case 'update':");
			Long id = Long.parseLong(request.getParameter("id"));
			login = request.getParameter("login");
			password = request.getParameter("password");
			email = request.getParameter("email");
			User updateUser = new User(id, login, password, email);
			System.out.println(updateUser);
			System.out.println(udao.update(updateUser));
			break;
		case "delete":
			
			break;

		default:
			System.out.println("default:");
			break;
		}
		
		userHolder.setList(udao.readAll());
		addressHolder.setList(adao.readAll());
		request.getSession().setAttribute("userHolder", userHolder);
		request.getSession().setAttribute("addressHolder", addressHolder);
		
		rd.forward(request, response);
	}

}
