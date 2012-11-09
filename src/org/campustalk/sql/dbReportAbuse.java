package org.campustalk.sql;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.campustalk.entity.CampusTalkReportAbuse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class dbReportAbuse extends DatabaseManager
{

	public boolean insertReportAbuse(CampusTalkReportAbuse objRptAbuse)
	{
		//CampusTalkReportAbuse

		boolean rtnFlag = false;
		try
		{
			this.open();
			CallableStatement pst = CON
					.prepareCall("{call insertReportAbuses(?,?,?,?)}");

			pst.setInt(1, objRptAbuse.getPostId());
			pst.setInt(2, objRptAbuse.getUserId());
			pst.setString(3, objRptAbuse.getDetail());
			pst.registerOutParameter(4, Types.BOOLEAN);
			pst.execute();
			rtnFlag = pst.getBoolean(4);
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return !rtnFlag;

	}

	public void BlockPost(int id) throws SQLException, ClassNotFoundException
	{
		try
		{
			this.open();

			CallableStatement csSql = CON.prepareCall("{ call blockPost(?)}");
			csSql.setInt(1, id);
			csSql.executeUpdate();
			csSql.close();

		}
		catch (ClassNotFoundException | SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public JSONArray getViewUser(int pid) {
		JSONArray jArray = new JSONArray();
		try
		{
			this.open();

			CallableStatement pst = CON
					.prepareCall("{ call getReportedUser(?)}");
			pst.setInt(1, pid);

			ResultSet rs = pst.executeQuery();
			JSONObject jTemp;
			while (rs.next())
			{
				jTemp = new JSONObject();
				jTemp.put("id", rs.getInt("userid"));
				jTemp.put("url", rs.getString("pictureurl"));
				jTemp.put("fnm", rs.getString("firstname"));
				jTemp.put("email", rs.getString("email"));

				jArray.put(jTemp);
				System.out.println(rs.getInt("userid"));

			}
		}
		catch (ClassNotFoundException | SQLException | JSONException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jArray;
	}

	public JSONArray getBlockUser() 
	{
		JSONArray jArray = new JSONArray();
		try
		{
			this.open();

			CallableStatement pst = CON.prepareCall("{ call getBlockUser()}");

			ResultSet rs = pst.executeQuery();
			JSONObject jTemp;
			while (rs.next())
			{
				jTemp = new JSONObject();
				jTemp.put("id", rs.getInt("id"));
				jTemp.put("url", rs.getString("pictureurl"));
				jTemp.put("fnm", rs.getString("firstname"));
				jTemp.put("email", rs.getString("email"));

				jArray.put(jTemp);
				System.out.println(rs.getInt("id"));

			}
		}
		catch (ClassNotFoundException | SQLException | JSONException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jArray;
	}

	public void BlockUser(int id, int pid) throws SQLException, ClassNotFoundException
	{
		try
		{
			this.open();

			CallableStatement csSql = CON.prepareCall("{ call blockUser(?,?)}");
			csSql.setInt(1, id);
			csSql.setInt(2, pid);
			csSql.executeUpdate();
			csSql.close();

		}
		catch (ClassNotFoundException | SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void UnblockUser(int id) 
	{
		try
		{
			this.open();

			CallableStatement csSql = CON
					.prepareCall("{ call getUnblockUser(?)}");
			csSql.setInt(1, id);
			csSql.executeUpdate();
			csSql.close();

		}
		catch (ClassNotFoundException | SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void Check( int pid, String rstatus) 
	{
		try
		{
			this.open();

			CallableStatement csSql = CON
					.prepareCall("{ call updateReportStatusForPost(?,?)}");
			
			csSql.setInt(1, pid);
			csSql.setString(2, rstatus);
			csSql.executeUpdate();
			csSql.close();

		}
		catch (ClassNotFoundException | SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public JSONArray getReportAbusesData() 
	{

		JSONArray jArray = new JSONArray();
		try
		{
			this.open();

			CallableStatement pst = CON
					.prepareCall("{ call getReportOnAllPost()}");

			ResultSet rs = pst.executeQuery();
			JSONObject jTemp;
			while (rs.next())
			{
				jTemp = new JSONObject();
				jTemp.put("id", rs.getInt("id"));
				jTemp.put("url", rs.getString("pictureurl"));
				jTemp.put("fnm", rs.getString("firstname"));
				jTemp.put("email", rs.getString("email"));
				jTemp.put("lnm", rs.getString("lastname"));
				jTemp.put("comment", rs.getString("cntcomment"));
				jTemp.put("nreport", rs.getString("cntnewreport"));
				jTemp.put("rreport", rs.getString("cntreadreport"));
				jTemp.put("pid", rs.getString("postid"));

				jArray.put(jTemp);
				System.out.println(rs.getInt("id"));
			}

		}
		catch (ClassNotFoundException | SQLException | JSONException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return jArray;

	}
}
