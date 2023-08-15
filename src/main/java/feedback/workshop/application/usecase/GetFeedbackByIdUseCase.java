package feedback.workshop.application.usecase;

import feedback.workshop.domain.Feedback;
import feedback.workshop.infrastructure.persistence.FeedbackRepository;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class GetFeedbackByIdUseCase {
    private final FeedbackRepository feedbackRepository;

    public GetFeedbackByIdUseCase(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    public Optional<Feedback> getFeedbackById(Integer id) {
        return feedbackRepository.findById(id);
    }
}