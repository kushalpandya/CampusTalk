package org.campustalk.sql;

import org.campustalk.entity.CampusTalkRoles;
//import com.mysql.jdbc.CallableStatement;
//import org.campustalk.ArrayList;
//import org.campustalk.ResultSet;
import java.util.*;
import java.sql.*;



public class dbRole extends DatabaseManager {
	
	public dbRole()
	{	super();	}
		
	public CampusTalkRoles objrole[]=new CampusTalkRoles[10];
	
	public CampusTalkRoles[] getRoleData()throws SQLException, ClassNotFoundException 
	{
		this.open();
		CallableStatement csSql = CON
				.prepareCall("{call getAllRoleData()}");
		ResultSet rs = csSql.executeQuery();
		ArrayList<CampusTalkRoles> role = new ArrayList<>();
		CampusTalkRoles temp_role;
		
		while(rs.next())
		{
			temp_role = new CampusTalkRoles();
			//temp_role.setroleId(rs.getInt("roleid"));
			temp_role.setRoleId(rs.getInt("roleid"));
			temp_role.setName(rs.getString("name"));			
			
			role.add(temp_role);
		}
		
		return role.toArray(new CampusTalkRoles[role.size()]);
		
		/*while(rs.next())
		{
			objrole[i].setName(rs.getString("name"));
			i++;
		}		
		return objrole;*/
	}	

}
