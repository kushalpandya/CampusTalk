package org.campustalk.sql;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.json.JSONArray;
import org.json.JSONObject;

//import org.campustalk.entity.CampusTalkGroup;


//import org.campustalk.entity.CampusTalkGroup;

public class dbGroup extends DatabaseManager {
	
	public dbGroup(){
		super();
	}

	//public CampusTalkGroup objGroup;
	
	

	public boolean AddGroup(String groupname,String description)throws SQLException, ClassNotFoundException {
		
		this.open();
		CallableStatement csSql = CON
				.prepareCall("{call insertGroup(?,?,?)}");

		/**
		 * CREATE PROCEDURE `campustalk`.`insertGroup`( 
		 * IN name VARCHAR(100), IN duration TEXT)
		 */
		csSql.setString(1,groupname);
		csSql.setString(2,description);
		csSql.registerOutParameter(3, Types.BOOLEAN);
		csSql.executeUpdate();
		
		
		boolean rtnFlag=csSql.getBoolean(3);
		csSql.close();
		return rtnFlag;
	}
	
	
public boolean EditGroup(int groupid,String groupname,String description,String status)throws SQLException, ClassNotFoundException {
		
		this.open();
		CallableStatement csSql = CON
				.prepareCall("{call updateGroupById(?,?,?,?,?)}");

		csSql.setInt(1,groupid);
		csSql.setString(2,groupname);
		csSql.setString(3,description);
		csSql.setString(4,status);
		csSql.registerOutParameter(5, Types.BOOLEAN);
	
		csSql.executeUpdate();
		
		boolean rtnFlag=csSql.getBoolean(5);
		csSql.close();
		return rtnFlag;
	}
	
	
public void DeleteGroup(int id) throws SQLException, ClassNotFoundException{

	
	this.open();
	CallableStatement csSql = CON
			.prepareCall("{call deleteGroupData(?)}");
	csSql.setInt(1, id);		
	
	csSql.executeQuery();
}

public JSONArray getGroupData() 
{
	JSONArray group_arr = new JSONArray();
	try{
	this.open();
	CallableStatement csSql = CON
			.prepareCall("{call getGroupData()}");
	ResultSet rs = csSql.executeQuery();
	
	
	JSONObject temp;
	while(rs.next())
	{
		temp = new JSONObject();
		temp.put("groupid", rs.getInt("groupid"));
		temp.put("name", rs.getString("name"));
		temp.put("description", rs.getString("description"));
		temp.put("status", rs.getString("status"));
	
		group_arr.put(temp);					
	}
	} catch (Exception e){
		e.printStackTrace();
		
	}	
	return group_arr;
	
}	

/*public CampusTalkGroup[] getAllGroupData() throws SQLException, ClassNotFoundException 
{
	
	this.open();
	CallableStatement csSql = CON
			.prepareCall("{call getAllGroupData()}");
	ResultSet rs = csSql.executeQuery();
	ArrayList<CampusTalkGroup> Group = new ArrayList<>();
	CampusTalkGroup temp_Group;
	
	while(rs.next())
	{
		temp_Group = new CampusTalkGroup();
		temp_Group.setGroupid(rs.getInt("Groupid"));
		temp_Group.setName(rs.getString("name"));
		temp_Group.setDuration(rs.getInt("duration"));
		temp_Group.setStatus(rs.getString("status"));
		
		Group.add(temp_Group);
	}
	
	return Group.toArray(new CampusTalkGroup[Group.size()]);
	
	/*while(rs.next())
	{
		objGroup[i].setName(rs.getString("name"));
		i++;
	}		
	return objGroup;
}*/


}



