/**
 * 
 */
package org.campustalk.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.campustalk.Settings;

/**
 * CampusTalk v0.1
 * 
 * @author Kushal Pandya < https://github.com/kushalpandya >
 * License: LGPLv3.
 * 
 * Database Interaction Class.
 */
public class DatabaseManager
{
	private String DRIVER;
	private String DATABASEURL;
	
	private Connection CON;
	
	/**
	 * Initializes this DatabaseManager object.
	 */
	public DatabaseManager()
	{
		DRIVER = Settings.DATABASEDRIVER;
		DATABASEURL = Settings.DATABASEURL;
	}
	
	/**
	 * Opens database connection with CampusTalk database.
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public void open() throws SQLException, ClassNotFoundException
	{
		Class.forName(DRIVER);
		this.CON = DriverManager.getConnection(DATABASEURL);
	}
	
	/**
	 * Closes this database connection with CampusTalk database.
	 * @throws SQLException
	 */
	public void close() throws SQLException
	{
		this.CON.close();
	}
}
