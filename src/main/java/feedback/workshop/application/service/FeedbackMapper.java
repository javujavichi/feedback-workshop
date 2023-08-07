package feedback.workshop.application.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import feedback.workshop.application.controller.FeedbackRequest;
import feedback.workshop.application.controller.FeedbackResponse;
import feedback.workshop.application.persistence.FeedBackJpa;

@Component
public class FeedbackMapper {
  public List<FeedbackResponse> toResponse(List<FeedBackJpa> results){
        List<FeedbackResponse> responses = new ArrayList<>();
        results.forEach(jpa -> responses.add(new FeedbackResponse(jpa.getId(), jpa.getGivenBy(), jpa.getFeedback())));
        return responses;
    }

    public FeedBackJpa toJpa(FeedbackRequest feedbackRequest){
        var feebackJpa = new FeedBackJpa();
        feebackJpa.setId(feedbackRequest.getId());
        feebackJpa.setGivenBy(feedbackRequest.getGivenBy());
        feebackJpa.setFeedback(feedbackRequest.getFeedback());
        return feebackJpa;
    }
}
