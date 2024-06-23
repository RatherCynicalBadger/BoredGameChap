package personal.rathercynicalbadger.BoredGameChap.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import personal.rathercynicalbadger.BoredGameChap.entity.Game;
import personal.rathercynicalbadger.BoredGameChap.repository.GameRepository;

@Controller
@AllArgsConstructor
public class GameController {
    private final GameRepository gameRepo;

    @GetMapping("/addGame")
    public String addGame(Model model) {
        model.addAttribute("newGame", new Game());
        return "user/add-game";
    }

    @PostMapping("/addGame")
    public void saveGame(@ModelAttribute Game newGame) {
        //TODO case 1 - game is new; case 2 - game is already in DB
        gameRepo.save(newGame);
    }

    @GetMapping("/myGames/{userId}")
    public String ownedGames(Model model, @PathVariable long userId) {
        //TODO
        //model.addAttribute("ownedGames", );
        return "user/owned-games";
    }
}
