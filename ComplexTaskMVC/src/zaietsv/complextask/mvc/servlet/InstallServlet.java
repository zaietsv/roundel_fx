package zaietsv.complextask.mvc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zaietsv.complextask.mvc.connect.Connector;
import zaietsv.complextask.mvc.connect.ConnectorTool;
import zaietsv.complextask.mvc.install.AddressTableInstaller;
import zaietsv.complextask.mvc.install.Installer;
import zaietsv.complextask.mvc.install.InstallerTool;
import zaietsv.complextask.mvc.install.MusicTableInstaller;
import zaietsv.complextask.mvc.install.SchemaInstaller;
import zaietsv.complextask.mvc.install.RoleTableInstaller;
import zaietsv.complextask.mvc.install.UserAddressTableInstaller;
import zaietsv.complextask.mvc.install.UserMusicTableInstaller;
import zaietsv.complextask.mvc.install.UserRoleTableInstaller;
import zaietsv.complextask.mvc.install.UserTableInstaller;

/**
 * Servlet implementation class InstallServlet
 */
@WebServlet("/InstallServlet")
public class InstallServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InstallServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getParameter("action"));
		PrintWriter writer = response.getWriter();
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		final String url = "jdbc:mysql://localhost:3306";
		final String user = "tomcat";
		final String password = "tacmot";
		final String schema = "music_users";
		try {
			Connector s = new ConnectorTool(url, user, password);
			
			InstallerTool schemaInstallerTool = new InstallerTool(new ArrayList<Installer>());
			try (Connection c = new ConnectorTool(url + "/" + schema, user, password).connect();) {
				schemaInstallerTool.getInstallers().add(new SchemaInstaller(s.connect(), schema));
				schemaInstallerTool.getInstallers().add(new UserTableInstaller(c));
				schemaInstallerTool.getInstallers().add(new MusicTableInstaller(c));
				schemaInstallerTool.getInstallers().add(new RoleTableInstaller(c));
				schemaInstallerTool.getInstallers().add(new AddressTableInstaller(c));
				schemaInstallerTool.getInstallers().add(new UserMusicTableInstaller(c));
				schemaInstallerTool.getInstallers().add(new UserRoleTableInstaller(c));
				schemaInstallerTool.getInstallers().add(new UserAddressTableInstaller(c));
				
				writer.print("<h2>Music users schema installation tool</h2>");
				for (Installer inst : schemaInstallerTool.getInstallers()) {
					writer.print("<textarea rows=\"8\" cols=\"120\" readonly=\"readonly\" title=\"databaseInfo\">" + inst.getInfo() + "</textarea><br/>");
				}
				

				String action = request.getParameter("action");
				if (action == null || (action.equals("install") && schemaInstallerTool.isInstalled()) || (action.equals("uninstall") && !schemaInstallerTool.isInstalled()) ) {
					action = "";
				}
				
				switch (action) {
				case "install":
					try {
						schemaInstallerTool.install();

						//writer.print("<font color=\"magenta\">Schema '" + ((SchemaInstaller)musi).getSchema() +"' installed succesfully.</font>");
					} catch (SQLException e) {
						writer.print("Cannot install a schema '" + schema + "'! Installation failed.");
						e.printStackTrace();
					}
					break;
				case "uninstall":
					try {
						schemaInstallerTool.unInstall();
						//writer.print("<font color=\"cyan\">Schema '" + ((SchemaInstaller)musi).getSchema() +"' uninstalled succesfully.</font>");
					} catch (SQLException e) {
						e.printStackTrace();
						writer.print("Cannot uninstall a schema '" + schema + "'! Uninstallation failed.");
					}
					break;

				default:
					if (schemaInstallerTool.isInstalled()) {
						//writer.print("<font color=\"magenta\">Schema '" + ((SchemaInstaller)musi).getSchema() +"' is installed.</font>");
					} else {
						//writer.print("<font color=\"cyan\">Schema '" + ((SchemaInstaller)musi).getSchema() +"' is not installed.</font>");
					}
					break;
				}
				//writer.print("<form action=\"InstallServlet\" method=\"get\"><input type=\"submit\" name=\"action\" value=\"" + (musi.isInstalled()?"uninstall":"install") + "\"></form>");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				writer.print("Cannot connect to a database '" + url + "' under user name '" + user + "'! Connection failed.");
			} finally {
				try {
					s.close();
				} catch (Exception e) {
					writer.print("Cannot disconnect from a database '" + url + "'! Connection lost.");
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			writer.print("Cannot create a ConnectionTool for a database '" + url + "' under user name '" + user + "'! Operation cancelled.");
			e.printStackTrace();
		}
		
		//RequestDispatcher rd = request.getRequestDispatcher("install.jsp");
		//rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
