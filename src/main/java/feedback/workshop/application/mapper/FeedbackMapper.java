package feedback.workshop.application.mapper;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import feedback.workshop.application.controller.FeedbackRequest;
import feedback.workshop.application.usecase.SubmitFeedbackResponse;
import feedback.workshop.domain.Feedback;

@Component
public class FeedbackMapper {
    public List<SubmitFeedbackResponse> toResponse(List<Feedback> results){
        List<SubmitFeedbackResponse> responses = new ArrayList<>();
        results.forEach(feedback -> responses.add(new SubmitFeedbackResponse()));
        return responses;
    }

    public Feedback toDomain(FeedbackRequest feedbackRequest){
        var feedback = new Feedback(feedbackRequest.getGivenBy(), feedbackRequest.getFeedback());
        feedback.setId(feedbackRequest.getId());
        return feedback;
    }

    public List<Feedback> mapToFeedbackList(List<SubmitFeedbackResponse> feedbackList) {
        List<Feedback> feedbacks = new ArrayList<>();
        feedbackList.forEach(response -> {
            Feedback feedback = new Feedback(response.getGivenBy(), response.getFeedback());
            feedback.setId(response.getId());
            feedbacks.add(feedback);
        });
        return feedbacks;
    }
    public Feedback toFeedback(FeedbackRequest feedbackRequest) {
        Feedback feedback = new Feedback();
        feedback.setGivenBy(feedbackRequest.getGivenBy());
        feedback.setFeedback(feedbackRequest.getFeedback());
        return feedback;
    }
}
