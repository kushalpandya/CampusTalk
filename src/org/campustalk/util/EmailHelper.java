package org.campustalk.util;

import javax.mail.MessagingException;

import org.campustalk.Settings;
import org.campustalk.entity.CampusTalkUsers;

public class EmailHelper {
	
	final public static boolean registrationVerifyEmail(CampusTalkUsers ctUser){
		try {
			OtherUtil.sendEmail(ctUser.getEmail(),"[CampusTalk] New Registration Confirmation",
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
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		

	}
	
	
}
