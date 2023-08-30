package dev.langchain4j.model.language;

import dev.langchain4j.model.ResponseHandle;
import dev.langchain4j.model.StreamingResponseHandler;
import dev.langchain4j.model.input.Prompt;

import static dev.langchain4j.model.input.structured.StructuredPromptProcessor.toPrompt;

/**
 * Represents a LLM that has a simple text interface (as opposed to a chat interface) and can stream responses one token at a time.
 * It is recommended to use the StreamingChatLanguageModel instead, as it offers better capabilities.
 * More details: https://openai.com/blog/gpt-4-api-general-availability
 */
public interface StreamingLanguageModel {

    ResponseHandle process(String text, StreamingResponseHandler handler);

    default ResponseHandle process(Prompt prompt, StreamingResponseHandler handler) {
        return process(prompt.text(), handler);
    }

    default ResponseHandle process(Object structuredPrompt, StreamingResponseHandler handler) {
        return process(toPrompt(structuredPrompt), handler);
    }
}
