package org.campustalk.sql;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;

//import org.campustalk.entity.CampusTalkGroup;

//import org.campustalk.entity.CampusTalkGroup;

public class dbEvent extends DatabaseManager {

	public dbEvent() {
		super();
	}

	// public CampusTalkGroup objEvent;

	public boolean AddEvent(Date fDate, Date tDate, String eName, String eDesc,String ePlace,int userId) {
		boolean rtnTemp = false;
		try {

	
			this.open();
			CallableStatement csSql = CON
					.prepareCall("{call insertEvents(?,?,?,?,?,?,?)}");

			/**
			 * CREATE PROCEDURE `campustalk`.`insertGroup`( IN name
			 * VARCHAR(100), IN duration TEXT)
			 */

			csSql.setDate(1, new java.sql.Date(fDate.getTime()));
			csSql.setDate(2, new java.sql.Date(tDate.getTime()));
			csSql.setString(3, eName);
			csSql.setString(4, eDesc);
			csSql.setString(5, ePlace);
			csSql.setInt(6, userId);
			
			csSql.registerOutParameter(7, Types.BOOLEAN);
			csSql.executeUpdate();

			rtnTemp= csSql.getBoolean(7);
			csSql.close();
			return rtnTemp;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return rtnTemp;
	}

	public void JoinEvent(int eId, int userId) {
		boolean rtnTemp = false;
		try {

	
			this.open();
			CallableStatement csSql = CON
					.prepareCall("{call joinEvents(?,?)}");

			/**
			 * CREATE PROCEDURE `campustalk`.`insertGroup`( IN name
			 * VARCHAR(100), IN duration TEXT)
			 */

			csSql.setInt(1, eId);
			csSql.setInt(2, userId);
			csSql.executeUpdate();

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public JSONArray getEventData(Date eDate) {
	JSONArray event_arr = new JSONArray();
		try {
			this.open();
			
			CallableStatement csSql = CON
					.prepareCall("{call getEventByDate(?)}");
			csSql.setDate(1, new java.sql.Date(eDate.getTime()));

			ResultSet rs = csSql.executeQuery();

			JSONObject temp;

			while (rs.next()) {
				temp = new JSONObject();

				temp.put("eid", rs.getInt("eventid"));
				temp.put("title", rs.getString("title"));
				temp.put("desc", rs.getString("description"));
				temp.put("place", rs.getString("place"));
				temp.put("fdate", rs.getDate("fromdate"));
				temp.put("tdate", rs.getDate("todate"));
				event_arr.put(temp);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return event_arr;
	}

public JSONArray getEventDetails(int id, int uId) {
		JSONArray event_arr = new JSONArray();
		try {
			this.open();

			CallableStatement csSql = CON
					.prepareCall("{call getEventDetails(?, ?)}");
			csSql.setInt(1, id);
			csSql.setInt(2, uId);
			ResultSet rs = csSql.executeQuery();

			JSONObject temp;

			while (rs.next()) {
				temp = new JSONObject();

				temp.put("eid", rs.getInt("eventid"));
				temp.put("title", rs.getString("title"));
				temp.put("desc", rs.getString("description"));
				temp.put("place", rs.getString("place"));
				temp.put("numofattempt", rs.getInt("num_attend"));
				temp.put("status", rs.getString("status"));
				temp.put("fdate", rs.getDate("fromdate"));
				temp.put("tdate", rs.getDate("todate"));
				temp.put("joinStatus", rs.getBoolean("joinstatus"));
				event_arr.put(temp);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return event_arr;
	}

}
