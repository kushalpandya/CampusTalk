package org.campustalk.util;

import java.util.Properties;
import java.util.UUID;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class OtherUtil {

	final static public  String getRandomeString(){
		String uuid = (UUID.randomUUID().toString() + UUID.randomUUID().toString()) ;
		uuid=  uuid.replace("-", "").replace(" ", "");
		System.out.println(uuid);
		return uuid;
	}
	final static public boolean sendEmail(String toEmail,String emailSubject,String emailMessage) throws AddressException, MessagingException{
		String[] to = new String[1];
		to[0]= toEmail;
		return sendEmail(to, emailMessage, emailMessage);
	}
	final static public boolean sendEmail(String[] toEmail,String emailSubject,String emailMessage) throws AddressException, MessagingException{

		 String host = "smtp.gmail.com";
		    String from = "betacampustalk";
		    String pass = "campustalk123a!";
		    Properties props = System.getProperties();
		    props.put("mail.smtp.starttls.enable", "true"); // added this line
		    props.put("mail.smtp.host", host);
		    props.put("mail.smtp.user", from);
		    props.put("mail.smtp.password", pass);
		    props.put("mail.smtp.port", "587");
		    props.put("mail.smtp.auth", "true");

		    Session session = Session.getDefaultInstance(props, null);
		    MimeMessage message = new MimeMessage(session);
		    message.setFrom(new InternetAddress(from));

		    InternetAddress[] toAddress = new InternetAddress[toEmail.length];

		    // To get the array of addresses
		    for( int i=0; i < toEmail.length; i++ ) { // changed from a while loop
		        toAddress[i] = new InternetAddress(toEmail[i]);
		    }
		    System.out.println(Message.RecipientType.TO);

		    for( int i=0; i < toAddress.length; i++) { // changed from a while loop
		        message.addRecipient(Message.RecipientType.TO, toAddress[i]);
		    }
		    message.setSubject(emailSubject);
		    message.setContent(emailMessage,"text/html");
		    Transport transport = session.getTransport("smtp");
		    transport.connect(host, from, pass);
		    transport.sendMessage(message, message.getAllRecipients());
		    transport.close();
		    return true;
	}
}
