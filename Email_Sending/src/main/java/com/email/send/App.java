package com.email.send;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "preparing to send message!" );
        String message = "Hello Dear, This message for security check";
        String subject = "PRM : confirmation";
        String to = "prmlaptop@gmail.com";
        String from = "pranavrmahajand@gmail.com";
        
        sendEmail(message, subject, to, from);
    }
    
    
    // this is responsible for send email..
	private static void sendEmail(String message, String subject, String to, String from) {
		//variable for gmail
		String host = "smtp.gmail.com";
		
		//get the system properties
		Properties properties = System.getProperties();
		System.out.println("Properties : "+properties);
		
		//setting the important information to properties object
		
		//host set
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");
		
		//Step 1 : to get the session object...
		Session session = Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("pranavrmahajand@gmail.com", "Mahajan510@");
			}
		});
		
		session.setDebug(true);
		
		//Step 2 : compose the message [text, multi media]
		MimeMessage mimeMessage = new MimeMessage(session);
		
		try {

			//from email
			mimeMessage.setFrom(from);
			
			//adding recipient to message
			mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			
			//add subject to message
			mimeMessage.setSubject(subject);
			
			//adding text to message
			mimeMessage.setText(message);
			
			
		//Step 3 : send the message using Transport class
			Transport.send(mimeMessage);
			System.out.println("Sent success................");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
    
}
