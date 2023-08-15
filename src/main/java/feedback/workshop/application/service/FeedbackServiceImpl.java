package feedback.workshop.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import feedback.workshop.domain.Feedback;
import feedback.workshop.infrastructure.persistence.FeedbackRepository;

@Service
public class FeedbackServiceImpl implements FeedbackService {
  private final FeedbackRepository feedbackRepository;

  public FeedbackServiceImpl(FeedbackRepository feedbackRepository) {
    this.feedbackRepository = feedbackRepository;
  }

  @Override
  public Feedback saveFeedback(Feedback feedback) {
    return feedbackRepository.save(feedback);
  }

  @Override
  public Optional<Feedback> getFeedbackById(Integer id) {
    return feedbackRepository.findById(id);
  }

  @Override
  public List<Feedback> getFeedbackByGivenBy(String givenBy) {
    return feedbackRepository.findAllByGivenBy(givenBy);
  }

  @Override
  public List<Feedback> getAllFeedback() {
    return feedbackRepository.findAll();
  }

}
