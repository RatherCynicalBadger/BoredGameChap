package personal.rathercynicalbadger.BoredGameChap.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import personal.rathercynicalbadger.BoredGameChap.repository.GameRepository;
import personal.rathercynicalbadger.BoredGameChap.repository.UserRepository;

@Controller
@AllArgsConstructor
public class UserController {
    private final UserRepository userRepo;
    private final GameRepository gameRepo;

    @GetMapping(value = "/user")
    public String showUserDashboard(Model model) {
        model.addAttribute("ownedGames", gameRepo.findAllGamesByOwnerId(1L));
        return "/user/dashboard";
    }

}
