package org.campustalk.sql;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

import org.campustalk.entity.CampusTalkComment;

public class dbComment extends DatabaseManager {
	
	public int createCommentOnPost(CampusTalkComment objComment){
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

}
