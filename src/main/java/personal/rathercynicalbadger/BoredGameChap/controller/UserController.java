package personal.rathercynicalbadger.BoredGameChap.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import personal.rathercynicalbadger.BoredGameChap.entity.User;
import personal.rathercynicalbadger.BoredGameChap.repository.UserRepository;

@Controller("/app")
@AllArgsConstructor
public class UserController {
    private final UserRepository userRepo;

    @PostMapping("/signup")
    public void saveUser(User user) {
        user = userRepo.save(user);
        //for testing of getting id from db
        System.out.println(user);
    }
}
