package personal.rathercynicalbadger.BoredGameChap.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import personal.rathercynicalbadger.BoredGameChap.repository.TeamRepository;

@Controller
@AllArgsConstructor
public class TeamController {
    private final TeamRepository teamRepo;
}
