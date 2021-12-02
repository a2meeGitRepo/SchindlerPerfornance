package com.schindlerperformance.config;

import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
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

public class EmailUtility{
  
	public static synchronized void sendEmail(final String host, final String port, final String userName, final String recipient, final String recipient2, final String recipient3, final String recipient4, final String recipient5, final String recipient6, final String recipient7, final String recipient8, final String recipient9, final String subject, final String file_name, final String message) throws AddressException, MessagingException {
        final String mails = String.valueOf(recipient) + "," + recipient2 + "," + recipient3 + "," + recipient4 + "," + recipient5 + "," + recipient6 + "," + recipient7 + "," + recipient8 + "," + recipient9;
        final String[] recipientList = mails.split(",");
        System.out.println("Email Starter at EmailUtility=" + recipient);
        final Properties props = new Properties();
        final Session session = Session.getDefaultInstance(props);
        props.put("mail.smtp.host", "smtp.eu.schindler.com");
        props.put("mail.smtp.port", "25");
        props.put("mail.smtp.auth", "false");
        props.put("mail.debug", "true");
        Message msg = null;
        try {
            msg = (Message)new MimeMessage(session);
            msg.setFrom((Address)new InternetAddress("kpi_report.in@schindler.com"));
            final InternetAddress[] recipientAddress = new InternetAddress[recipientList.length];
            int counter = 0;
            String[] array;
            for (int length = (array = recipientList).length, i = 0; i < length; ++i) {
                final String mailss = array[i];
                recipientAddress[counter] = new InternetAddress(mailss.trim());
                ++counter;
            }
            msg.setRecipients(Message.RecipientType.TO, (Address[])recipientAddress);
            msg.setSubject(subject);
            msg.setSentDate(new Date());
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent((Object)message, "text/html");
            final Multipart multipart = (Multipart)new MimeMultipart();
            multipart.addBodyPart((BodyPart)messageBodyPart);
            messageBodyPart = new MimeBodyPart();
           // final String filename = "C:\\SchindlerPerformance_pdf_files\\" + file_name + ".pdf";
            
           String filename = "\\indwsr0008\\SchindlerPerformance_pdf_files$"+ file_name+".pdf";
	           //String filename = "//indwsr0008/SchindlerPerformance_pdf_files/"+ file_name+".pdf";
	          
            final FileDataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler((DataSource)source));
            messageBodyPart.setFileName("Performance_Report.pdf");
            multipart.addBodyPart((BodyPart)messageBodyPart);
            msg.setContent(multipart);
            Transport.send(msg);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Email Msg=" + msg);
        System.out.println("Email Sent Successfully to " + recipient);
    }
    
    private static Address InternetAddress(final String recipient) {
        return null;
    }
  
 
}