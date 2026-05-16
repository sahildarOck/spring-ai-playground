package com.springai.playground.service;

import com.springai.playground.dto.AIResponse;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatService {
    private final ChatClient chatClient;
    private final List<Message> history;

    public ChatService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
        history = new ArrayList<>();
    }

    /**
     *
     * @param prompt
     * @return
     */
    public AIResponse chat(String prompt) {
        history.add(new UserMessage(prompt));
        var responseContent = chatClient.prompt().messages(history).call().content();
        history.add(new AssistantMessage(responseContent));
        return new AIResponse(responseContent);
    }
}
