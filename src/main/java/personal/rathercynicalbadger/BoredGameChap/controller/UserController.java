package personal.rathercynicalbadger.BoredGameChap.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import personal.rathercynicalbadger.BoredGameChap.entity.Game;
import personal.rathercynicalbadger.BoredGameChap.entity.User;
import personal.rathercynicalbadger.BoredGameChap.security.CurrentUser;
import personal.rathercynicalbadger.BoredGameChap.service.GameService;
import personal.rathercynicalbadger.BoredGameChap.service.InviteService;
import personal.rathercynicalbadger.BoredGameChap.service.UserService;

@Controller
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final GameService gameService;
    private final InviteService inviteService;

    @Secured("ROLE_USER")
    @GetMapping("/bgc")
    public String showUserDashboard(Model model, @AuthenticationPrincipal CurrentUser currentUser) {
        model.addAttribute("ownedGames", gameService.findAllOwnedByUser(currentUser.getUser()));
        model.addAttribute("invites", inviteService.findAllByInvitedId(currentUser.getUser().getId()));
        return "/bgc/dashboard";
    }

    @Secured("ROLE_USER")
    @GetMapping("/bgc/game/owned")
    public String listOwnedGames(Model model, @AuthenticationPrincipal CurrentUser currentUser) {
        model.addAttribute("owned", gameService.findAllOwnedByUser(currentUser.getUser()));
        return "/bgc/owned-games";
    }

    @Secured("ROLE_USER")
    //TODO fix Hibernate weirding out with update queries
    @RequestMapping("/bgc/game/add_to_collection")
    public String addToUserCollection(@RequestParam Long gameId, @AuthenticationPrincipal CurrentUser currentUser) {
        User user = currentUser.getUser();
        Game game = gameService.findById(gameId);
        user.getOwnedGames().add(game);
        userService.save(user);
        return "redirect:/bgc/game/owned";
    }
}
