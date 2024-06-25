package personal.rathercynicalbadger.BoredGameChap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import personal.rathercynicalbadger.BoredGameChap.entity.Game;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {

    List<Game> findAllByTitleContainingIgnoreCase(String title);

    @Query("SELECT g FROM Game g JOIN g.owners o WHERE o.id = :userId")
    List<Game> findAllGamesByOwnerId(@Param("userId") Long userId);

    @Query("SELECT g.title FROM Game g JOIN g.owners o WHERE o.id = :userId")
    List<String> findAllGamesSimpleByOwnerId(@Param("userId") Long userId);
}
