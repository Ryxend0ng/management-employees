//package com.amis.misa.schedules;
//
//import javax.mail.MessagingException;
//import javax.mail.internet.MimeMessage;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//@Component
//public class SchedulesNotificationEmployee {
//	private static final Logger logger = LoggerFactory.getLogger(SchedulesNotificationEmployee.class);
//	@Autowired
//	private JavaMailSender emailSender;
//
//	@Scheduled(fixedRate = 5000)
//	public void scheduleTaskSendEmailSalaryEmp() throws MessagingException {
//		MimeMessage message = emailSender.createMimeMessage();
//	     
//	    MimeMessageHelper helper = new MimeMessageHelper(message, true);
//		
//	    helper.setSubject("Luong cuoi thang");
//		String htmlMsg = "<div>Chao " + "dong" + " !</div> <br/><br/>";
//		htmlMsg += "<div>Luong cuoi thang cua ban la:!</div> <br/>";	
//		message.setContent(htmlMsg, "text/html");
//		helper.setTo("donglon778899@gmail.com");
//		emailSender.send(message);
//	}
//
//	public void scheduleTaskWithFixedDelay() {
//	}
//
//	public void scheduleTaskWithInitialDelay() {
//	}
//
//	public void scheduleTaskWithCronExpression() {
//	}
//}
