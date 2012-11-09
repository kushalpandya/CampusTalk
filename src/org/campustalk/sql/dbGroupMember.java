package org.campustalk.sql;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class dbGroupMember extends DatabaseManager
{

	public dbGroupMember()
	{
		// TODO Auto-generated constructor stub
		super();
	}

	public boolean AddGroupMember(int groupname, String email,
			String position) 
	{
		boolean rflag=false;
		try{
		this.open();
		System.out.println(groupname + " " +  email + " " + position);
		CallableStatement csSql = CON.prepareCall("{call insertGroupMember(?,?,?,?)}");
		csSql.setInt(1, groupname);
		csSql.setString(2, email);
		csSql.setString(3, position);
		csSql.registerOutParameter(4, Types.BOOLEAN);
		
		csSql.executeUpdate();
		rflag= csSql.getBoolean(4);
		csSql.close();
		}
		catch (Exception e){
			e.printStackTrace();
				
		}
		return rflag;
	}

	public void EditGroupMember(int groupid, int userid, String position,
			String status) throws SQLException, ClassNotFoundException
	{

		this.open();
		CallableStatement csSql = CON
				.prepareCall("{call updateGroupMember(?,?,?,?)}");

		csSql.setInt(1, groupid);
		csSql.setInt(2, userid);
		csSql.setString(3, position);
		csSql.setString(4, status);

		csSql.executeUpdate();
		csSql.close();
	}

	public void DeleteGroupMember(int groupid, int userid) throws SQLException,
			ClassNotFoundException
	{

		this.open();
		CallableStatement csSql = CON
				.prepareCall("{call deleteGroupMember(?,?)}");
		csSql.setInt(1, groupid);		
		csSql.setInt(2,userid);
		
		csSql.executeQuery();
	}

	public JSONArray getGroupMemberData(int groupid) throws SQLException,
			ClassNotFoundException, JSONException
	{
		JSONArray jArray = new JSONArray();
		try {
			this.open();

			CallableStatement pst = CON
					.prepareCall("{ call getGroupMemberData(?)}");
			pst.setInt(1, groupid);
			
			ResultSet rs = pst.executeQuery();
			JSONObject jTemp;
			while (rs.next()) {
				jTemp = new JSONObject();
				jTemp.put("userid",rs.getInt("userid"));
				jTemp.put("email", rs.getString("email"));
				jTemp.put("name", rs.getString("name"));
				jTemp.put("role", rs.getString("position"));
				jArray.put(jTemp);
			}
			
		} catch (ClassNotFoundException | SQLException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return jArray;
	}

}
