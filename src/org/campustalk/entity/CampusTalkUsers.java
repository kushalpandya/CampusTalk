/**
 * CamposTalk v0.1
 * 
 * License: GPLv3.
 * Author: Faishal Saiyed < https://github.com/faishal >
 * 
 * Entity class Users to represent Users in CampusTalk.
 */
package org.campustalk.entity;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;



public class CampusTalkUsers 
{
	private Integer id;
	private String email;
	private String password;
	private String registerwith;
	private String status;
	private String authstring;
	private Date authdate;
	private Date registerdate;
	private String firstname;
	private String lastname;
	private Date birthdate;
	private String gender;
	private Integer cityid;
	private Integer branchid;
	private Integer year;
	private String pictureUrl;

	/**
	 * 
	 * @param id to se t id 
	 */
	public void setId(Integer id)
	{
		this.id=id;
	}
	/**
	 * 
	 * @return id
	 */
	public Integer getId()
	{
		return this.id;
	}
	/**
	 * 
	 * @param email to set email
	 */
	public void setEmail(String email)
	{
		this.email=email;
	}
	/**
	 * 
	 * @return email
	 */
	public String getEmail()
	{
		return this.email;
	}
	/**
	 * 
	 * @param password to ser password
	 */
	public void setPassword(String password)
	{
		this.password=password;
	}
	/**
	 * 
	 * @return password
	 */
	public String getPassword()
	{
		return this.password;
	}
	/**
	 * 
	 * @param registerwith to set register with
	 */
	public void setRegisterWith(String registerwith)
	{
		this.registerwith=registerwith;
	}
	/**
	 *
	 * @return registerwith
	 */
	public String getRegisterWith()
	{
		return this.registerwith;
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
	 * 
	 * @param authstring to set autstring
	 */
	public void setAuthString(String authstring)
	{
		this.authstring=authstring;
	}
	/**
	 * 
	 * @return to get authstring
	 */
	public String getAuthString()
	{
		return this.authstring;
	}
	/**
	 * 
	 * @param authdate to set authdate
	 */
	public void setAuthDate(Date authdate)
	{
		this.authdate=authdate;
	}
	/**
	 * 
	 * @return authdate
	 */
	public Date getAuthDate()
	{
		return this.authdate;
	}
	/**
	 * 
	 * @param registerdate to set registerdate
	 */
	public void setRegisterDate(Date registerdate)
	{
		this.registerdate=registerdate;
	}
	/**
	 * 
	 * @return registrationdate
	 */
	public Date getRegisterDate()
	{
		return this.registerdate;
	}
	/**
	 * 
	 * @param firstname to set firstname
	 */
	public void setFirstName(String firstname)
	{
		this.firstname=firstname;
	}
	/**
	 * 
	 * @return firstname
	 */
	public String getFirstname()
	{
		return this.firstname;
	}
	/**
	 * 
	 * @param lastname to set lastname
	 */
	public void setLastName(String lastname)
	{
		this.lastname=lastname;
	}
	/**
	 * 
	 * @return lastname
	 */
	public String getLastname()
	{
		return this.lastname;
	}
	/**
	 * 
	 * @param birthdate to set birthdate
	 */
	public void setBirthDate(Date birthdate)
	{
		this.birthdate=birthdate;
	}
	/**
	 * 
	 * @return birthdate
	 */
	public Date getBirthDate()
	{
		return this.birthdate;
	}
	/**
	 * 
	 * @param gender to set gender
	 */
	public void setGender(String gender)
	{
		this.gender=gender;
	}
	/**
	 * 
	 * @return gender
	 */
	public String getGender()
	{
		return this.gender;
	}
	/**
	 * 
	 * @param cityid to set cityid
	 */
	public void setCityId(Integer cityid)
	{
		this.cityid=cityid;
	}
	/**
	 * 
	 * @return cityid
	 */
	public Integer getCityId()
	{
		return this.cityid;
	}
	/**
	 * 
	 * @param branchid to set branchid
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
	 * @param year to set year
	 */
	public void setYear(Integer year)
	{
		this.year=year;
	}
	/**
	 * 
	 * @return year
	 */
	public Integer getYear()
	{
		return this.year;
	}
	/**
	 * 
	 * @param year to set year
	 */
	public void setPictureUrl(String pictureUrl)
	{
		this.pictureUrl=pictureUrl;
	}
	/**
	 * 
	 * @return year
	 */
	public String getPictureUrl()
	{
		return this.pictureUrl;
	}
	public JSONObject toJSONObject() throws JSONException
	{
		JSONObject obj = new JSONObject();
		obj.put("userid", this.branchid);
		obj.put("firstname", this.firstname);
		obj.put("lastname", this.lastname);
		obj.put("pictureUrl", this.pictureUrl);
		obj.put("birthdate", this.birthdate);
		obj.put("brnachid", this.branchid);
		obj.put("year", this.year);
		obj.put("gender", this.gender);
		obj.put("email", this.email);
		return obj;
	}
}