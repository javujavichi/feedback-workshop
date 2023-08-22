package feedback.workshop.application.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import feedback.workshop.application.domain.entity.Feedback;
import feedback.workshop.application.domain.repository.FeedbackRepository;

@Service
public class FeedbackImplService implements FeedbackService {
  private final FeedbackRepository feedbackRepository;

  public FeedbackImplService(FeedbackRepository feedbackRepository) {
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
