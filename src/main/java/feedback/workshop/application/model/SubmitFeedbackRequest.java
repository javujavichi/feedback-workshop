package feedback.workshop.application.model;

public class SubmitFeedbackRequest {
    private String givenBy;
    private String feedback;

    public SubmitFeedbackRequest() {}

    public SubmitFeedbackRequest(String givenBy, String feedback) {
        this.givenBy = givenBy;
        this.feedback = feedback;
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