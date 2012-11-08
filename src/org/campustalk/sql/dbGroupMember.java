package org.campustalk.sql;


import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class dbGroupMember extends DatabaseManager {
	
	public dbGroupMember() {
		// TODO Auto-generated constructor stub
		super();
	}

	
public boolean AddGroupMember(String groupname,String email,String position)throws SQLException, ClassNotFoundException {
		
		this.open();
		CallableStatement csSql = CON
				.prepareCall("{call insertGroup(?,?,?)}");

		/**
		 * CREATE PROCEDURE `campustalk`.`insertGroupMember`( 
		 * IN name VARCHAR(100), IN email VARCHAR(50),IN position VARCHAR(100), IN status CHAR(2))
		 */
		csSql.setString(1,groupname);
		csSql.setString(2,email);
		csSql.setString(3,position);
		csSql.registerOutParameter(4, Types.BOOLEAN);
		
		csSql.executeUpdate();
		
		boolean rtnFlag=csSql.getBoolean(3);
		csSql.close();
		return rtnFlag;
	}
	
	
public void EditGroupMember(int groupid,int userid,String position,String status)throws SQLException, ClassNotFoundException {
		
		this.open();
		CallableStatement csSql = CON
				.prepareCall("{call updateGroupMember(?,?,?,?)}");

		csSql.setInt(1,groupid);
		csSql.setInt(2, userid);
		csSql.setString(3,position);
		csSql.setString(4,status);
		
	
		csSql.executeUpdate();
		csSql.close();
	}
	
	
public void DeleteGroupMember(int groupid,int userid) throws SQLException, ClassNotFoundException{

	
	this.open();
	CallableStatement csSql = CON
			.prepareCall("{call deleteGroupMembers(?,?)}");
	csSql.setInt(1, groupid);		
	csSql.setInt(2,userid);
	
	csSql.executeQuery();
}

public ResultSet getGroupMemberData(int groupid)throws SQLException, ClassNotFoundException 
{
	
	this.open();
	CallableStatement csSql = CON
			.prepareCall("{call getGroupMemberData(?)}");
	
	csSql.setInt(1, groupid);
	ResultSet rs = csSql.executeQuery();
	while(rs.next())
	{
		System.out.println("Hello");
	}
	return rs;		
}	
	
}
