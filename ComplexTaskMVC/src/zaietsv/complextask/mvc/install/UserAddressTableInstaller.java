package zaietsv.complextask.mvc.install;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import zaietsv.complextask.mvc.connect.ConnectorTool;

public class UserAddressTableInstaller extends TableInstaller {
	
	private final static String table = "user_address";
	
	/**
	 * @param connection
	 */
	public UserAddressTableInstaller(Connection connection) {
		super(connection, table);
	}

	/* (non-Javadoc)
	 * @see zaietsv.complextask.mvc.install.AbstractInstaller#install()
	 */
	@Override
	public boolean install() throws SQLException {
		System.out.println("public static boolean install() throws SQLException { ");
		Boolean success = false;
		String sql = "create table if not exists `" + table + "` (" +
				"user_id bigint unsigned not null unique," +
				"address_id bigint unsigned not null," +
				"primary key (user_id, address_id)," +
				"foreign key(user_id) references `user`(id) on delete restrict on update cascade," +
				"foreign key(address_id) references `address`(id) on delete restrict on update cascade" +
			");";
		try (PreparedStatement ps = connection.prepareStatement(sql);) {
			//ps.set(1, table);
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
		try (ConnectorTool ct = new ConnectorTool("jdbc:mysql://localhost:3306/music_users", "tomcat", "tacmot");) {
			
			//ct.connect();
			UserAddressTableInstaller uati = new UserAddressTableInstaller(ct.connect());
			uati.install();
			uati.isInstalled();
			uati.unInstall();
			uati.isInstalled();
			//if (!isSchemaInstalled()) {
				//install();
			//}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
