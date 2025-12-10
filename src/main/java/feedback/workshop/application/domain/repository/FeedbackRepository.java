package feedback.workshop.application.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import feedback.workshop.application.domain.entity.Feedback;

import java.util.List;


@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
    List<Feedback> findByGivenBy(String givenBy);
    List<Feedback> findByGivenByIgnoreCase(String givenBy);
    List<Feedback> findByFeedback(String feedback);
    List<Feedback> findAllByGivenBy(String givenBy);
    List<Feedback> findAllByGivenByIgnoreCase(String givenBy);

    @SuppressWarnings("unchecked")
    Feedback save(Feedback feedback);
}
