//package com.in28minutes.rest.webservices.restfulwebservices.todo;
//
//import java.time.format.DateTimeFormatter;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.in28minutes.rest.webservices.restfulwebservices.EmailDemo.EmailService;
//
//@RestController
//public class TodoJpaResource {
//
//	private TodoService todoService;
//	private TodoRepository todoRepository;
//	
//	@Autowired
//	private EmailService emailService;
//	
//	public TodoJpaResource(TodoService todoService,TodoRepository todoRepository) {
//		this.todoService = todoService;
//		this.todoRepository = todoRepository;
//	}
//	
//	@GetMapping("/users/{username}/todos")
//	public List<Todo> retrieveTodos(@PathVariable String username) {
//		//return todoService.findByUsername(username);
//		return todoRepository.findByUsername(username);
//	}
//	
//	@GetMapping("/users/{username}/todos/{id}")
//	public Todo retrieveTodo(@PathVariable String username,@PathVariable int id) {
//		//return todoService.findById(id);
//		return todoRepository.findById(id).get();
//	}
//	
//	@DeleteMapping("/users/{username}/todos/{id}")
//	public ResponseEntity<Void> deleteTodo(@PathVariable String username,@PathVariable int id) {
//		//todoService.deleteById(id);
//		todoRepository.deleteById(id);
//		return ResponseEntity.noContent().build();
//	}
//	
//	@PutMapping("/users/{username}/todos/{id}")
//	public Todo updateTodo(@PathVariable String username,@PathVariable int id,@RequestBody Todo todo) {
//		//todoService.updateTodo(todo);
//		todoRepository.save(todo);
//		return todo;
//	}
//	
////	@PostMapping("/users/{username}/todos")
////	public Todo createTodo(@PathVariable String username,@RequestBody Todo todo) {
////		todo.setId(null);
////		todo.setUsername(username);
////		return todoRepository.save(todo);
//////		Todo createdTodo = todoService.addTodo(username, todo.getDescription(), todo.getTargetDate(), todo.isDone());
//////		return createdTodo;
////	}
//	
//	@PostMapping("/users/{username}/todos")
//	public Todo createTodo(@PathVariable String username, @RequestBody Todo todo) {
//	    todo.setId(null);
//	    todo.setUsername(username);
//	    Todo createdTodo = todoRepository.save(todo);
//
//	    // Email notification
//	    String recipient = "adapajohnmu143@gmail.com"; // Replace with dynamic email if you want
//	    String subject = "New TODO Created!";
//	    String body = "A new TODO was created for user: " + username + "\n\nDescription: " + createdTodo.getDescription();
//
//	    try {
//	        emailService.sendSimpleMessage(recipient, subject, body);
//	    } catch (Exception ex) {
//	        System.err.println("Failed to send email: " + ex.getMessage());
//	    }
//
//	    return createdTodo;
//	}
//	
////	@PostMapping("/users/{username}/todos")
////    public Todo createTodo(@PathVariable String username, @RequestBody Todo todo) {
////        todo.setId(null);
////        todo.setUsername(username);
////        // Optionally set createdDate to now if not handled elsewhere
////        if (todo.getCreatedDate() == null) {
////            todo.setCreatedDate(java.time.LocalDateTime.now());
////        }
////        Todo createdTodo = todoRepository.save(todo);
////
////        // Email details
////        String recipient = "adapajohnmu143@gmail.com"; // replace with dynamic if needed
////        String subject = "✅ New TODO Created: " + createdTodo.getDescription();
////
////        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
////        String createdAt = createdTodo.getCreatedDate() != null ? createdTodo.getCreatedDate().format(fmt) : "N/A";
////        String targetDate = createdTodo.getTargetDate() != null ? createdTodo.getTargetDate().format(fmt) : "No due date";
////
////        String htmlBody = String.format(
////            "<h2>📝 New TODO Created!</h2>" +
////            "<table style='border-collapse:collapse;font-size:16px;'>"+
////            "<tr><td style='padding:4px 8px;'><b>User:</b></td><td>%s</td></tr>" +
////            "<tr><td style='padding:4px 8px;'><b>Description:</b></td><td>%s</td></tr>" +
////            "<tr><td style='padding:4px 8px;'><b>Created At:</b></td><td>%s</td></tr>" +
////            "<tr><td style='padding:4px 8px;'><b>Due Date:</b></td><td>%s</td></tr>" +
////            "<tr><td style='padding:4px 8px;'><b>Status:</b></td><td style='color:%s;'>%s</td></tr>" +
////            "</table><br><p>Best regards,<br><b>Your TODO App</b></p>",
////            username,
////            createdTodo.getDescription(),
////            createdAt,
////            targetDate,
////            createdTodo.isDone() ? "green" : "red",
////            createdTodo.isDone() ? "Completed" : "Pending"
////        );
////
////        try {
////            emailService.sendHtmlMessage(recipient, subject, htmlBody);
////        } catch (Exception ex) {
////            System.err.println("Failed to send email: " + ex.getMessage());
////        }
////
////        return createdTodo;
////    }
//	
//	
//}

package com.in28minutes.rest.webservices.restfulwebservices.todo;

import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.in28minutes.rest.webservices.restfulwebservices.EmailDemo.EmailService;

@RestController
public class TodoJpaResource {

    private TodoService todoService;
    private TodoRepository todoRepository;

    @Autowired
    private EmailService emailService;

    public TodoJpaResource(TodoService todoService, TodoRepository todoRepository) {
        this.todoService = todoService;
        this.todoRepository = todoRepository;
    }

    @GetMapping("/users/{username}/todos")
    public List<Todo> retrieveTodos(@PathVariable String username) {
        return todoRepository.findByUsername(username);
    }

    @GetMapping("/users/{username}/todos/{id}")
    public Todo retrieveTodo(@PathVariable String username, @PathVariable int id) {
        return todoRepository.findById(id).get();
    }

    @DeleteMapping("/users/{username}/todos/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable int id) {
        todoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/users/{username}/todos/{id}")
    public Todo updateTodo(@PathVariable String username, @PathVariable int id, @RequestBody Todo todo) {
        todoRepository.save(todo);
        return todo;
    }

    @PostMapping("/users/{username}/todos")
    public Todo createTodo(@PathVariable String username, @RequestBody Todo todo) {
        todo.setId(null);
        todo.setUsername(username);
        if (todo.getCreatedDate() == null) {
            todo.setCreatedDate(java.time.LocalDateTime.now());
        }
        Todo createdTodo = todoRepository.save(todo);

        // --- Attractive HTML Email ---
        String recipient = "adapajohnmu143@gmail.com"; // Make dynamic if you want
        String subject = "✅ New TODO Created: " + createdTodo.getDescription();

        DateTimeFormatter dateFmt = DateTimeFormatter.ofPattern("dd MMM yyyy");
        DateTimeFormatter dateTimeFmt = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");

        String createdAt = createdTodo.getCreatedDate() != null
                ? createdTodo.getCreatedDate().format(dateTimeFmt)
                : "N/A";
        String targetDate = createdTodo.getTargetDate() != null
                ? createdTodo.getTargetDate().format(dateFmt)
                : "No due date";

        String statusColor = createdTodo.isDone() ? "#2ecc40" : "#ff4136";
        String statusText = createdTodo.isDone() ? "Completed" : "Pending";

        String htmlBody = String.format(
                "<div style='font-family:Roboto,Arial,sans-serif; background:#f9f9f9; padding:24px;'>"
                        + "<div style='max-width:500px;margin:auto;background:white;padding:24px;border-radius:10px;box-shadow:0 2px 10px #ccc;'>"
                        + "<h2 style='color:#0056b3;'>📝 New TODO Created!</h2>"
                        + "<table style='width:100%%;border-collapse:collapse;font-size:16px;'>"
                        + "<tr><td style='padding:8px;font-weight:bold;'>User:</td><td style='padding:8px;'>%s</td></tr>"
                        + "<tr><td style='padding:8px;font-weight:bold;'>Description:</td><td style='padding:8px;'>%s</td></tr>"
                        + "<tr><td style='padding:8px;font-weight:bold;'>Created At:</td><td style='padding:8px;'>%s</td></tr>"
                        + "<tr><td style='padding:8px;font-weight:bold;'>Due Date:</td><td style='padding:8px;'>%s</td></tr>"
                        + "<tr><td style='padding:8px;font-weight:bold;'>Status:</td><td style='padding:8px;color:%s;'><b>%s</b></td></tr>"
                        + "</table>"
                        + "<div style='margin-top:24px;padding-top:12px;border-top:1px solid #eee;'>"
                        + "<p style='color:#888;'>Thank you for using <b>TODO App</b>!<br>Keep up your productivity 🚀</p>"
                        + "</div></div></div>",
                username,
                createdTodo.getDescription(),
                createdAt,
                targetDate,
                statusColor,
                statusText
        );

        try {
            emailService.sendHtmlMessage(recipient, subject, htmlBody);
        } catch (Exception ex) {
            System.err.println("Failed to send email: " + ex.getMessage());
        }

        return createdTodo;
    }
}