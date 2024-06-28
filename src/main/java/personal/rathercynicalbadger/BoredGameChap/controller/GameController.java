package personal.rathercynicalbadger.BoredGameChap.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import personal.rathercynicalbadger.BoredGameChap.entity.Game;
import personal.rathercynicalbadger.BoredGameChap.entity.User;
import personal.rathercynicalbadger.BoredGameChap.security.CurrentUser;
import personal.rathercynicalbadger.BoredGameChap.service.BGGAPIService;
import personal.rathercynicalbadger.BoredGameChap.service.GameService;
import personal.rathercynicalbadger.BoredGameChap.service.UserService;

import java.util.List;

@Controller
@AllArgsConstructor
public class GameController {
    private final GameService gameService;
    private final UserService userService;
    private final BGGAPIService apiService;

    @GetMapping("/bgc/game/add")
    public String addGameForm() {
        return "/bgc/add-game-menu";
    }

    @PostMapping("/bgc/game/search_local")
    public String gameLocalSearchResults(Model model, @RequestParam String title) {
        model.addAttribute("resultsLocal", gameService.findAllByTitleLike(title));
        return "/bgc/add-game-list";
    }

    @PostMapping("/bgc/game/search_api")
    public String gameAPISearchResults(Model model, @RequestParam String title) {
        model.addAttribute("resultsApi", apiService.searchByTitle(title));
        return "/bgc/add-game-list";
    }

//    @RequestMapping("/bgc/add_game")
//    public void addGameAction(@ModelAttribute Game gameToAdd, @AuthenticationPrincipal CurrentUser currentUser) {
//        User user = userService.findById(currentUser.getUser().getId());
//        List<Game> userGames = gameService.findAllOwnedByUser(currentUser.getUser());
//        user.setOwnedGames(userGames);
//        userService.save(user);
//    }

    @PostMapping("/bgc/game/add_to_local_db")
    public String addGameFromAPIToDB(Model model, @RequestParam Integer bggId) {
        Game game = apiService.fetchGame(bggId);
        game = gameService.save(game);
        model.addAttribute("gameToAdd", game);
        return "forward:/bgc/game/add_to_collection";
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
