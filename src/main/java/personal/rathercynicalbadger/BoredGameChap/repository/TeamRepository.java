package personal.rathercynicalbadger.BoredGameChap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import personal.rathercynicalbadger.BoredGameChap.entity.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
