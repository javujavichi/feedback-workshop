package feedback.workshop.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import feedback.workshop.domain.Feedback;

import java.util.List;


@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
    List<Feedback> findByGivenBy(String givenBy);
    List<Feedback> findByFeedback(String feedback);
    List<Feedback> findAllByGivenBy(String givenBy);

    @SuppressWarnings("unchecked")
    Feedback save(Feedback feedback);
}
