/**
 * 
 */
package zaietsv.complextask.mvc.install;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

/**
 * @author Voww
 *
 */
public abstract class AbstractInstaller implements Installer {
	
	protected Connection connection;

	/**
	 * 
	 */
	public AbstractInstaller() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param connection
	 */
	public AbstractInstaller(Connection connection) {
		this.connection = connection;
	}

	/* (non-Javadoc)
	 * @see zaietsv.complextask.mvc.install.Installer#install()
	 */
	@Override
	public abstract boolean install() throws SQLException;
	
	/* (non-Javadoc)
	 * @see zaietsv.complextask.mvc.install.Installer#isInstalled()
	 */
	@Override
	public abstract boolean isInstalled() throws SQLException;

	/* (non-Javadoc)
	 * @see zaietsv.complextask.mvc.install.Installer#unInstall()
	 */
	@Override
	public abstract boolean unInstall() throws SQLException;
	
	public String getInfo() {
		System.out.println("public String getInfo() { ");
		String msg = "databaseInfo:";
		try {
			DatabaseMetaData md = connection.getMetaData();
			msg += "\nDatabaseProductName = " + md.getDatabaseProductName();
			msg += "\nDatabaseProductVersion = " + md.getDatabaseProductVersion();
			msg += "\nDriverName = " + md.getDriverName();
			msg += "\nDriverVersion = " + md.getDriverVersion();
			msg += "\nURL = " + md.getURL();
			msg += "\nUserName = " + md.getUserName();
			//msg += "\nSchema = " + connection.getSchema();
		} catch (SQLException e) {
			msg += e.getMessage();
			e.printStackTrace();
		}
		return msg;
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

}
