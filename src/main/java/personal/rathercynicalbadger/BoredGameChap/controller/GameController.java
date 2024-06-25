package personal.rathercynicalbadger.BoredGameChap.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import personal.rathercynicalbadger.BoredGameChap.entity.Game;
import personal.rathercynicalbadger.BoredGameChap.entity.User;
import personal.rathercynicalbadger.BoredGameChap.repository.GameRepository;
import personal.rathercynicalbadger.BoredGameChap.repository.UserRepository;

import java.util.List;

//TODO replace temporary IDs with actual IDs once Spring Security is on
@Controller
@AllArgsConstructor
public class GameController {
    private final GameRepository gameRepo;
    private final UserRepository userRepo;
    private final long TEST_USER_ID = 1L;

    @GetMapping("/bgc/game/add")
    public String addGameViewForm() {
        return "/bgc/add-game-menu";
    }

    @PostMapping("/bgc/game/search")
    public String gameSearchResults(Model model, @RequestParam String title) {
        model.addAttribute("results", gameRepo.findAllByTitleContainingIgnoreCase(title));
        return "/bgc/add-game-list";
    }

    @PostMapping("/bgc/add_game")
    public void addGameAction(@ModelAttribute Game gameToAdd) {
        User testUser = userRepo.findById(TEST_USER_ID).orElseThrow();
        List<Game> testUserGames = gameRepo.findAllGamesByOwnerId(TEST_USER_ID);
        testUser.setOwnedGames(testUserGames);
        userRepo.save(testUser);
    }

    @GetMapping("/bgc/game/add_by_hand")
    public String addGameByHandForm(Model model) {
        model.addAttribute("gameToAdd", new Game());
        return "/bgc/add-game-by-hand";
    }

    @PostMapping("/bgc/game/add_by_hand")
    public String addGameToDB(@ModelAttribute(name = "gameToAdd") Game gameToAdd) {
        gameToAdd = gameRepo.save(gameToAdd);
        User user = userRepo.getReferenceById(TEST_USER_ID);
        return "redirect:/bgc/game/add_to_collection?gameId=" + gameToAdd.getId() + "&userId=" + user.getId();
    }
}
