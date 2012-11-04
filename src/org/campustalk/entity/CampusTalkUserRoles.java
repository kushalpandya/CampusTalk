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
	public CampusTalkRoles role;

	public CampusTalkUserRoles(){
		this.role=new CampusTalkRoles();
	}
	/**
	 * 
	 * @param userid to set id 
	 */
	public void setUserid(Integer userid)
	{
		this.userid=userid;
	}
	/**
	 * 
	 * @return userid
	 */
	public Integer getUserid()
	{
		return this.userid;
	}
	
}