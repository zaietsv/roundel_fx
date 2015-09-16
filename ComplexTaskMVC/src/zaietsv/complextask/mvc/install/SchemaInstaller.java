package zaietsv.complextask.mvc.install;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SchemaInstaller extends AbstractInstaller {
	
	private String schema;
	
	/**
	 * @param connection
	 * @param dbName
	 */
	public SchemaInstaller(Connection connection, String schema) {
		super(connection);
		this.schema = schema;
	}

	public boolean install() throws SQLException {
		System.out.println("public static boolean install() throws SQLException { ");
		Boolean success = false;
		//String sql = "CREATE SCHEMA IF NOT EXISTS ? DEFAULT CHARACTER SET utf8";
		//String sql = "CREATE SCHEMA IF NOT EXISTS music_users DEFAULT CHARACTER SET = utf8";
		String sql = "CREATE SCHEMA IF NOT EXISTS `" + schema + "` DEFAULT CHARACTER SET utf8";
		try (PreparedStatement ps = connection.prepareStatement(sql);) {
			//ps.set(1, schema);
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
			throw new SQLException("Cannot perform install(). " + schema + " Installation is cancelled.", e);
		}
		System.out.println("return " + success + " }");
		return success;
	}
	
	public boolean isInstalled() throws SQLException {
		System.out.println("public static boolean isDBInstalled() throws SQLException { ");
		Boolean isInstalled = false;
		//String sql = "show schemas like '" + dbName + "'";
		String sql = "SHOW SCHEMAS LIKE ?";
		try (PreparedStatement ps = connection.prepareStatement(sql);){
			ps.setString(1, schema);
			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					isInstalled = true;
				}
			} catch (SQLException e) {
				throw new SQLException(schema + " Request is failed", e);
			}
		} catch (SQLException e) {
			throw new SQLException(schema + " Connection was lost", e);
		}
		System.out.println("return " + isInstalled + " }");
		return isInstalled;
	}
	
	public boolean unInstall() throws SQLException {
		System.out.println("public static boolean uninstall() throws SQLException { ");
		Boolean success = false;
		String sql = "DROP SCHEMA IF EXISTS " + schema;
		try (PreparedStatement ps = connection.prepareStatement(sql);) {
				boolean result = ps.execute();
				System.out.println("result=" + result + ";");
				if (result && ps.getWarnings() == null) {
					success = true;
				} else {
					//System.out.println(ps.getWarnings().getErrorCode());
					System.out.println(ps.getWarnings());
				}
		} catch (SQLException e) {
			throw new SQLException("Cannot perform unInstall()! '" + schema + "' uninstallation is cancelled.", e);
		}
		System.out.println("return " + success + " }");
		return success;
	}

	/**
	 * @return the schema name
	 */
	public String getSchema() {
		return schema;
	}

	/**
	 * @param schema the schema to set
	 */
	public void setSchema(String schema) {
		this.schema = schema;
	}
}
