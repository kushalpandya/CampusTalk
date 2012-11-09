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

	public boolean AddEvent(Date eDate, Date eTime, String eName, String eDesc) {
		boolean rtnTemp = false;
		try {

			System.out.println(eDate);
			java.sql.Date sqlDate = new java.sql.Date(eDate.getTime());
			System.out.println("DbEvent");
			System.out.println(sqlDate);
			this.open();
			CallableStatement csSql = CON
					.prepareCall("{call insertEvent(?,?,?,?)}");

			/**
			 * CREATE PROCEDURE `campustalk`.`insertGroup`( IN name
			 * VARCHAR(100), IN duration TEXT)
			 */

			csSql.setDate(1, sqlDate);
			csSql.setString(2, eName);
			csSql.setString(3, eDesc);
			csSql.registerOutParameter(4, Types.BOOLEAN);
			csSql.executeUpdate();

			boolean rtnFlag = csSql.getBoolean(5);
			csSql.close();
			return rtnTemp;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return rtnTemp;
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

//	public JSONArray getEventDetails(int id) {
//		JSONArray event_arr = new JSONArray();
//		try {
//			this.open();
//
//			CallableStatement csSql = CON
//					.prepareCall("{call getEventDetails(?)}");
//			csSql.setInt(1, id);
//			ResultSet rs = csSql.executeQuery();
//
//			JSONObject temp;
//
//			while (rs.next()) {
//				temp = new JSONObject();
//
//				temp.put("eid", rs.getInt("eventid"));
//				temp.put("title", rs.getString("title"));
//				temp.put("desc", rs.getString("description"));
//				temp.put("place", rs.getString("place"));
//				temp.put("fdate", rs.getDate("fromdate"));
//				temp.put("tdate", rs.getDate("todate"));
//				event_arr.put(temp);
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return event_arr;
//	}
//*/
}
