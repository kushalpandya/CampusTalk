/**
 * CamposTalk v0.1
 * 
 * License: GPLv3.
 * Author: Faishal Saiyed < https://github.com/faishal >
 * 
 * Entity class Branch to represent Userroles in CampusTalk.
 */
package org.campustalk.entity;



public class CampusTalkUserRoles

{
	private Integer userid;
	private Integer roleid;

	/**
	 * 
	 * @param userid to set id 
	 */
	public void setUserId(Integer userid)
	{
		this.userid=userid;
	}
	/**
	 * 
	 * @return userid
	 */
	public Integer getUserId()
	{
		return this.userid;
	}
	/**
	 * 
	 * @param roleid to set roleid
	 */
	public void setRoleId(Integer roleid)
	{
		this.roleid=roleid;
	}
	/**
	 * 
	 * @return roleid
	 */
	public Integer getRoleId()
	{
		return this.roleid;
	}
	
}