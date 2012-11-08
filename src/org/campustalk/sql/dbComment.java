package org.campustalk.sql;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.campustalk.entity.CampusTalkComment;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class dbComment extends DatabaseManager {

	public int createCommentOnPost(CampusTalkComment objComment) {
		int rCount = 0;
		try {
			this.open();
			CallableStatement pst = CON
					.prepareCall("{call CommentOnPost(?,?,?,?)}");

			pst.setInt(1, objComment.getPostId());
			pst.setInt(2, objComment.getUserId());
			pst.setString(3, objComment.getDetail());
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

	public boolean deleteCommentOfPost(int commentId, int userId) {
		try {
			this.open();
			CallableStatement pst = CON
					.prepareCall("{call CommentDeleteOfPost(?,?,?)}");
			pst.setInt(1, commentId);
			pst.setInt(2, userId);
			pst.registerOutParameter(3, Types.BOOLEAN);
			pst.execute();
			return pst.getBoolean(3);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public JSONArray getCommentForPost(int postid, int skipRow, int nRow ,int userid) {

		JSONArray jArray = new JSONArray();
		try {
			this.open();

			CallableStatement pst = CON
					.prepareCall("{ call getCommentOnPost(?,?,?,?)}");
			pst.setInt(1, postid);
			pst.setInt(2, skipRow);
			pst.setInt(3, nRow);
			pst.setInt(4, userid);
			ResultSet rs = pst.executeQuery();
			JSONObject jTemp;
			while (rs.next()) {
				jTemp = new JSONObject();
				jTemp.put("commentid", rs.getInt("commentid"));
				jTemp.put("userid", rs.getInt("userid"));
				jTemp.put("detail", rs.getString("detail"));
				jTemp.put("entTime", rs.getTimestamp("entTime"));
				jTemp.put("firstname", rs.getString("firstname"));
				jTemp.put("lastname", rs.getString("lastname"));
				jTemp.put("pictureurl", rs.getString("pictureurl"));
				jArray.put(jTemp);
			}

		} catch (ClassNotFoundException | SQLException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return jArray;
	}

}
