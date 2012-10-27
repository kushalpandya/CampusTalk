/**
 * 
 */
package org.campustalk;

import java.util.ResourceBundle;

/**
 * CampusTalk v0.1
 * 
 * @author Kushal Pandya < https://github.com/kushalpandya >
 * License: LGPLv3.
 * 
 * Global class to get configuration settings from Config.properties
 */
public class Settings
{
	private static final ResourceBundle RB = ResourceBundle.getBundle("org.campustalk.Config");

	//Put Properties from Config file here.
	
	/**
	 * Driver Name for underlying Database in use.
	 */
	public static final String DATABASEDRIVER = RB.getString("DatabaseDriver");
	
	/**
	 * URL of database along with user name and password parameters.
	 */
	public static final String DATABASEURL = RB.getString("DatabaseURL");
	public static final String DBUSERNAME= RB.getString("dbUserName");
	public static final String DBPASSWORD = RB.getString("dbPassword");
	public static final String APPURL=RB.getString("AppURL"); 
}
