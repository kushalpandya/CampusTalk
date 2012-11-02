package org.campustalk.sql;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.campustalk.entity.CampusTalkPost;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.appengine.labs.repackaged.org.json.JSONArray;

public class dbPost extends DatabaseManager {

	public int createPost(CampusTalkPost objPost) {
		int rCount = 0;
		try {
			this.open();
			CallableStatement pst = CON
					.prepareCall("{call PostInsert(?,?,?,?)}");
			// `insertPost`(IN userid INT,IN content TEXT, IN ptype CHAR(2),OUT
			// postid INT)
			pst.setInt(1, objPost.getUserid());
			pst.setString(2, objPost.getDetail());
			pst.setString(3, objPost.getType());
			pst.registerOutParameter(4, Types.INTEGER);
			pst.execute();
			rCount = pst.getInt(4);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rCount;

	}

	public JSONObject getPostForUser(String email, int skipRow, int nRow) {
		JSONObject jResponse = new JSONObject();
		JSONArray jArray = new JSONArray();
		try {
			this.open();

			CallableStatement pst = CON
					.prepareCall("{ call getPostsForUser(?,?,?)}");
			pst.setString(1, email);
			pst.setInt(2, skipRow);
			pst.setInt(3, nRow);
			ResultSet rs = pst.executeQuery();
			JSONObject jTemp;
			while (rs.next()) {
				jTemp = new JSONObject();
				jTemp.put("postid", rs.getInt("postid"));
				jTemp.put("userid", rs.getInt("userid"));
				jTemp.put("detail", rs.getString("detail"));
				jTemp.put("type", rs.getString("type"));
				jTemp.put("entTime", rs.getDate("entTime"));
				jTemp.put("firstname", rs.getString("firstname"));
				jTemp.put("lastname", rs.getString("lastname"));
				jTemp.put("pictureurl", rs.getString("pictureurl"));
				jTemp.put("nocomment", rs.getInt("nocomment"));
				jTemp.put("lastmodifytime", rs.getDate("lastmodifytime"));

				jArray.put(jTemp);
			}
			jResponse.put("posts", jArray);
		} catch (ClassNotFoundException | SQLException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return jResponse;
	}

}
