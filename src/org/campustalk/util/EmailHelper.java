package org.campustalk.util;

import org.campustalk.Settings;
import org.campustalk.entity.CampusTalkUsers;

public class EmailHelper {
	
	final public static boolean registrationVerifyEmail(CampusTalkUsers ctUser){
		OtherUtil.SendEmailNew(ctUser.getEmail(),"[CampusTalk] New Registration Confirmation",
						"Hi,"
								+ ctUser.getFirstname()
								+ "<br> <a href='"
								+ Settings.APPURL
								+ "?q="
								+ ctUser.getAuthString()
								+ "&e="
								+ ctUser.getEmail()
								+ "' > Click Here</a> to verify yor A/C");
		return true;
		

	}
	
	
}
