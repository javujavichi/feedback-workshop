package feedback.workshop.application.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import feedback.workshop.application.domain.entity.Feedback;
import feedback.workshop.application.domain.repository.FeedbackRepository;

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