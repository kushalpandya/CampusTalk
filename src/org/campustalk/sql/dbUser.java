package org.campustalk.sql;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.campustalk.entity.CampusTalkUsers;


public class dbUser extends DatabaseManager {
	public dbUser() {
		super();
	}
	public CampusTalkUsers objUser;
	public CampusTalkUsers getUserDetailFromEmail(String email)
			throws SQLException, ClassNotFoundException {
		this.open();
		CallableStatement csSql = CON
				.prepareCall("{call UserDetailFromEmail(?)}");
		/**
		 * UserDetailFromEmail Return Structure
		 * `id`,`email`,`password`,`registerwith
		 * `,`status`,`authstring`,`authdate`,`registerdate`,`firstname`,
		 * `lastname
		 * `,`birthdate`,`gender`,`cityid`,`branchid`,`year`,`pictureurl`
		 */
		csSql.setString(1, email);
		ResultSet rs = csSql.executeQuery();
		objUser = new CampusTalkUsers();
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

			throws SQLException, ClassNotFoundException {
		this.open();
		CallableStatement csSql = CON
				.prepareCall("{call UserNewRegisteration(?,?,?,?,?,?,?,?)}");

		/**
		 * CREATE PROCEDURE `campustalk`.`UserNewRegisteration`( IN userid INT,
		 * IN fname VARCHAR(50), IN lname VARCHAR(50), IN gender VARCHAR(7), IN
		 * passwd VARCHAR(100), IN authstring VARCHAR(100), IN registerwith
		 * VARCHAR(20)) BEGIN
		 */
		csSql.setInt(1, objUser.getId());
		csSql.setString(2, objUser.getFirstName());
		csSql.setString(3, objUser.getLastName());
		csSql.setString(4, objUser.getGender());
		csSql.setString(5, objUser.getPassword());
		csSql.setString(6, objUser.getAuthString());
		csSql.setString(7, objUser.getRegisterWith());
		csSql.setString(8, objUser.getPictureUrl());
		csSql.executeUpdate();
		csSql.close();
	}
	
	public int verifyNewUser(String email,String aString) throws ClassNotFoundException, SQLException{
		this.open();
		CallableStatement csSql = CON
				.prepareCall("{call UserVerifyRegistration(?,?,?)}");
		csSql.setString(1, email);
		csSql.setString(2, aString);
		csSql.registerOutParameter(3, Types.INTEGER);
		csSql.executeQuery();
		int rtnTemp=csSql.getInt(3);
		csSql.close();
		return rtnTemp;
	}
	
	public boolean userLogin(String email, String password) throws SQLException, ClassNotFoundException{
		this.open();
		CallableStatement csSql = CON.prepareCall("{call UserLogin(?,?,?)}");
		csSql.setString(1, email);
		csSql.setString(2, password);
		csSql.registerOutParameter(3, Types.BOOLEAN);
		ResultSet rs = csSql.executeQuery();
		if (rs.next()) {
			objUser = new CampusTalkUsers();
			objUser.setId(rs.getInt("id"));
			objUser.setEmail(email);
			objUser.setPassword(password);
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
		} 
		boolean rtnFlag=csSql.getBoolean(3);
		csSql.close();
		return rtnFlag;
	}
}