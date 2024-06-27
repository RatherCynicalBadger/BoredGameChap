package personal.rathercynicalbadger.BoredGameChap.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import personal.rathercynicalbadger.BoredGameChap.entity.User;

@Controller
@AllArgsConstructor
public class HomepageController {

    @GetMapping("/")
    public String homepage() {
        return "/public-access/homepage";
    }

    @GetMapping("/about")
    public String about() {
        return "/public-access/about";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "/public-access/register";
    }
}
