package porunit.comp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import porunit.comp.data.domain.Feedback;
import porunit.comp.data.dto.FeedbackDTO;
import porunit.comp.services.AuthService;
import porunit.comp.services.FeedbackService;

import java.util.List;

@RestController
@RequestMapping("/feedback")
@RequiredArgsConstructor
public class FeedbackController {
    private final FeedbackService feedbackService;
    private final AuthService authService;

    @PostMapping
    public ResponseEntity<?> saveFeedback(@RequestBody FeedbackDTO feedbackDTO,
                                          @RequestHeader("Authorization") String token) {

        String login = authService.getLoginFromHeader(token);

        feedbackService.saveFeedback(feedbackDTO, login);
        return ResponseEntity.ok("✅");
    }

    @GetMapping
    public ResponseEntity<?> getAllFeedbacks(@RequestHeader("Authorization") String token) {
        String login = authService.getLoginFromHeader(token);
        List<FeedbackDTO> feedbacks = feedbackService.getAllFeedbacks(login);
        return ResponseEntity.ok(feedbacks);
    }
}
