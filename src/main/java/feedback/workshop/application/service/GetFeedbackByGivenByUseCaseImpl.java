package feedback.workshop.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import feedback.workshop.application.usecase.GetFeedbackByGivenByUseCase;
import feedback.workshop.domain.Feedback;
import feedback.workshop.infrastructure.persistence.FeedbackRepository;

@Service
public class GetFeedbackByGivenByUseCaseImpl implements GetFeedbackByGivenByUseCase {
    private final FeedbackRepository feedbackRepository;

    public GetFeedbackByGivenByUseCaseImpl(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    @Override
    public List<Feedback> getFeedbackByGivenBy(String givenBy) {
        return feedbackRepository.findByGivenBy(givenBy);
    }
}