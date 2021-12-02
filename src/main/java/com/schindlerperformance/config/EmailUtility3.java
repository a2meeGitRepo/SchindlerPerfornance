package com.schindlerperformance.config;

import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;

public class EmailUtility3{
  
	
	public synchronized static void sendEmail3(String host, String port, String recipient , String subject , String message)
					throws AddressException, MessagingException {
		
		
		String mails = recipient;
		
		String[] recipientList = mails.split(",");
		
		System.out.println("Email Starter at EmailUtility="+recipient);
	
	
		 Properties props = new Properties();
		    props.put("mail.smtp.host", "smtp.eu.schindler.com");
		    props.put("mail.smtp.port", "25");
		    props.put("mail.smtp.auth", "false");
		    props.put("mail.debug", "true");
		    Session session = Session.getDefaultInstance(props);
		    
		    Message msg = null;
		    try
		    {
		      msg = new MimeMessage(session);
		      
		      msg.setFrom(new InternetAddress("kpi_report.in@schindler.com"));
		   
		      
		      InternetAddress[] recipientAddress = new InternetAddress[recipientList.length];
				
				int counter = 0;
				for (String mailss : recipientList) {
				    recipientAddress[counter] = new InternetAddress(mailss.trim());
				    counter++;
				}
				
				
				msg.setRecipients(Message.RecipientType.TO, recipientAddress);
				msg.setSubject(subject);
				msg.setSentDate(new Date());

				MimeBodyPart messageBodyPart = new MimeBodyPart();
				messageBodyPart.setContent(message, "text/html");

			
				Multipart multipart = new MimeMultipart();
				multipart.addBodyPart(messageBodyPart);

				msg.setContent(multipart);
				
				Transport.send(msg);
		      
		    }
		    catch (Exception e) {
		      e.printStackTrace();
		    }
		    
		    System.out.println("Email Msg=" + msg);
		    System.out.println("Email Sent Successfully to " + recipient);
		  }
	
	private static Address InternetAddress(String recipient) {
		// TODO Auto-generated method stub
		return null;
	}
	
  
 
}