package feedback.workshop.application.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FeedbackRepository extends JpaRepository<FeedBackJpa, Integer> {
    List<FeedBackJpa> findAllByGivenBy(String givenBy);
}

