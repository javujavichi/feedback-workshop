package feedback.workshop.application.usecase;

import java.util.Optional;

import org.springframework.stereotype.Service;

import feedback.workshop.application.domain.entity.Feedback;
import feedback.workshop.application.domain.repository.FeedbackRepository;

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