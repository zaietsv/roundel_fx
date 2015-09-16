package zaietsv.complextask.mvc.login;

import java.sql.SQLException;

public interface Logger {
	
	public boolean login() throws SQLException;
	
	public boolean logout() throws SQLException;

}
