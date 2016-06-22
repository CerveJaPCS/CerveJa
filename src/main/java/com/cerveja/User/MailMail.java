package com.cerveja.User;

//import java.util.Properties;
//import java.util.*;
//import javax.mail.*;
//import javax.mail.internet.*;
//import javax.activation.*;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class MailMail
{
	private MailSender mailSender;
	
	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	public void sendMail(String from, String to, String subject, String msg) {

		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setFrom(from);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(msg);
		mailSender.send(message);	
		
//	    String host = "localhost";
//		Properties properties = System.getProperties();
//		properties.setProperty("mail.smtp.socketFactory.port", "465");
//		properties.setProperty("mail.smtp.host", host);
//		Session session = Session.getDefaultInstance(properties);
//		try{
//	         MimeMessage message = new MimeMessage(session);
//	
//	         message.setFrom(new InternetAddress(from));
//	
//	         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
//	
//	         message.setSubject(subject);
//	
//	         message.setText(msg);
//	
//	         Transport.send(message);
//	         System.out.println("Mensagem enviada com sucesso....");
//	         
//	     }catch (MessagingException mex) {
//	         mex.printStackTrace();
//	     }
	}
}

