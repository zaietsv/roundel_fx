package zaietsv.complextask.mvc.install;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TableInstaller extends AbstractInstaller {
	
	private final String table;
	
	/**
	 * @param connection
	 */
	public TableInstaller(Connection connection, String table) {
		super(connection);
		this.table = table;
	}

	/* (non-Javadoc)
	 * @see zaietsv.complextask.mvc.install.AbstractInstaller#install()
	 */
	@Override
	public boolean install() throws SQLException {
		System.out.println("public static boolean install() throws SQLException { ");
		Boolean success = false;
		String sql = "create table if not exists `" + table + "` (" +
				"id serial primary key," +
				");";
		try (PreparedStatement ps = connection.prepareStatement(sql);) {
			//ps.set(1, dbName);
				int rows = ps.executeUpdate();
				;
				System.out.println("rows=" + rows + ";");
				if (rows == 1 && ps.getWarnings() == null) {
					success = true;
				} else {
					//System.out.println(ps.getWarnings().getErrorCode());
					System.out.println(ps.getWarnings());
				}
		} catch (SQLException e) {
			throw new SQLException("Cannot perform install(). " + table + " Installation is cancelled.", e);
		}
		System.out.println("return " + success + " }");
		return success;
	}
	
	/* (non-Javadoc)
	 * @see zaietsv.complextask.mvc.install.AbstractInstaller#isInstalled()
	 */
	@Override
	public boolean isInstalled() throws SQLException {
		System.out.println("public boolean isInstalled() throws SQLException { ");
		Boolean isInstalled = false;
		String sql = "SHOW TABLES LIKE ?";
		try (PreparedStatement ps = connection.prepareStatement(sql);){
			ps.setString(1, table);
			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					isInstalled = true;
				}
			} catch (SQLException e) {
				throw new SQLException("'" + table + "' request is failed", e);
			}
		} catch (SQLException e) {
			throw new SQLException("'" + table + "' connection was lost! Result undefined.", e);
		}
		System.out.println("return " + isInstalled + " }");
		return isInstalled;
	}
	
	/* (non-Javadoc)
	 * @see zaietsv.complextask.mvc.install.AbstractInstaller#unInstall()
	 */
	@Override
	public boolean unInstall() throws SQLException {
		System.out.println("public static boolean uninstall() throws SQLException { ");
		Boolean success = false;
		String sql = "DROP TABLE IF EXISTS " + table;
		//String sql = "DROP TABLE IF EXISTS ?";
		try (PreparedStatement ps = connection.prepareStatement(sql);) {
			//ps.setString(1, table);
			boolean result = ps.execute();
				System.out.println("result=" + result + ";");
				if (result && ps.getWarnings() == null) {
					success = true;
				} else {
					//System.out.println(ps.getWarnings().getErrorCode());
					System.out.println(ps.getWarnings());
				}
		} catch (SQLException e) {
			throw new SQLException("Cannot perform unInstall()! '" + table + "' uninstallation is cancelled.", e);
		}
		System.out.println("return " + success + " }");
		return success;
	}
	
	

	/* (non-Javadoc)
	 * @see zaietsv.complextask.mvc.install.AbstractInstaller#getInfo()
	 */
	@Override
	public String getInfo() {
		String sql = "DESCRIBE " + table;
		return table;
	}

	/**
	 * @return the table name
	 */
	public String getTable() {
		return table;
	}

	/*
	public static void main(String[] args) {
		try {
			ConnectorTool ct = new ConnectorTool("jdbc:mysql://localhost:3306/music_users", "tomcat", "tacmot");
			//ct.connect();
			TableInstaller uti = new TableInstaller(ct.connect());
			uti.install();
			uti.isInstalled();
			uti.unInstall();
			uti.isInstalled();
			//if (!isSchemaInstalled()) {
				//install();
			//}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}*/
}
