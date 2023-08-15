package feedback.workshop.application.service;

import java.util.List;
import java.util.Optional;

import feedback.workshop.domain.Feedback;

public interface FeedbackService {
    Feedback saveFeedback(Feedback feedback);
    Optional<Feedback> getFeedbackById(Integer id);
    List<Feedback> getFeedbackByGivenBy(String givenBy);
    List<Feedback> getAllFeedback();
}