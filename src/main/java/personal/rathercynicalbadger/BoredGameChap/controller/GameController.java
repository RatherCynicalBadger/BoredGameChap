package personal.rathercynicalbadger.BoredGameChap.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import personal.rathercynicalbadger.BoredGameChap.entity.Game;
import personal.rathercynicalbadger.BoredGameChap.service.BGGAPIService;
import personal.rathercynicalbadger.BoredGameChap.service.GameService;

@Controller
@AllArgsConstructor
public class GameController {
    private final GameService gameService;
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

    @PostMapping("/bgc/game/add_to_local_db")
    public String addGameFromAPIToDB(@RequestParam Long bggId) {
        Game game = apiService.fetchGame(bggId);
        game = gameService.save(game);
        return "redirect:/bgc/game/add_to_collection?gameId=" + game.getId();
    }

    @GetMapping("/bgc/game/add_by_hand")
    public String addGameByHandForm(Model model) {
        model.addAttribute("gameToAdd", new Game());
        return "/bgc/add-game-by-hand";
    }

    @PostMapping("/bgc/game/add_by_hand")
    public String addGameToDB(@Valid @ModelAttribute(name = "gameToAdd") Game gameToAdd, BindingResult br) {
        if (br.hasErrors()) {
            return "/bgc/add-game-by-hand";
        }
        gameToAdd = gameService.save(gameToAdd);
        return "redirect:/bgc/game/add_to_collection?gameId=" + gameToAdd.getId();
    }

}
