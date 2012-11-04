package org.campustalk.sql;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.campustalk.entity.CampusTalkBranch;

public class dbBranch extends DatabaseManager {
	
	public CampusTalkBranch getBranchById(int branchId){
		CampusTalkBranch objBranch= new CampusTalkBranch();
		
		try {
			this.open();
			CallableStatement pst = CON.prepareCall("{ call getBranchById(?)}");
			pst.setInt(1,branchId);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				objBranch.setBranchId(rs.getInt("branchid"));
				objBranch.setName(rs.getString("name"));
				objBranch.setDuration(rs.getInt("duration"));
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return objBranch;
		
		
	}
}

