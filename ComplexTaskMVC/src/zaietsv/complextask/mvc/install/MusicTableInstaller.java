package zaietsv.complextask.mvc.install;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import zaietsv.complextask.mvc.connect.ConnectorTool;

public class MusicTableInstaller extends TableInstaller {
	
	private final static String table = "music";
	
	/**
	 * @param connection
	 */
	public MusicTableInstaller(Connection connection) {
		super(connection, table);
	}

	public boolean install() throws SQLException {
		System.out.println("public static boolean install() throws SQLException { ");
		Boolean success = false;
		String sql = "create table if not exists `" + table + "` (" +
				"id serial primary key," +
				"name varchar (80)," +
				"rating int" +
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
			throw new SQLException("Cannot perform install(). `" + table + "` installation is cancelled.", e);
		}
		System.out.println("return " + success + " }");
		return success;
	}

	
	public static void main(String[] args) {
		try {
			ConnectorTool ct = new ConnectorTool("jdbc:mysql://localhost:3306/music_users", "tomcat", "tacmot");
			//ct.connect();
			MusicTableInstaller mti = new MusicTableInstaller(ct.connect());
			mti.install();
			mti.isInstalled();
			mti.unInstall();
			mti.isInstalled();
			//if (!isSchemaInstalled()) {
				//install();
			//}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
