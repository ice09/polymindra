package tech.indus340.polymindra.rest;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.indus340.polymindra.conv.AgentConversationService;

@RestController
public class ChatController {

    private static final Logger log = LoggerFactory.getLogger(ChatController.class);

    private static final String MESSAGE_SEPERATOR = "###";
    private static final String POLYMINDRA_PREFIX = "Polymindra: ";
    private final AgentConversationService agentConversationService;

    private final StringBuilder messages;

    public ChatController(AgentConversationService agentConversationService) {
        this.agentConversationService = agentConversationService;
        this.messages = new StringBuilder();
    }

    @PostConstruct
    private void initiateChat() {
        messages.append(
            agentConversationService.passUserMessageToPolymindraAndReturnAnswer("system",
            "Polymindra, please introduce yourself to the group chat so we can get started with the discussion.")).append(MESSAGE_SEPERATOR);
    }


    @PostMapping("/addMessage")
    public ResponseEntity<Void> addMessage(@RequestParam String username, @RequestParam String message) {
        if (username == null || message == null) {
            return ResponseEntity.badRequest().build();
        }
        messages.append(("%s: %s" + MESSAGE_SEPERATOR).formatted(username, message));

        String polymindraResponse = agentConversationService.passUserMessageToPolymindraAndReturnAnswer(username, message).replace("\n\n", "<br/>");
        log.debug(polymindraResponse);
        messages.append(polymindraResponse.startsWith(POLYMINDRA_PREFIX) ? polymindraResponse : POLYMINDRA_PREFIX + polymindraResponse).append(MESSAGE_SEPERATOR);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/messages")
    public ResponseEntity<String> getMessages() {
        return ResponseEntity.ok(messages.toString());
    }
}