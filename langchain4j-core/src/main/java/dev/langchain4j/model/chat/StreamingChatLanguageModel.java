package dev.langchain4j.model.chat;

import dev.langchain4j.agent.tool.ToolSpecification;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.ResponseHandle;
import dev.langchain4j.model.StreamingResponseHandler;
import dev.langchain4j.model.input.Prompt;

import java.util.List;

import static dev.langchain4j.data.message.UserMessage.userMessage;
import static dev.langchain4j.model.input.structured.StructuredPromptProcessor.toPrompt;
import static java.util.Collections.singletonList;

/**
 * Represents a LLM that has a chat interface and can stream responses one token at a time.
 */
public interface StreamingChatLanguageModel {

    default ResponseHandle sendUserMessage(String userMessage, StreamingResponseHandler handler) {
        return sendUserMessage(userMessage(userMessage), handler);
    }

    default ResponseHandle sendUserMessage(UserMessage userMessage, StreamingResponseHandler handler) {
        return sendMessages(singletonList(userMessage), handler);
    }

    default ResponseHandle sendUserMessage(Object structuredPrompt, StreamingResponseHandler handler) {
        Prompt prompt = toPrompt(structuredPrompt);
        return sendUserMessage(prompt.toUserMessage(), handler);
    }

    ResponseHandle sendMessages(List<ChatMessage> messages, StreamingResponseHandler handler);

    ResponseHandle sendMessages(List<ChatMessage> messages, List<ToolSpecification> toolSpecifications, StreamingResponseHandler handler);

    ResponseHandle sendMessages(List<ChatMessage> messages, ToolSpecification toolSpecification, StreamingResponseHandler handler);
}