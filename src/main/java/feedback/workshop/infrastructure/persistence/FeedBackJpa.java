package feedback.workshop.infrastructure.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FeedBackJpa {
  
    @Id
    private Integer id;
    private String givenBy;
    @Column(length = 255)
    private String feedback;

    public FeedBackJpa() {
    }

    public Integer getId() {
        return id;
    }

    public String getGivenBy() {
        return givenBy;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setGivenBy(String givenBy) {
        this.givenBy = givenBy;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
