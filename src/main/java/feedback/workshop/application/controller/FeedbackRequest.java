package feedback.workshop.application.controller;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FeedbackRequest {
    private Integer id;
    private String givenBy;
    private String feedback;

    @JsonCreator
    public FeedbackRequest(@JsonProperty("id") Integer id, @JsonProperty("givenBy") String givenBy, @JsonProperty("feedback") String feedback) {
        this.id = id;
        this.givenBy = givenBy;
        this.feedback = feedback;
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