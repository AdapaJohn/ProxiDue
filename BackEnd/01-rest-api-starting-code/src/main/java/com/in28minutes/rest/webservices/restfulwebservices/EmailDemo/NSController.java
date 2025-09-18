//package com.in28minutes.rest.webservices.restfulwebservices.EmailDemo;
//
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("orders")
//public class NSController {
//
//    private final Notificationservice ns;
//
//    public NSController(Notificationservice ns){
//        this.ns = ns;
//    }
//
//    @PostMapping("/sendNoti")
//    public String sendOrderConfirmation(@RequestBody String orderDatails){
//
//        ns.sendOrderConfirmation("adapajohnmu143@gmail.com", orderDatails);
//
//        return "order confirmation sent successfully";
//    }
//    
//}

//package com.in28minutes.rest.webservices.restfulwebservices.EmailDemo;
//
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.security.access.prepost.PreAuthorize;
//
//@RestController
//@RequestMapping("orders")
//public class NSController {
//
//    private final Notificationservice ns;
//    private static final Logger logger = LoggerFactory.getLogger(NSController.class);
//
//    public NSController(Notificationservice ns){
//        this.ns = ns;
//    }
//
//    @PostMapping("/sendNoti")
//    @PreAuthorize("hasRole('USER')") // Secure with JWT role-based access
//    public String sendOrderConfirmation(@RequestBody String orderDetails){
//        try {
//            ns.sendOrderConfirmation("adapajohnmu143@gmail.com", orderDetails);
//            return "Order confirmation sent successfully";
//        } catch (Exception e) {
//            logger.error("Failed to send order confirmation: {}", e.getMessage(), e);
//            return "Failed to send order confirmation: " + e.getMessage();
//        }
//    }
//}

package com.in28minutes.rest.webservices.restfulwebservices.EmailDemo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/orders") // Changed to /api for better structure
public class NSController {

    private final Notificationservice ns;
    private static final Logger logger = LoggerFactory.getLogger(NSController.class);

    public NSController(Notificationservice ns){
        this.ns = ns;
    }

    @PostMapping("/send-notification")
    //@PreAuthorize("hasRole('USER')") // Requires JWT authentication with USER role
    @PreAuthorize("isAuthenticated()")
    public String sendOrderConfirmation(@RequestBody EmailRequest emailRequest) {
        try {
            ns.sendOrderConfirmation(emailRequest.getTo(), emailRequest.getOrderDetails());
            return "Order confirmation sent successfully to " + emailRequest.getTo();
        } catch (Exception e) {
            logger.error("Failed to send order confirmation: {}", e.getMessage(), e);
            return "Failed to send order confirmation: " + e.getMessage();
        }
    }
}

// Add this DTO class in the same file or separate file
class EmailRequest {
    private String to;
    private String orderDetails;
    
    // Getters and setters
    public String getTo() { return to; }
    public void setTo(String to) { this.to = to; }
    public String getOrderDetails() { return orderDetails; }
    public void setOrderDetails(String orderDetails) { this.orderDetails = orderDetails; }
}