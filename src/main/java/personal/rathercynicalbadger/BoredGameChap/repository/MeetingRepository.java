package personal.rathercynicalbadger.BoredGameChap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import personal.rathercynicalbadger.BoredGameChap.entity.Meeting;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {
}
