package feedback.workshop.application.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import feedback.workshop.application.domain.entity.Feedback;
import feedback.workshop.application.domain.repository.FeedbackRepository;
import feedback.workshop.application.usecase.GetFeedbackByGivenByUseCase;

@Service
public class GetFeedbackByGivenByUseCaseImplService implements GetFeedbackByGivenByUseCase {
    private final FeedbackRepository feedbackRepository;

    public GetFeedbackByGivenByUseCaseImplService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    @Override
    public List<Feedback> getFeedbackByGivenBy(String givenBy) {
        return feedbackRepository.findByGivenBy(givenBy);
    }
}