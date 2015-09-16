package zaietsv.complextask.mvc.connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.jdbc.Driver;

public class ConnectorTool implements Connector, AutoCloseable {
	
	private Connection connection;
	private String url;
	private String user;
	private String password;
	
	
	
	public ConnectorTool() throws SQLException {
		DriverManager.registerDriver(new Driver());
	}

	/**
	 * @param url a database url
	 * @param user a database user login
	 * @param password a database user password
	 * @throws SQLException 
	 */
	public ConnectorTool(String url, String user, String password) throws SQLException {
		this.url = url;
		this.user = user;
		this.password = password;
		DriverManager.registerDriver(new Driver());
	}

	public Connection connect() throws SQLException {
		System.out.println("public boolean connect() throws SQLException { ");
		try {
				Properties properties = new Properties();
				properties.put("user", user);
				properties.put("password", password);
				connection = DriverManager.getConnection(url, properties);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Unable to connect to a database '" + url + "' under user name '" + user + "'! connect() failed.", e);
		}
	System.out.println("return connection=" + connection + " }");
	return connection;
	}
	
	public boolean ping() {
		System.out.println("public boolean ping() { ");
		Boolean ping = false;
		try {
			if (connection instanceof Connection) {
				ping = connection.isValid(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("return " + ping + " }");
		return ping;
	}
	
	@Override
	public void close() throws SQLException {
		System.out.println("public void close() throws SQLException { ");
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Unable to disconnect from a database '" + url + "'! close() failed.", e);
		}
	System.out.println("return void }");
	return;
	}
	
	/**
	 * @return the connection
	 */
	public Connection getConnection() {
		return connection;
	}

	/**
	 * @param connection the connection to set
	 */
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * main() method foe local tests
	 * @param args
	 */
	public static void main(String[] args) {
		try (ConnectorTool ct = new ConnectorTool("jdbc:mysql://localhost:3306", "tomcat", "tacmot");) {
			ct.ping();
			ct.connect();
			ct.ping();
			ct.close();
			ct.ping();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try (ConnectorTool ct = new ConnectorTool("jdbc:mysql://localhost:3306/music_users", "tomcat", "tacmot");) {
			
			ct.ping();
			ct.connect();
			ct.ping();
			ct.close();
			ct.ping();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
