package org.campustalk.sql;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class dbMessage extends DatabaseManager {

	public boolean sendMessege(int fromUserid, String toUserIds,
			String msgDetail) {

		boolean rFlag = false;
		try {
			this.open();
			CallableStatement pst = CON
					.prepareCall("{call sendMessege(?,?,?,?)}");

			pst.setInt(1, fromUserid);
			pst.setString(2, toUserIds);
			pst.setString(3, msgDetail);
			pst.registerOutParameter(4, Types.BOOLEAN);
			pst.execute();
			rFlag = pst.getBoolean(4);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rFlag;

	}

	public JSONArray getAllMessageUsersList(int userId) {
		JSONArray jArray = new JSONArray();
		try {
			this.open();

			CallableStatement pst = CON
					.prepareCall("{ call getAllMessageUsersList(?)}");
			pst.setInt(1, userId);
			ResultSet rs = pst.executeQuery();
			JSONObject jTemp;
			while (rs.next()) {
				jTemp = new JSONObject();
				jTemp.put("userid", rs.getInt("id"));
				jTemp.put("firstname", rs.getString("firstname"));
				jTemp.put("lastname", rs.getString("lastname"));
				jTemp.put("pictureurl", rs.getString("pictureurl"));
				jTemp.put("totmsg", rs.getInt("totmsg"));
				jTemp.put("unreadmsg", rs.getInt("unreadmsg"));
				jTemp.put("lastmsgtime", rs.getTimestamp("lastmsgtime"));
				jArray.put(jTemp);
			}

		} catch (ClassNotFoundException | SQLException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return jArray;
	}
	public JSONArray getAllMessageOfUser(int userId,int toUserId) {
		JSONArray jArray = new JSONArray();
		try {
			this.open();

			CallableStatement pst = CON
					.prepareCall("{ call getAllMessageForUser(?,?)}");
			pst.setInt(1, userId);
			pst.setInt(2, toUserId);
			
			ResultSet rs = pst.executeQuery();
			JSONObject jTemp;
			while (rs.next()) {
				jTemp = new JSONObject();
				jTemp.put("userid", rs.getInt("userid"));
				jTemp.put("messageid", rs.getInt("messageid"));
				jTemp.put("firstname", rs.getString("firstname"));
				jTemp.put("message", rs.getString("message"));
				jTemp.put("enttime", rs.getString("enttime"));
				jArray.put(jTemp);
			}

		} catch (ClassNotFoundException | SQLException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return jArray;
	}

}
