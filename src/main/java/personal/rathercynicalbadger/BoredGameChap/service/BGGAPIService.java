package personal.rathercynicalbadger.BoredGameChap.service;

import personal.rathercynicalbadger.BoredGameChap.entity.Game;

import java.util.Map;

public interface BGGAPIService {
    Map<Integer, String> searchByTitle(String title);

    Game fetchGame(Integer bggId);

}
