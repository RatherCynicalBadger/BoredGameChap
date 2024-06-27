package personal.rathercynicalbadger.BoredGameChap.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import personal.rathercynicalbadger.BoredGameChap.entity.Game;
import personal.rathercynicalbadger.BoredGameChap.entity.User;
import personal.rathercynicalbadger.BoredGameChap.security.CurrentUser;
import personal.rathercynicalbadger.BoredGameChap.service.GameService;
import personal.rathercynicalbadger.BoredGameChap.service.UserService;

import java.util.List;

@Controller
@AllArgsConstructor
public class GameController {
    private final GameService gameService;
    private final UserService userService;

    @GetMapping("/bgc/game/add")
    public String addGameViewForm() {
        return "/bgc/add-game-menu";
    }

    @PostMapping("/bgc/game/search")
    public String gameSearchResults(Model model, @RequestParam String title) {
        model.addAttribute("results", gameService.findAllByTitleLike(title));
        return "/bgc/add-game-list";
    }

    @PostMapping("/bgc/add_game")
    public void addGameAction(@ModelAttribute Game gameToAdd, @AuthenticationPrincipal CurrentUser currentUser) {
        User user = userService.findById(currentUser.getUser().getId());
        List<Game> testUserGames = gameService.findAllOwnedByUser(currentUser.getUser());
        user.setOwnedGames(testUserGames);
        userService.save(user);
    }

    @GetMapping("/bgc/game/add_by_hand")
    public String addGameByHandForm(Model model) {
        model.addAttribute("gameToAdd", new Game());
        return "/bgc/add-game-by-hand";
    }

    @PostMapping("/bgc/game/add_by_hand")
    public String addGameToDB(@ModelAttribute(name = "gameToAdd") Game gameToAdd) {
        gameToAdd = gameService.save(gameToAdd);
        return "redirect:/bgc/game/add_to_collection?gameId=" + gameToAdd.getId();
    }
}
