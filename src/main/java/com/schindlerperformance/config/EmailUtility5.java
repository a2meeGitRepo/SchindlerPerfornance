package com.schindlerperformance.config;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
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
import javax.mail.internet.MimeMultipart;


/**
 * A utility class for sending e-mail with attachment.
 * @author www.codejava.net
 *
 */
public class EmailUtility5 {
	
	public static void sendEmail(String host, String port,final String fromMail,
			final String userName, final String password,
			String recipient, String subject, String message,String file_name)
					throws AddressException, MessagingException {
		
		System.out.println("Email Starter at EmailUtility="+recipient);
		
		// sets SMTP server properties
		Properties properties = new Properties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		/*properties.put("mail.user", userName);
		properties.put("mail.password", password);*/

		// creates a new session with an authenticator
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		};
		Session session = Session.getInstance(properties, auth);
		// creates a new e-mail message
		Message msg = null;
		
		try {
			msg = new MimeMessage(session);

			msg.setFrom(new InternetAddress(fromMail));
			msg.setReplyTo(InternetAddress.parse(fromMail, false));
			InternetAddress[] toAddresses = { new InternetAddress(recipient) };
			msg.setRecipients(Message.RecipientType.TO, toAddresses);
			msg.setSubject(subject);

			// creates message part
			Multipart multipart = new MimeMultipart();
			
			MimeBodyPart messageBodyPart = new MimeBodyPart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            // Part two is attachment
            messageBodyPart = new MimeBodyPart();
            
			
			
			messageBodyPart.setContent(message, "text/html");
			
			
			
			  String filename = "C:\\SchindlerPerformance_pdf_files\\"+ file_name+".pdf";
	           // C:\SchindlerPerformance_pdf_files
	           
	            FileDataSource source = new FileDataSource(filename);
	            messageBodyPart.setDataHandler(new DataHandler(source));
	            messageBodyPart.setFileName("Performance_Report");
	            multipart.addBodyPart(messageBodyPart);

	            // Send the complete message parts
	            msg.setContent(multipart);
			// creates multi-part
		

	
			   
			
			System.out.println(msg);
			
		
			msg.setContent(multipart);

			// sends the e-mail
			Transport.send(msg);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Email Msg="+msg);
		System.out.println("Email Sent Successfully to "+recipient);
	}
}