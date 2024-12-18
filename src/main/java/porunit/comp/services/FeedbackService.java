package porunit.comp.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import porunit.comp.data.domain.Feedback;
import porunit.comp.data.domain.User;
import porunit.comp.data.dto.FeedbackDTO;
import porunit.comp.repositories.FeedbackRepository;
import porunit.comp.repositories.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final UserRepository userRepository;

    public void saveFeedback(FeedbackDTO feedbackDTO, String login) {
        Optional<User> optionalUser = userRepository.findByLogin(login);

        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        User user = optionalUser.get();

        Feedback feedback = Feedback.builder()
                .text(feedbackDTO.getText())
                .header(feedbackDTO.getHeader())
                .user(user)
                .build();

        feedbackRepository.save(feedback);
    }

    public List<FeedbackDTO> getAllFeedbacks(String login) {
        Optional<User> optionalUser = userRepository.findByLogin(login);

        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        User user = optionalUser.get();

        return feedbackRepository.findAllByUser(user).stream()
                .map(feedback -> FeedbackDTO.builder()
                        .header(feedback.getHeader())
                        .text(feedback.getText())
                        .build())
                .collect(Collectors.toList());
    }
}
