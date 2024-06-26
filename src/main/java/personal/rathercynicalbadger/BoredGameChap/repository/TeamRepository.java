package personal.rathercynicalbadger.BoredGameChap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import personal.rathercynicalbadger.BoredGameChap.entity.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {

    @Query("SELECT t.name FROM Team t WHERE t.id = :teamId")
    String findNameById(@Param("teamId") Long teamId);
}
