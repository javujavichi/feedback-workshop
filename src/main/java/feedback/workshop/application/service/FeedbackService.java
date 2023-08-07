package feedback.workshop.application.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import feedback.workshop.application.controller.FeedbackRequest;
import feedback.workshop.application.controller.FeedbackResponse;
import feedback.workshop.application.persistence.FeedBackJpa;
import feedback.workshop.application.persistence.FeedbackRepository;

@Service
public class FeedbackService {
  
  private final FeedbackRepository feedbackRepository;
    private final FeedbackMapper mapper;

    public FeedbackService(FeedbackRepository feedbackRepository, FeedbackMapper mapper) {
        this.feedbackRepository = feedbackRepository;
        this.mapper = mapper;
    }

    public void save(FeedbackRequest feedbackRequest) {
        var feedbackJpa = mapper.toJpa(feedbackRequest);
        this.feedbackRepository.save(feedbackJpa);
    }

    public List<FeedbackResponse> findById(Integer id) {
        var results = this.feedbackRepository.findById(id);
        if(results.isPresent()) {
            return this.mapper.toResponse((List<FeedBackJpa>) results.get());
        }
        return new ArrayList<>();
    }

    public List<FeedbackResponse> findAllByGivenBy(String givenBy) {
        var results = this.feedbackRepository.findAllByGivenBy(givenBy);
        return this.mapper.toResponse(results);
    }

    public List<FeedbackResponse> findAll() {
        var results = this.feedbackRepository.findAll();
        return this.mapper.toResponse(results);
    }
}
