package personal.rathercynicalbadger.BoredGameChap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import personal.rathercynicalbadger.BoredGameChap.entity.Meeting;

import java.util.List;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long> {

    @Query("SELECT m FROM Meeting m WHERE m.team.id = :teamId ORDER BY ")
    List<Meeting> findAllByTeam(@Param("teamId") Long teamId);

    List<Meeting> findAllByTeamIdOrderByTimeAsc(Long teamId);
}
