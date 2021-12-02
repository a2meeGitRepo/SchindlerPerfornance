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

public class EmailUtilityEscalation{
  
	
	public synchronized static void sendEmail(String host, String port,
			final String userName, 
			String recipient , String recipient2, String recipient3, String recipient4, String recipient5, String recipient6, String recipient7, String recipient8, String recipient9, String subject, String file_name, String message)
					throws AddressException, MessagingException {
		
		
		String mails = recipient + "," + recipient2+ "," + recipient3+ "," + recipient4+ "," + recipient5+ "," + recipient6+ "," + recipient7+ "," + recipient8+ "," + recipient9;
		
		String[] recipientList = mails.split(",");
		
		System.out.println("Email Starter at EmailUtility="+recipient);
	
	
		 Properties props = new Properties();
		 // For Schindler Check
		   props.put("mail.smtp.host", "smtp.eu.schindler.com");
		    props.put("mail.smtp.port", "25");
		    props.put("mail.smtp.auth", "false");
		    props.put("mail.debug", "true");
		    
	 Session session = Session.getDefaultInstance(props);
		
		 //For Local Check
		  /*props.put("mail.smtp.host", "smtp.ionos.com");
		    props.put("mail.smtp.auth", "true");
		    props.put("mail.debug", "true");
		    props.put("mail.smtp.port", "587");
		    
		    props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
			
            //create Authenticator object to pass in Session.getInstance argument
		Authenticator auth = new Authenticator() {
				//override the getPasswordAuthentication method
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("shivam.kumar@a2mee.com", "Shivam@123");
				}
				
			};
			Session session = Session.getInstance(props, auth); */
		 
		    
		    Message msg = null ;
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
				
				//InternetAddress[] toAddresses = { new InternetAddress(mails) };
				
				msg.setRecipients(Message.RecipientType.TO, recipientAddress);
				msg.setSubject(subject);
				msg.setSentDate(new Date());

				// creates message part
				MimeBodyPart messageBodyPart = new MimeBodyPart();
				messageBodyPart.setContent(message, "text/html");

				// creates multi-part
				Multipart multipart = new MimeMultipart();
				
				
		            // Set text message part
		            multipart.addBodyPart(messageBodyPart);

		            // Part two is attachment
		            messageBodyPart = new MimeBodyPart();
		            
		       
		         
		       //     String filename = "\\indwsr0008\\SchindlerPerformance_pdf_files$"+ file_name;
		           //String filename = "//indwsr0008/SchindlerPerformance_pdf_files/"+ file_name+".pdf";
		          
		            
		          //  String filename = "\\GK8-PC\\Users\\Public\\Shivam performance test\\"+ file_name+".pdf";
		            
		            
		            //Path      //indwsr0008//SchindlerPerformance_pdf_files
		              
		           
		           //String filename = "C:\\Users\\Shree\\Downloads\\indwsr0008\\SchindlerPerformance_pdf_files\\"+ file_name+".pdf";
		            String filename = "C:\\SchindlerPerformance_pdf_files\\"+ file_name;
		            
		            FileDataSource source = new FileDataSource(filename);
		            messageBodyPart.setDataHandler(new DataHandler(source));
		            messageBodyPart.setFileName("Escalation_Report");
		            multipart.addBodyPart(messageBodyPart);

		            // Send the complete message parts
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