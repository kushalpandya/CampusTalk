package org.campustalk.sql;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class dbRoles extends DatabaseManager {
	
	public dbRoles(){
		super();
	}

	//public CampusTalkGroup objGroup;
	
	
	public void AddRoles(String rolesname)throws SQLException, ClassNotFoundException {
		
		this.open();
		CallableStatement csSql = CON
				.prepareCall("{call insertRoles(?)}");

		/**
		 * CREATE PROCEDURE `campustalk`.`insertRoles`( 
		 * IN name VARCHAR(20), IN duration TEXT, IN status CHAR(2))
		 */
		csSql.setString(1,rolesname);
		
	
		csSql.executeUpdate();
		csSql.close();
	}
	
	
public void EditRoles(int rolesid,String rolesname)throws SQLException, ClassNotFoundException {
		
		this.open();
		CallableStatement csSql = CON
				.prepareCall("{call updateRoles(?,?)}");

		
		csSql.setInt(1,rolesid);
		csSql.setString(2,rolesname);
		
		csSql.executeUpdate();
		csSql.close();
	}
	
	
public void DeleteRoles(int rolesid) throws SQLException, ClassNotFoundException{

	
	this.open();
	CallableStatement csSql = CON
			.prepareCall("{call deleteRoles(?)}");
	csSql.setInt(1, rolesid);		
	
	csSql.executeQuery();
}

public ResultSet getRolesData()throws SQLException, ClassNotFoundException 
{
	
	this.open();
	CallableStatement csSql = CON
			.prepareCall("{call getRolesData()}");
	ResultSet rs = csSql.executeQuery();
	return rs;		
}	
}