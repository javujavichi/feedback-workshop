package feedback.workshop.application.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "feedback")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "given_by")
    private String givenBy;

    @Column(name = "feedback")
    private String feedback;

    public Feedback() {}

    public Feedback(String givenBy, String feedback) {
        this.givenBy = givenBy;
        this.feedback = feedback;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer integer) {
        this.id = integer;
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