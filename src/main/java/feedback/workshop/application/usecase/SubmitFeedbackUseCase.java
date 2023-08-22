package feedback.workshop.application.usecase;

import org.springframework.stereotype.Service;

import feedback.workshop.application.domain.entity.Feedback;
import feedback.workshop.application.domain.service.FeedbackService;
import feedback.workshop.application.infrastructure.web.model.SubmitFeedbackRequest;

@Service
public class SubmitFeedbackUseCase {
    private final FeedbackService feedbackService;

    public SubmitFeedbackUseCase(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    public Feedback submitFeedback(SubmitFeedbackRequest submitFeedbackRequest) {
        Feedback feedback = new Feedback(submitFeedbackRequest.getGivenBy(), submitFeedbackRequest.getFeedback());
        return feedbackService.saveFeedback(feedback);
    }
}
