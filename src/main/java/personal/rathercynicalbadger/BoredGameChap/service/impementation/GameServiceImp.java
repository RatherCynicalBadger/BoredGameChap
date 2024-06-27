package personal.rathercynicalbadger.BoredGameChap.service.impementation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import personal.rathercynicalbadger.BoredGameChap.entity.Game;
import personal.rathercynicalbadger.BoredGameChap.entity.User;
import personal.rathercynicalbadger.BoredGameChap.repository.GameRepository;
import personal.rathercynicalbadger.BoredGameChap.service.GameService;

import java.util.List;

@Service
@AllArgsConstructor
public class GameServiceImp implements GameService {
    private final GameRepository gameRepo;

    @Override
    public Game findById(Long gameId) {
        return gameRepo.findById(gameId).orElseThrow();
    }

    @Override
    public List<Game> findAllOwnedByUser(User user) {
        return gameRepo.findAllGamesByOwnerId(user.getId());
    }

    @Override
    public List<Game> findAllByTitleLike(String title) {
        return gameRepo.findAllByTitleContainingIgnoreCase(title);
    }

    @Override
    public Game save(Game game) {
        return gameRepo.save(game);
    }
}
