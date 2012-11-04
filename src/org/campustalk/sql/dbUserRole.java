package org.campustalk.sql;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.campustalk.entity.CampusTalkRoles;
import org.campustalk.entity.CampusTalkUserRoles;

public class dbUserRole extends DatabaseManager {

public CampusTalkUserRoles getRoleById(CampusTalkUserRoles objUserRole){
		
		try {
			this.open();
			CallableStatement pst = CON.prepareCall("{ call getUserRoleById(?)}");
			pst.setInt(1,objUserRole.getUserid());
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				objUserRole.setUserid(rs.getInt("userid"));
				dbRoles objDbRole = new dbRoles();
				objUserRole.role.setRoleid(rs.getInt("roleid"));
				objUserRole.role= objDbRole.getRoleById(objUserRole.role);
				
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return objUserRole ;
		
	}
}
