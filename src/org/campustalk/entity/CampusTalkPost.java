package org.campustalk.entity;

import java.util.Date;

public class CampusTalkPost {

	private int postid;
	private String detail;
	private int userid;
	private String type;
	private String status;
	private Date entTime;
	private Date lastmodifytime;
	public int getPostid() {
		return postid;
	}
	public void setPostid(int postid) {
		this.postid = postid;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getEntTime() {
		return entTime;
	}
	public void setEntTime(Date entTime) {
		this.entTime = entTime;
	}
	public Date getLastmodifytime() {
		return lastmodifytime;
	}
	public void setLastmodifytime(Date lastmodifytime) {
		this.lastmodifytime = lastmodifytime;
	}
	
	
}
