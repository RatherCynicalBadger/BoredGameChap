package personal.rathercynicalbadger.BoredGameChap.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import personal.rathercynicalbadger.BoredGameChap.entity.Game;
import personal.rathercynicalbadger.BoredGameChap.entity.User;
import personal.rathercynicalbadger.BoredGameChap.repository.GameRepository;
import personal.rathercynicalbadger.BoredGameChap.repository.InviteRepository;
import personal.rathercynicalbadger.BoredGameChap.repository.UserRepository;

//TODO replace temporary IDs with actual IDs once Spring Security is on
@Controller
@AllArgsConstructor
public class UserController {
    private final UserRepository userRepo;
    private final GameRepository gameRepo;
    private final InviteRepository inviteRepo;
    private final long TEST_USER_ID = 1L;

    @GetMapping("/bgc")
    public String showUserDashboard(Model model) {
        model.addAttribute("ownedGamesSimple", gameRepo.findAllGamesNamesByOwnerId(TEST_USER_ID));
        model.addAttribute("invites", inviteRepo.findAllByInvitedId(TEST_USER_ID));
        return "/bgc/dashboard";
    }

    @GetMapping("/bgc/game/owned")
    public String listOwnedGames(Model model) {
        model.addAttribute("owned", gameRepo.findAllGamesByOwnerId(TEST_USER_ID));
        return "/bgc/owned-games";
    }

    //TODO fix Hibernate weirding out with update queries
    @GetMapping("/bgc/game/add_to_collection")
    public String addToUserCollection(@RequestParam Long gameId, @RequestParam Long userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found."));
        Game game = gameRepo.findById(gameId).orElseThrow(() -> new EntityNotFoundException("Game not found."));
        user.getOwnedGames().add(game);
        userRepo.save(user);
        return "redirect:/bgc/game/owned";
    }
}
