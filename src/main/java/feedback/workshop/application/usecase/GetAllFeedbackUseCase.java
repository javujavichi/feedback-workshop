package feedback.workshop.application.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import feedback.workshop.domain.Feedback;
import feedback.workshop.infrastructure.persistence.FeedbackRepository;

@Service
public class GetAllFeedbackUseCase {
    private final FeedbackRepository feedbackRepository;

    public GetAllFeedbackUseCase(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAll();
    }
}