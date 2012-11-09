package org.campustalk.entity;

import java.sql.Date;

public class CampusTalkEvent {

	private int  EventId;
	private int UserId;
	private String Title;
	private Date fromDate;
	private Date toDate;
	private String Place;
	private String Description;
	
	public int getEventId() {
		return EventId;
	}
	public void setEventId(int eventId) {
		EventId = eventId;
	}
	public int getUserId() {
		return UserId;
	}
	public void setUserId(int userId) {
		UserId = userId;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public String getPlace() {
		return Place;
	}
	public void setPlace(String place) {
		Place = place;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
}
