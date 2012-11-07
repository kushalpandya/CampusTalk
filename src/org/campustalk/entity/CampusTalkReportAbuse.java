package org.campustalk.entity;
import java.util.Date;
public class CampusTalkReportAbuse {
	private int postId;
	private int userId;
	private Date entTime;
	private String status;
	private String detail;
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Date getEntTime() {
		return entTime;
	}
	public void setEntTime(Date entTime) {
		this.entTime = entTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	
}
