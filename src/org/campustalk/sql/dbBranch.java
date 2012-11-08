package org.campustalk.sql;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import org.campustalk.entity.CampusTalkBranch;

public class dbBranch extends DatabaseManager {

	public CampusTalkBranch getBranchById(int branchId) {
		CampusTalkBranch objBranch = new CampusTalkBranch();

		try {
			this.open();
			CallableStatement pst = CON.prepareCall("{ call getBranchById(?)}");
			pst.setInt(1, branchId);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				objBranch.setBranchId(rs.getInt("branchid"));
				objBranch.setName(rs.getString("name"));
				objBranch.setDuration(rs.getInt("duration"));
			}

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return objBranch;

	}

	/**
	 * Admin Module ....
	 */

	public boolean AddBranch(String branchname, int duration)
			throws SQLException, ClassNotFoundException {

		this.open();
		CallableStatement csSql = CON.prepareCall("{call insertBranch(?,?,?)}");

		/**
		 * CREATE PROCEDURE `campustalk`.`insertRoles`( IN name VARCHAR(20), IN
		 * duration TEXT, IN status CHAR(2))
		 */
		csSql.setString(1, branchname);
		csSql.setInt(2, duration);
		csSql.registerOutParameter(3, Types.BOOLEAN);

		csSql.executeUpdate();

		boolean rtnTemp = csSql.getBoolean(3);
		csSql.close();
		return rtnTemp;
	}

	public Boolean EditBranch(int branchid, String branchname, int duration)
			throws SQLException, ClassNotFoundException {

		this.open();
		CallableStatement csSql = CON
				.prepareCall("{call updateBranchByID(?,?,?,?)}");

		csSql.setInt(1, branchid);
		csSql.setString(2, branchname);
		csSql.setInt(3, duration);
		csSql.registerOutParameter(4, Types.BOOLEAN);
		csSql.executeUpdate();
		boolean rtnTemp = csSql.getBoolean(4);
		csSql.close();
		return rtnTemp;
	}

	public void DeleteBranch(int branchid) throws SQLException,
			ClassNotFoundException {

		this.open();
		CallableStatement csSql = CON.prepareCall("{call deleteBranch(?)}");
		csSql.setInt(1, branchid);

		csSql.executeQuery();
	}

	/*
	 * public ResultSet getBranchData() throws SQLException,
	 * ClassNotFoundException {
	 * 
	 * this.open();
	 * 
	 * CallableStatement csSql = CON.prepareCall("{call getBranchData()}");
	 * ResultSet rs = csSql.executeQuery(); return rs; }
	 * 
	 * public CampusTalkBranch getBranchById(int branchId){ CampusTalkBranch
	 * objBranch= new CampusTalkBranch();
	 * 
	 * try { this.open(); CallableStatement pst =
	 * CON.prepareCall("{ call getBranchById(?)}"); pst.setInt(1,branchId);
	 * ResultSet rs = pst.executeQuery(); if(rs.next()){
	 * objBranch.setBranchId(rs.getInt("branchid"));
	 * objBranch.setName(rs.getString("name"));
	 * objBranch.setDuration(rs.getInt("duration")); }
	 * 
	 * } catch (ClassNotFoundException | SQLException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); }
	 * 
	 * 
	 * return objBranch;
	 * 
	 * 
	 * }
	 */

	public CampusTalkBranch objBranch[] = new CampusTalkBranch[10];

	public CampusTalkBranch[] getBranchData() throws SQLException,
			ClassNotFoundException {

		this.open();
		CallableStatement csSql = CON.prepareCall("{call getAllBranch()}");
		ResultSet rs = csSql.executeQuery();
		ArrayList<CampusTalkBranch> branch = new ArrayList<>();
		CampusTalkBranch temp_branch;

		while (rs.next()) {
			temp_branch = new CampusTalkBranch();
			temp_branch.setBranchId(rs.getInt("branchid"));
			temp_branch.setName(rs.getString("name"));
			temp_branch.setDuration(rs.getInt("duration"));

			branch.add(temp_branch);
		}

		return branch.toArray(new CampusTalkBranch[branch.size()]);

		/*
		 * while(rs.next()) { objBranch[i].setName(rs.getString("name")); i++; }
		 * return objBranch;
		 */
	}
	/**
	 * End admin module
	 */
}
