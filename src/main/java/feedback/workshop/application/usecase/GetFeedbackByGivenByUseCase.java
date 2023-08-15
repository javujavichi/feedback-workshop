package feedback.workshop.application.usecase;

import feedback.workshop.domain.Feedback;

import java.util.List;

public interface GetFeedbackByGivenByUseCase {
    List<Feedback> getFeedbackByGivenBy(String givenBy);
}