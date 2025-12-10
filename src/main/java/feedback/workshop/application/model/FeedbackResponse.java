package feedback.workshop.application.model;

import feedback.workshop.application.domain.entity.Feedback;

public class FeedbackResponse {
    private Integer id;
    private String givenBy;
    private String feedback;

    public FeedbackResponse() {
    }

    public FeedbackResponse(Integer id, String givenBy, String feedback) {
        this.id = id;
        this.givenBy = givenBy;
        this.feedback = feedback;
    }

    public static FeedbackResponse fromFeedback(Feedback feedback) {
        return new FeedbackResponse(feedback.getId(), feedback.getGivenBy(), feedback.getFeedback());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGivenBy() {
        return givenBy;
    }

    public void setGivenBy(String givenBy) {
        this.givenBy = givenBy;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}