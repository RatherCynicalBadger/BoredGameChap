package personal.rathercynicalbadger.BoredGameChap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import personal.rathercynicalbadger.BoredGameChap.entity.Poll;

@Repository
public interface PollRepository extends JpaRepository<Poll, Long> {
}
