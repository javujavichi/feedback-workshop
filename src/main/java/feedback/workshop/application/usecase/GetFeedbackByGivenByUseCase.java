package feedback.workshop.application.usecase;

import java.util.List;

import feedback.workshop.application.domain.entity.Feedback;

public interface GetFeedbackByGivenByUseCase {
    List<Feedback> getFeedbackByGivenBy(String givenBy);
}