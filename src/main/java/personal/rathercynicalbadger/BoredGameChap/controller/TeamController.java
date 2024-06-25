package personal.rathercynicalbadger.BoredGameChap.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import personal.rathercynicalbadger.BoredGameChap.entity.Team;
import personal.rathercynicalbadger.BoredGameChap.entity.User;
import personal.rathercynicalbadger.BoredGameChap.repository.MeetingRepository;
import personal.rathercynicalbadger.BoredGameChap.repository.PollRepository;
import personal.rathercynicalbadger.BoredGameChap.repository.TeamRepository;
import personal.rathercynicalbadger.BoredGameChap.repository.UserRepository;

@Controller
@AllArgsConstructor
public class TeamController {
    private final TeamRepository teamRepo;
    private final UserRepository userRepo;
    private final MeetingRepository meetingRepo;
    private final PollRepository pollRepo;

    @GetMapping("/bgc/team/{teamId}")
    public String teamDashboard(@PathVariable Long teamId) {
        return "/bgc/team/team-dashboard";
    }

    @GetMapping("/bgc/team/create")
    public String createTeamForm(Model model) {
        model.addAttribute("teamToCreate", new Team());
        return "/bgc/team/create-team";
    }

    @PostMapping("/bgc/team/create")
    public String createTeamAction(@ModelAttribute Team teamToCreate,
                                   @RequestParam Long teamAdminId) {
        teamToCreate.setTeamAdminId(teamAdminId);
        User user = userRepo.findById(teamAdminId)
                .orElseThrow(() -> new EntityNotFoundException("User that created group not in DB?"));
        teamToCreate.getMembers().add(user);
        teamToCreate = teamRepo.save(teamToCreate);
        return "redirect:/bgc/team/" + teamToCreate.getId();
    }
}
