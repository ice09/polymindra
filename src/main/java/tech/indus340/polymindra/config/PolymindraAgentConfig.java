package tech.indus340.polymindra.config;

import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.service.AiServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.indus340.polymindra.conv.PolymindraAgent;

@Configuration
public class PolymindraAgentConfig {

    @Bean
    PolymindraAgent polymindraAgent(ChatLanguageModel chatLanguageModel) {
        return AiServices.builder(PolymindraAgent.class)
                .chatLanguageModel(chatLanguageModel)
                .chatMemory(MessageWindowChatMemory.withMaxMessages(20))
                .build();
    }
}