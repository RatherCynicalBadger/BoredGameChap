package personal.rathercynicalbadger.BoredGameChap.service;

import personal.rathercynicalbadger.BoredGameChap.entity.Game;
import personal.rathercynicalbadger.BoredGameChap.entity.User;

import java.util.List;

public interface GameService {
    Game findById(Long gameId);

    List<Game> findAllOwnedByUser(User user);

    List<Game> findAllByTitleLike(String title);

    Game save(Game game);
}
