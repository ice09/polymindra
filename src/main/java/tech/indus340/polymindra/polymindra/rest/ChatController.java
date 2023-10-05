package tech.indus340.polymindra.polymindra.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {
    private final StringBuilder userMessages = new StringBuilder();

    @PostMapping("/addMessage")
    public ResponseEntity<Void> addMessage(@RequestParam String username, @RequestParam String message) {
        if (username == null || message == null) {
            return ResponseEntity.badRequest().build();
        }
        userMessages.append(username + ": " + message + "###");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/messages")
    public ResponseEntity<String> getMessages() {
        return ResponseEntity.ok(userMessages.toString());
    }
}