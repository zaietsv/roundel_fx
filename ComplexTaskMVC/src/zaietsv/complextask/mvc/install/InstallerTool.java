package zaietsv.complextask.mvc.install;

import java.sql.SQLException;
import java.util.ArrayList;

public class InstallerTool implements Installer {

	private ArrayList<Installer> installers;
	
	public InstallerTool() {
		// TODO Auto-generated constructor stub
	}
	
	public InstallerTool(ArrayList<Installer> installers) {
		this.installers = installers;
	}

	@Override
	public boolean install() throws SQLException {
		Boolean installed = false;
		for (Installer installer : installers) {
			installed = installer.install();
			/*if (!installed) {
				break;
			}*/
		}
		return installed;
	}

	@Override
	public boolean isInstalled() throws SQLException {
		Boolean installed = false;
		for (Installer installer : installers) {
			installed = installer.isInstalled();
			if (!installed) {
				break;
			}
		}
		return installed;
	}

	@Override
	public boolean unInstall() throws SQLException {
		Boolean installed = false;
		for (Installer installer : installers) {
			installed = installer.unInstall();
			if (!installed) {
				break;
			}
		}
		return installed;
	}
	
	@Override
	public String getInfo() {
		String msg = "";
		for (Installer installer : installers) {
			msg += installer.getInfo();
		}
		return msg;
	}

	/**
	 * @return the installers
	 */
	public ArrayList<Installer> getInstallers() {
		return installers;
	}

	/**
	 * @param installers the installers to set
	 */
	public void setInstallers(ArrayList<Installer> installers) {
		this.installers = installers;
	}
}
