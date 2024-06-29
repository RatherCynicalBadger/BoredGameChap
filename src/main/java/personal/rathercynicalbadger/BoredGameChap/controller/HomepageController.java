package personal.rathercynicalbadger.BoredGameChap.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import personal.rathercynicalbadger.BoredGameChap.entity.User;
import personal.rathercynicalbadger.BoredGameChap.service.UserService;

@Controller
@AllArgsConstructor
public class HomepageController {
    private UserService userService;

    @GetMapping("/home")
    public String homepage() {
        return "/home/homepage";
    }

    @GetMapping("/home/about")
    public String about() {
        return "/home/about";
    }

    @GetMapping("/home/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "/home/register";
    }

    @PostMapping("/home/register")
    public String register(@ModelAttribute User user) {
        userService.save(user);
        return "redirect:/bgc";
    }
}
