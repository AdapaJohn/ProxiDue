//package com.in28minutes.rest.webservices.restfulwebservices.EmailDemo;
//
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//
//@Service    
//public class Notificationservice {
//
//    private final JavaMailSender jms;
//
//    public Notificationservice(JavaMailSender jms){
//        this.jms = jms;
//    }
//
//    public void sendOrderConfirmation(String to, String orderDetails) {
//        SimpleMailMessage msg = new SimpleMailMessage();
//        msg.setTo(to);
//        msg.setSubject("Order Confirmation");
//        msg.setText("Your Order has been placed successfully!\n\nDetails: "+ orderDetails);
//        jms.send(msg);
//    }
//    
//}

package com.in28minutes.rest.webservices.restfulwebservices.EmailDemo;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service    
public class Notificationservice {

    private final JavaMailSender jms;
    private static final Logger logger = LoggerFactory.getLogger(Notificationservice.class);

    public Notificationservice(JavaMailSender jms){
        this.jms = jms;
    }

    public void sendOrderConfirmation(String to, String orderDetails) {
        try {
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setTo(to);
            msg.setFrom("adapajohnmu143@gmail.com"); // Add from address
            msg.setSubject("Order Confirmation");
            msg.setText("Your Order has been placed successfully!\n\nDetails: "+ orderDetails);
            
            jms.send(msg);
            logger.info("Email sent successfully to: {}", to);
            
        } catch (Exception e) {
            logger.error("Failed to send email to: {}, Error: {}", to, e.getMessage(), e);
            throw new RuntimeException("Failed to send email: " + e.getMessage(), e);
        }
    }
}