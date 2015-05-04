package com.privatecloud.utility;
import java.io.UnsupportedEncodingException;
import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

import org.springframework.stereotype.Service;

@Service
public class mailutility {

	final static String username = "cmpe283team12";
	final static String password = "12!@qwQW12";

	public static void main(String[] args) {
		sendMail("T12-VM", "CPU", "rashmi052492@gmail.com", true);
		sendMail("T12-VM", "CPU", "rashmi052492@gmail.com", false);
	}
	
	public static void sendMail(String vmName, String property, String emailId, boolean usageHigh) {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		String subjectHighUsage = vmName + ": has reached limit "+ property + " usage";
		String subjectNormalUsage = vmName + ": You are back to normal "+ property + " usage";
		
		String emailBodyHighUsage = "Hello,"
				+ "\n\n THIS IS AN AUTOGENRATED MAIL. PLEASE DO NOT REPLY TO THIS.\n"
				+ " This mail is sent as you exceeded Limit for" + property + " on your virtual machine " + vmName + "."
				+ " To check our usage and monitor the usage please login to the Private Cloud to see the performace of your virtual machine."
				+ "\n\n Regards, \n Private Cloud Performance Monitoring Team, \n Private Cloud - TEAM12.";

		String emailBodyNormalUsage = "Hello,"
				+ "\n\n THIS IS AN AUTOGENRATED MAIL. PLEASE DO NOT REPLY TO THIS.\n" 
				+ "\n\n This mail is sent as your are back to normal usage now " + property + " on your virtual machine " 
				+ vmName 
				+ " You may login to the Private Cloud to see the performace of your virtual machine." + vmName 
				+ "\n\n Regards, \n Private Cloud Performance Monitoring Team, \n Private Cloud - TEAM12.";
		
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
		session.setDebug(true);
		try {
			if(isValidEmailAddress(emailId)) {
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress("cmpe283team12@gmail.com"));
				message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(emailId));
				if(usageHigh) {
					message.setSubject(subjectHighUsage);
					message.setText(emailBodyHighUsage);
				} else {
					message.setSubject(subjectNormalUsage);
					message.setText(emailBodyNormalUsage);
				}
				
				Transport.send(message);
				System.out.println("Email sent successfully to " + emailId);
			} else {
				System.out.println(emailId + " is not a valid email address");
			}
		} catch (MessagingException e) {
			System.out.println("Messaging Exception");
			e.printStackTrace();
		} 
	}
	
	private static boolean isValidEmailAddress(String emailId) {
		boolean result = true;
		   try {
		      InternetAddress emailAddr = new InternetAddress(emailId);
		      emailAddr.validate();
		   } catch (AddressException ex) {
		      result = false;
		   }
		   return result;
	}
}