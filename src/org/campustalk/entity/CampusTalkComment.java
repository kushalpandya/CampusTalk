/**
 * CamposTalk v0.1
 * 
 * License: GPLv3.
 * Author: Faishal Saiyed < https://github.com/faishal >
 * 
 * Entity class Branch to represent Comments in CampusTalk.
 */
package org.campustalk.entity;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

public class CampusTalkComment 
{

	private Integer commentid;
	private Integer postid;
	private Integer userid;
	private String detail;
	private Date enttime;
	private String status;

	/**
	 * 
	 * @param commentid to set commentid
	 */
	public void setCommentId(Integer commentid)
	{
		this.commentid=commentid;
	}
	/**
	 * 
	 * @return commentid
	 */
	public Integer getCommentId()
	{
		return this.commentid;
	}
	/**
	 * 
	 * @param postid to set postid
	 */
	public void setPostId(Integer postid)
	{
		this.postid=postid;
	}
	/**
	 * 
	 * @return postid
	 */
	public Integer getPostId()
	{
		return this.postid;
	}
	/**
	 * 
	 * @param userid to set userid
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
	 * @param detail to set comment detail
	 */
	public void setDetail(String detail)
	{
		this.detail=detail;
	}
	/**
	 * 
	 * @return details
	 */
	public String getDetail()
	{
		return this.detail;
	}
	/**
	 * 
	 * @param enttime to set enttime
	 */
	public void setEntTime(Date enttime)
	{
		this.enttime=enttime;
	}
	/**
	 * 
	 * @return enttime
	 */
	public Date getEntTime()
	{
		return this.enttime;
	}
	/**
	 * 
	 * @param status to set status
	 */
	public void setStatus(String status)
	{
		this.status=status;
	}
	/**
	 * 
	 * @return status
	 */
	public String getStatus()
	{
		return this.status;
	}
	/**
	 * Gets JSONObject representing this Object.
	 * @return JSONObject
	 * @throws JSONException
	 */
	public JSONObject toJSONObject() throws JSONException
	{
		JSONObject obj = new JSONObject();
		obj.put("commentid", this.commentid);
		obj.put("detail", this.detail);
		obj.put("enttime", this.enttime);
		obj.put("postid", this.postid);
		obj.put("status", this.status);
		obj.put("userid", this.userid);
		return obj;
	}
}