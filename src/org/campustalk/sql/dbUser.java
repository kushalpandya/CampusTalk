package org.campustalk.sql;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.campustalk.entity.CampusTalkUsers;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class dbUser extends DatabaseManager {
	public dbUser() {
		super();
	}
	public CampusTalkUsers objUser;
	public CampusTalkUsers getUserDetailFromEmail(String email) {
		try {
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
		
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
		
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
		csSql.setString(2, objUser.getFirstname());
		csSql.setString(3, objUser.getLastname());
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
			objUser.setBirthDate(rs.getDate("birthdate"));
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
	public JSONArray serchUserMsgAC(String query){
		JSONArray jArray = new JSONArray();
		try {
			this.open();
	
		CallableStatement csSql = CON.prepareCall("{call SearchUserMsgAC(?)}");
		csSql.setString(1, query);
		ResultSet rs = csSql.executeQuery();
		JSONObject jObj ;
		
		while (rs.next()) {
			jObj= new JSONObject();
			objUser = new CampusTalkUsers();
			//jObj.put("id",rs.getInt("id"));
			jObj.put("value",rs.getString("email"));
			//jObj.put("firstname",rs.getString("firstname"));
			//jObj.put("lastname",rs.getString("lastname"));
		//	jObj.put("pictureurl",rs.getString("pictureurl"));
			jArray.put(jObj);
		} 
		csSql.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jArray;
	}
	public String getUserIdListFromEmailList(String emailList){
		
		String rtnStr = "";
		String sep ="";
		
		try {
			this.open();
	
		CallableStatement csSql = CON.prepareCall("{call getUserIdListFromEmailList(?)}");
		csSql.setString(1, emailList);
		ResultSet rs = csSql.executeQuery();
		
		
		while (rs.next()) {
			rtnStr += sep +  rs.getString(1);
			sep=",";
					
		} 
		csSql.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return rtnStr;
		
	}
	/**
	 * Admin Module
	 */
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
//, 
		
		this.open();
		CallableStatement csSql = CON
				.prepareCall("{call editUser(?,?,?,?,?)}");
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
		csSql.setString(2, status);		
		
		csSql.executeQuery();
	}
	
	
	public Boolean HelloFun(int id, String email, String branch, int year, String role, String st)
	{
		Exception err=null;
		boolean ret=false;
		try {
			
			//String st1="s";
			
			
			this.open();

			CallableStatement csSql = CON
					.prepareCall("{call editUser(?,?,?,?,?,?)}");
			csSql.setInt(1, id);		
			csSql.setString(2, email);
			csSql.setString(3, branch);
			csSql.setInt(4, year);		
			csSql.setString(5, role);
			csSql.setString(6, st);
				
			if(csSql.executeQuery() != null)
			{ ret= true;}
			else
			{	ret=false;}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			err=e;
		}
		return ret;
	}
	/**
	 * End Admin Module
	 */
	
}
