package porunit.comp.services;

import lombok.Generated;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import porunit.comp.components.AiMessageHandler;
import porunit.comp.data.domain.*;
import porunit.comp.data.dto.DialogDTO;
import porunit.comp.data.dto.MessageDTO;
import porunit.comp.repositories.*;


import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DialogService {

    private final DialogSessionRepository dialogSessionRepository;
    private final MessageRepository messageRepository;
    private final AuthService authService;
    private final UserRepository userRepository;
    private final CompanionRepository companionRepository;
    private final CompanionDataRepository companionDataRepository;
    private final AiMessageHandler aiMessageHandler;


    @Transactional
    public void saveDialog(DialogDTO dialogDTO, String token) {
        String login = authService.getLoginFromHeader(token);

        User user = userRepository.findByLogin(login).orElseThrow(() -> new IllegalArgumentException("User not found"));
        Companion companion = companionRepository.findById(dialogDTO.getCompanionId())
                .orElseThrow(() -> new IllegalArgumentException("Companion not found"));
        DialogSession dialog = DialogSession.builder()
                .companion(companion)
                .user(user)
                .creationTime(LocalDateTime.now())
                .build();

        dialogSessionRepository.save(dialog);
    }


    @Transactional
    public List<?> saveMessage(Long sessionId, MessageDTO messageDTO) {
        DialogSession session = dialogSessionRepository.findById(sessionId)
                .orElseThrow(() -> new IllegalArgumentException("DialogSession not found"));

        Companion companion = session.getCompanion();
        CompanionData companionData = companionDataRepository.findByCompanion(companion);
        String messageData = messageDTO.getData();

        Message message = Message.builder()
                .isUser(true)
                .data(messageData.getBytes(StandardCharsets.UTF_8))
                .time(LocalDateTime.now())
                .session(session)
                .build();
        messageRepository.save(message);

        if (companionData == null || companionData.getData() == null) {
            String answer = aiMessageHandler.generateAnswer(messageData);
            Message aiMessage = Message.builder()
                    .isUser(false)
                    .data(answer.getBytes(StandardCharsets.UTF_8))
                    .time(LocalDateTime.now())
                    .session(session)
                    .build();
            messageRepository.save(aiMessage);
            var list = new ArrayList<String>();
            list.add(answer);
            return list;
        }

        String companionMetaPrompt = companionData.getData();

        List<Generation> aiResponse = aiMessageHandler.generateAnswer(messageData, companionMetaPrompt);
        System.out.println(aiResponse);
        var userOpt = userRepository.findById(Long.valueOf(companion.getAuthorId()));
        Message genMessage = Message.builder()
                .isUser(false)
                .data(aiResponse.get(0).getOutput().getContent().getBytes(StandardCharsets.UTF_8))
                .time(LocalDateTime.now())
                .session(session)
                .build();

        messageRepository.save(genMessage);
        var user = userOpt.get();

        user.setBalance(user.getBalance() - 50);
        userRepository.save(user);
        return aiResponse;
    }


    public List<Integer> getAllDialogsForUser(String token) {
        String login = authService.getLoginFromHeader(token);

        User user = userRepository.findByLogin(login).orElseThrow(() -> new IllegalArgumentException("User not found"));

        List<DialogSession> dialogSessions = dialogSessionRepository.findAllByUser(user);

        return dialogSessions.stream().map(DialogSession::getSessionId).collect(Collectors.toList());
    }

    public List<MessageDTO> getAllMessagesForDialog(Long dialogId) {
        DialogSession session = dialogSessionRepository.findById(dialogId)
                .orElseThrow(() -> new IllegalArgumentException("Dialog not found"));

        List<Message> messages = messageRepository.findAllBySession(session);

        return messages.stream().map(
                x -> MessageDTO.builder()
                        .isUser(x.getIsUser())
                        .data(new String(x.getData(), StandardCharsets.UTF_8))
                        .timestamp(x.getTime())
                        .build()
        ).collect(Collectors.toList());
    }
}