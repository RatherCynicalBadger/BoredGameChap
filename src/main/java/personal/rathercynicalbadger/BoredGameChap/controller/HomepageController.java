package personal.rathercynicalbadger.BoredGameChap.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import personal.rathercynicalbadger.BoredGameChap.entity.User;
import personal.rathercynicalbadger.BoredGameChap.repository.UserRepository;

@Controller
@AllArgsConstructor
public class HomepageController {
    private final UserRepository userRepo;

    @GetMapping("/")
    public String homepage() {
        return "/always-accessible/homepage";
    }

    @GetMapping("/about")
    public String about() {
        return "/always-accessible/about";
    }

    @GetMapping("/signup")
    public String signup() {
        return "/always-accessible/signup";
    }

    @PostMapping("/signup")
    public void saveUser(User user) {
        user = userRepo.save(user);
        //for testing of getting id from db
        System.out.println(user);
    }
}
