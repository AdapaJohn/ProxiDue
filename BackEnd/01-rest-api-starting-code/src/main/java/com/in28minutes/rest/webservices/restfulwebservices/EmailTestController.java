package com.in28minutes.rest.webservices.restfulwebservices;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@RestController
@RequestMapping("/test")
public class EmailTestController {

    @Autowired
    private JavaMailSender mailSender;

//    @GetMapping("/email")
//    public String testEmail() {
//        try {
//            SimpleMailMessage message = new SimpleMailMessage();
//            message.setTo("adapajohnmu143@gmail.com");
//            message.setSubject("Test Email");
//            message.setText("This is a test email from your Spring Boot application");
//            
//            mailSender.send(message);
//            return "Test email sent successfully!";
//        } catch (Exception e) {
//            return "Failed to send test email: " + e.getMessage();
//        }
//    }
    
    @GetMapping("/test/email")
    public String testEmail() {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo("adapajohnmu143@gmail.com");
            message.setSubject("Test Email");
            message.setText("This is a test email from your Spring Boot application");
            mailSender.send(message);
            return "Test email sent successfully!";
        } catch (Exception e) {
            return "Failed to send test email: " + e.getMessage();
        }
    }
}