package com.springai.prompttemplate.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * PromptTemplateService
 *
 * @author Uthiraraj Saminathan
 */
@Service
public class PromptTemplateService {

    private static final Logger logger = LoggerFactory.getLogger(PromptTemplateService.class);

    private final ChatModel chatModel;

    private final String systemMessage = """
            You are a specialized {language} programming assistant. Your sole purpose is to provide assistance and information related to {language} programming.
            - You should answer questions about {language} syntax, concepts, APIs, best practices, and related technologies within the {language} ecosystem.
            - For any questions or topics that are not related to {language} programming, you should politely decline and state that you are only equipped to handle {language} programming queries.
            - Do not engage in discussions or provide information on any other subject.
            - Ensure your responses are concise and directly address the {language} programming question asked.
            """;

    public PromptTemplateService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public Map<String, String> callLLM(String userPrompt, String language) {

        SystemPromptTemplate systemPromptTemplate = new SystemPromptTemplate(systemMessage);
        Message systemMessage = systemPromptTemplate.createMessage(Map.of("language", language));

        Message userMessage = new UserMessage(userPrompt);

        Prompt prompt = new Prompt(List.of(userMessage,systemMessage),
                                           ChatOptions.builder()
                                                        .model("gpt-4o-mini")
                                                        .temperature(0.2)
                                                        .topP(1.0)
                                                        .frequencyPenalty(0.0)
                                                        .presencePenalty(0.0)
                                                        .build());

       String llmResponseContent =  chatModel.call(prompt)
                                                .getResult()
                                                .getOutput()
                                                .getText();

        logger.info("LLM Response: {}", llmResponseContent);
        return Map.of("message", llmResponseContent);
    }
}
