package feedback.workshop.application.service;

import org.springframework.stereotype.Service;

import feedback.workshop.application.domain.entity.Feedback;
import feedback.workshop.application.domain.service.FeedbackService;

@Service
public class SubmitFeedbackUseCase {
    private final FeedbackService feedbackService;

    public SubmitFeedbackUseCase(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    public Feedback submitFeedback(String givenBy, String feedbackText) {
        Feedback feedback = new Feedback(givenBy, feedbackText);
        return feedbackService.saveFeedback(feedback);
    }
}
