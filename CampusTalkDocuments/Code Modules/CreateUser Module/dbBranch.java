package org.campustalk.sql;

import org.campustalk.entity.CampusTalkBranch;
//import org.campustalk.entity.CampusTalkUsers;
//import com.mysql.jdbc.CallableStatement;
//import org.campustalk.ArrayList;
//import org.campustalk.ResultSet;
import java.util.*;
import java.sql.*;


public class dbBranch extends DatabaseManager {
	
	public dbBranch()
	{	super();	}
		
	public CampusTalkBranch objBranch[]=new CampusTalkBranch[10];
	
	public CampusTalkBranch[] getBranchData()throws SQLException, ClassNotFoundException 
	{
		
		this.open();
		CallableStatement csSql = CON
				.prepareCall("{call getAllBranchData()}");
		ResultSet rs = csSql.executeQuery();
		ArrayList<CampusTalkBranch> branch = new ArrayList<>();
		CampusTalkBranch temp_branch;
		
		while(rs.next())
		{
			temp_branch = new CampusTalkBranch();
			temp_branch.setBranchId(rs.getInt("branchid"));
			temp_branch.setName(rs.getString("name"));
			temp_branch.setDuration(rs.getInt("duration"));
			
			branch.add(temp_branch);
		}
		
		return branch.toArray(new CampusTalkBranch[branch.size()]);
		
		/*while(rs.next())
		{
			objBranch[i].setName(rs.getString("name"));
			i++;
		}		
		return objBranch;*/
	}
}
