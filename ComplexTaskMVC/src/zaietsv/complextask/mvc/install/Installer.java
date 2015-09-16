package zaietsv.complextask.mvc.install;

import java.sql.SQLException;

public interface Installer {
	
	/** 
	 * Installs a new schema or a table into a database
	 * @return true on success false on fault
	 * @throws SQLException - if a database access error occurs
	 */
	public boolean install() throws SQLException;
	
	/** 
	 * Verifies if schema or a table exists in a database
	 * @return true on success false on fault
	 * @throws SQLException - if a database access error occurs
	 */
	public boolean isInstalled() throws SQLException;
	
	/** 
	 * Uninstalls an existing schema or a table from a database
	 * @return true on success false on fault
	 * @throws SQLException - if a database access error occurs
	 */
	public boolean unInstall() throws SQLException;
	
	public String getInfo();

}
