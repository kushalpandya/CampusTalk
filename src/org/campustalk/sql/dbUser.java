package org.campustalk.sql;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Types;

import org.campustalk.entity.CampusTalkUsers;

import com.google.api.server.spi.config.ApiCacheControl.Type;

public class dbUser extends DatabaseManager {
	public dbUser() {
		super();
	}

	public CampusTalkUsers getUserDetailFromEmail(String email)
			throws SQLException, ClassNotFoundException {
		this.open();
		CallableStatement pst = CON
				.prepareCall("{call UserDetailFromEmail(?)}");
		/**
		 * UserDetailFromEmail Return Structure
		 * `id`,`email`,`password`,`registerwith
		 * `,`status`,`authstring`,`authdate`,`registerdate`,`firstname`,
		 * `lastname
		 * `,`birthdate`,`gender`,`cityid`,`branchid`,`year`,`pictureurl`
		 */
		pst.setString(1, email);
		ResultSet rs = pst.executeQuery();
		CampusTalkUsers objUser = new CampusTalkUsers();
		if (rs.next()) {
			objUser.setId(rs.getInt("id"));
			objUser.setEmail(email);
			objUser.setPassword(rs.getString("password"));
			objUser.setStatus(rs.getString("status"));
			objUser.setAuthString(rs.getString("authstring"));
			objUser.setAuthDate(rs.getTimestamp("authdate"));
			objUser.setRegisterDate(rs.getTimestamp("registerdate"));
			objUser.setFirstName(rs.getString("firstname"));
			objUser.setLastName(rs.getString("lastname"));
			objUser.setBirthDate(rs.getTimestamp("birthdate"));
			objUser.setGender(rs.getString("gender"));
			objUser.setCityId(rs.getInt("cityid"));
			objUser.setBranchId(rs.getInt("branchid"));
			objUser.setYear(rs.getInt("year"));
			objUser.setPictureUrl(rs.getString("pictureurl"));

		} else {
			objUser.setId(0);
		}
		return objUser;
	}

	public void registerNewuserDetail(CampusTalkUsers objUser)

			throws SQLException {
		PreparedStatement pst = CON
				.prepareCall("{call UserNewRegisteration(?,?,?,?,?,?,?,?)}");

		/**
		 * CREATE PROCEDURE `campustalk`.`UserNewRegisteration`( IN userid INT,
		 * IN fname VARCHAR(50), IN lname VARCHAR(50), IN gender VARCHAR(7), IN
		 * passwd VARCHAR(100), IN authstring VARCHAR(100), IN registerwith
		 * VARCHAR(20)) BEGIN
		 */
		pst.setInt(1, objUser.getId());
		pst.setString(2, objUser.getFirstName());
		pst.setString(3, objUser.getLastName());
		pst.setString(4, objUser.getGender());
		pst.setString(5, objUser.getPassword());
		pst.setString(6, objUser.getAuthString());
		pst.setString(7, objUser.getRegisterWith());
		pst.setString(8, objUser.getPictureUrl());
		pst.executeUpdate();
		pst.close();
	}
	
	public int verifyNewUser(String email,String aString) throws ClassNotFoundException, SQLException{
		this.open();
		CallableStatement pst = CON
				.prepareCall("{call UserVerifyRegistration(?,?,?)}");
		pst.setString(1, email);
		pst.setString(2, aString);
		pst.registerOutParameter(3, Types.INTEGER);
		pst.executeQuery();

		return pst.getInt(3);
	}
}
