package tech.indus340.polymindra.conv;

import org.springframework.stereotype.Service;

@Service
public class AgentConversationService {

    private final PolymindraAgent polymindraAgent;

    public AgentConversationService(PolymindraAgent polymindraAgent) {
        this.polymindraAgent = polymindraAgent;
    }

    public String passUserMessageToPolymindraAndReturnAnswer(String username, String message) {
        return polymindraAgent.chat(username + ":" + message);
    }

}
