package org.campustalk.sql;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

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
}
