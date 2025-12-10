package feedback.workshop.application.usecase.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import feedback.workshop.application.domain.entity.Feedback;
import feedback.workshop.application.domain.repository.FeedbackRepository;
import feedback.workshop.application.usecase.GetFeedbackByGivenByUseCase;

@Service
public class GetFeedbackByGivenByUseCaseImplUseCase implements GetFeedbackByGivenByUseCase {
    private final FeedbackRepository feedbackRepository;

    public GetFeedbackByGivenByUseCaseImplUseCase(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    @Override
    public List<Feedback> getFeedbackByGivenBy(String givenBy) {
        if (givenBy == null) return List.of();
        return feedbackRepository.findByGivenByIgnoreCase(givenBy);
    }
}
