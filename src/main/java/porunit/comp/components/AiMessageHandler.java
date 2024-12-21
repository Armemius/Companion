package porunit.comp.components;


import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class AiMessageHandler {

    private final OpenAiChatModel chatModel;

    private final String SYSTEM_TEXT = """
            You are a helpful AI assistant that helps people talk with other peoples avatars.
            Other peoples avatars depends on data which provided below.
            User this data about human to talk/chat like him.
            
            {data}
            
            """;

    public List<Generation> generateAnswer(String text, String data) {
        SystemPromptTemplate template = new SystemPromptTemplate(SYSTEM_TEXT);
        Message systemMessage = template.createMessage(Map.of("data", data));
        Message userMessage = new UserMessage(text);

        Prompt prompt = new Prompt(List.of(userMessage, systemMessage));

        return chatModel.call(prompt).getResults();
    }

    public String generateAnswer(String text) {
        return chatModel.call(text);
    }

}
