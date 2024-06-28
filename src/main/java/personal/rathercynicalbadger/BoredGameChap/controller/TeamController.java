package personal.rathercynicalbadger.BoredGameChap.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import personal.rathercynicalbadger.BoredGameChap.entity.Team;
import personal.rathercynicalbadger.BoredGameChap.entity.User;
import personal.rathercynicalbadger.BoredGameChap.service.MeetingService;
import personal.rathercynicalbadger.BoredGameChap.service.TeamService;
import personal.rathercynicalbadger.BoredGameChap.service.UserService;

@Controller
@Secured("ROLE_USER")
@AllArgsConstructor
public class TeamController {
    private final TeamService teamService;
    private final UserService userService;
    private final MeetingService meetingService;

    @GetMapping("/bgc/team/{teamId}")
    public String teamDashboard(@PathVariable Long teamId, Model model) {
        model.addAttribute("team", teamService.findById(teamId));
        model.addAttribute("meetings", meetingService.findAllByTeamIdOrderedByTime(teamId));
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
        User user = userService.findById(teamAdminId);
        teamToCreate.getMembers().add(user);
        teamService.save(teamToCreate);
        teamToCreate = teamService.findByName(teamToCreate.getName());
        return "redirect:/bgc/team/" + teamToCreate.getId();
    }

    @Secured("ROLE_TEAM_ADMIN")
    @GetMapping("/bgc/team/{teamId}/admin")
    public String teamAdminPanel(@PathVariable Long teamId, Model model) {
        model.addAttribute("teamId", teamId);
        return "/bgc/team/admin/team-control-panel";
    }
}
