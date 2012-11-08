/**
 * CamposTalk v0.1
 * 
 * License: GPLv3.
 * Author: Faishal Saiyed < https://github.com/faishal >
 * 
 * Entity class Branch to represent Roels in CampusTalk.
 */
package org.campustalk.entity;

import org.json.JSONException;
import org.json.JSONObject;


public class CampusTalkRoles 
{
	private Integer roleid;
	private String name;

	/**
	 * 
	 * @param roleid set roleid
	 */
	public void setRoleid(Integer roleid)
	{
		this.roleid=roleid;
	}
	/**
	 * 
	 * @return roleid
	 */
	public Integer getRoleid()
	{
		return this.roleid;
	}
	/**
	 * 
	 * @param name to set role name
	 */
	public void setName(String name)
	{
		this.name=name;
	}
	/**
	 * 
	 * @return name
	 */
	public String getName()
	{
		return this.name;
	}
	public JSONObject toJSONObject() throws JSONException
	{
		JSONObject obj = new JSONObject();
		obj.put("roleid", this.roleid);
		obj.put("name", this.name);		
		return obj;
	}	
}