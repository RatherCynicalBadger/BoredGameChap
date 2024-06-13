package personal.rathercynicalbadger.BoredGameChap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import personal.rathercynicalbadger.BoredGameChap.entity.Poll;

public interface PollRepository extends JpaRepository<Poll, Long> {
}
