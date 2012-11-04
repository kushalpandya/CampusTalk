package org.campustalk.sql;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.campustalk.entity.CampusTalkUsers;

public class dbUser extends DatabaseManager {
	public dbUser() 
	{	super();	}
	
	public CampusTalkUsers objUser;
	public CampusTalkUsers getUserDetailFromEmail(String email)	throws SQLException, ClassNotFoundException {
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

	public void registerNewuserDetail(CampusTalkUsers objUser) throws SQLException, ClassNotFoundException {
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
	
	public void AddUser(String email, String branch, int year, String role) throws SQLException, ClassNotFoundException{
		
		
		this.open();
		CallableStatement csSql = CON
				.prepareCall("{call openRegistration(?,?,?,?)}");
		csSql.setString(1, email);
		csSql.setString(2, branch);
		csSql.setInt(3, year);		
		csSql.setString(4, role);
			
		csSql.executeQuery();		
	}
	
	public ResultSet getUserData()throws SQLException, ClassNotFoundException 
	{
		
		this.open();
		CallableStatement csSql = CON
				.prepareCall("{call getAllUser()}");
		ResultSet rs = csSql.executeQuery();
		//ArrayList<CampusTalkBranch> branch = new ArrayList<>();
		//CampusTalkBranch temp_branch;
		
		/*while(rs.next())
		{
			temp_branch = new CampusTalkBranch();
			temp_branch.setBranchId(rs.getInt("branchid"));
			temp_branch.setName(rs.getString("name"));
			temp_branch.setDuration(rs.getInt("duration"));
			
			branch.add(temp_branch);
		}*/
		
		//return branch.toArray(new CampusTalkBranch[branch.size()]);
		return rs;		
	}	
	
	public void EditUser(int id, String email, String branch, int year, String role, String status) throws SQLException, ClassNotFoundException{

		
		this.open();
		CallableStatement csSql = CON
				.prepareCall("{call editUserData(?,?,?,?,?,?)}");
		csSql.setInt(1, id);		
		csSql.setString(2, email);
		csSql.setString(3, branch);
		csSql.setInt(4, year);		
		csSql.setString(5, role);
		csSql.setString(6, status);
			
		csSql.executeQuery();
	}
	
	public void DeleteUser(int id) throws SQLException, ClassNotFoundException{

		String status="D";
		this.open();
		CallableStatement csSql = CON
				.prepareCall("{call updateUserStatus(?,?)}");
		csSql.setInt(1, id);		
		csSql.setInt(2, status);		
		
		csSql.executeQuery();
	}
	
	public void ForgotPasswordRequest(String email, String authString) throws SQLException, ClassNotFoundException
	{
		this.open();
		String status="P";
		CallableStatement csSql = CON.prepareCall("{call UpdateAuthString(?,?,?)}");
		csSql.setString(1, email);
		csSql.setString(2, authString);
		csSql.setString(3, status);
		
	}
}
