/**
 * CamposTalk v0.1
 * 
 * License: GPLv3.
 * Author: Faishal Saiyed < https://github.com/faishal >
 * 
 * Entity class Branch to represent Branches in CampusTalk.
 */
package org.campustalk.entity;

import org.json.JSONException;
import org.json.JSONObject;

public class CampusTalkBranch 
{
	private Integer branchid;
	private String name;
	private Integer duration;
		
	/**
	 * 
	 * @param branchid the branchid to set
	 */
	public void setBranchId(Integer branchid)
	{
		this.branchid=branchid;
	}
	/**
	 * 
	 * @return branchid
	 */
	public Integer getBranchId()
	{
		return this.branchid;
	}
	/**
	 * 
	 * @param name the branch name to set
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
	/**
	 * 
	 * @param duration to set duration
	 */
	public void setDuration(Integer duration)
	{
		this.duration=duration;
	}
	/**
	 * 
	 * @return duration
	 */
	public Integer getDuration()
	{
		return this.duration;
	}
	/**
	 * Gets JSONObject representing this Object.
	 * @return JSONObject
	 * @throws JSONException
	 */
	public JSONObject toJSONObject() throws JSONException
	{
		JSONObject obj = new JSONObject();
		obj.put("branchid", this.branchid);
		obj.put("name", this.name);
		obj.put("duration", this.duration);
		return obj;
	}
}