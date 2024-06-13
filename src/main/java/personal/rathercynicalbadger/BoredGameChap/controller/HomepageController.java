package personal.rathercynicalbadger.BoredGameChap.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("/")
@AllArgsConstructor
public class HomepageController {

    @GetMapping("/")
    public String homepage() {
        return "/always-accessible/homepage";
    }
}
