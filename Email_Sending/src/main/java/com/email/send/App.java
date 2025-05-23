package com.email.send;

import java.io.File;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println("preparing to send message!");
		String message = "Hello Dear, This message for security check";
		String subject = "PRM : confirmation";
		String to = "pranavmahajan510@gmail.com";
		String from = "emailsendbyuser@gmail.com";
		// 1
//        sendEmail(message, subject, to, from);

		// 2
		sendAttach(message, subject, to, from);
	}

	// this is the responsible to send message with attachment
	private static void sendAttach(String message, String subject, String to, String from) {
		// variable for gmail
		String host = "smtp.gmail.com";

		// get the system properties
		Properties properties = System.getProperties();
		System.out.println("Properties : " + properties);

		// setting the important information to properties object

		// host set
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		// Step 1 : to get the session object...
		Session session = Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("emailsendbyuser@gmail.com", "ksju slab kkrx zqos");
			}
		});

		session.setDebug(true);

		// Step 2 : compose the message [text, multi media]
		MimeMessage mimeMessage = new MimeMessage(session);

		try {

			// from email
			mimeMessage.setFrom(from);

			// adding recipient to message
			mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// add subject to message
			mimeMessage.setSubject(subject);

			//set attachment...
			//file path
			String path = "F:\\SpringBoot_Concepts\\Images\\rohit.jpg";
			
			MimeMultipart mimeMultipart = new MimeMultipart();
			
			//file
			MimeBodyPart fileMime = new MimeBodyPart();
			
			//text
			MimeBodyPart textMime = new MimeBodyPart();
			
			
			try {

				//text
				textMime.setText(message);
				
				//file
				File file = new File(path);
				fileMime.attachFile(file);
				
				mimeMultipart.addBodyPart(textMime);
				mimeMultipart.addBodyPart(fileMime);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			mimeMessage.setContent(mimeMultipart);
			
			

			// Step 3 : send the message using Transport class
			Transport.send(mimeMessage);
			System.out.println("Sent success................");
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	

	// this is responsible for send email..
	private static void sendEmail(String message, String subject, String to, String from) {
		// variable for gmail
		String host = "smtp.gmail.com";

		// get the system properties
		Properties properties = System.getProperties();
		System.out.println("Properties : " + properties);

		// setting the important information to properties object

		// host set
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		// Step 1 : to get the session object...
		Session session = Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("emailsendbyuser@gmail.com", "ksju slab kkrx zqos");
			}
		});

		session.setDebug(true);

		// Step 2 : compose the message [text, multi media]
		MimeMessage mimeMessage = new MimeMessage(session);

		try {

			// from email
			mimeMessage.setFrom(from);

			// adding recipient to message
			mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// add subject to message
			mimeMessage.setSubject(subject);

			// adding text to message
			mimeMessage.setText(message);

			// Step 3 : send the message using Transport class
			Transport.send(mimeMessage);
			System.out.println("Sent success................");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
