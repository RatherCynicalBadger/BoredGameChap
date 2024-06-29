package personal.rathercynicalbadger.BoredGameChap.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import personal.rathercynicalbadger.BoredGameChap.entity.Team;
import personal.rathercynicalbadger.BoredGameChap.security.CurrentUser;
import personal.rathercynicalbadger.BoredGameChap.service.MeetingService;
import personal.rathercynicalbadger.BoredGameChap.service.TeamService;

@Controller
@Secured("ROLE_USER")
@AllArgsConstructor
public class TeamController {
    private final TeamService teamService;
    private final MeetingService meetingService;

    @GetMapping("/bgc/team/{teamId}")
    public String teamDashboard(@PathVariable Long teamId, Model model, @AuthenticationPrincipal CurrentUser currentUser) {
        model.addAttribute("team", teamService.findById(teamId));
        model.addAttribute("meetings", meetingService.findAllByTeamIdOrderedByTime(teamId));
        model.addAttribute("userId", currentUser.getUser().getId());
        return "/bgc/team/team-dashboard";
    }

    @GetMapping("/bgc/team/create")
    public String createTeamForm(Model model) {
        model.addAttribute("teamToCreate", new Team());
        return "/bgc/team/create-team";
    }

    @PostMapping("/bgc/team/create")
    public String createTeamAction(@ModelAttribute Team teamToCreate,
                                   @AuthenticationPrincipal CurrentUser currentUser) {
        teamToCreate.setTeamAdminId(currentUser.getUser().getId());
        teamToCreate.getMembers().add(currentUser.getUser());
        teamService.save(teamToCreate);
        teamToCreate = teamService.findByName(teamToCreate.getName());
        return "redirect:/bgc/team/" + teamToCreate.getId();
    }

    @Secured("ROLE_TEAM_ADMIN")
    @GetMapping("/bgc/team/{teamId}/admin")
    public String teamAdminPanel(@PathVariable Long teamId, Model model) {
        model.addAttribute("team", teamService.findById(teamId));
        return "/bgc/team/admin/team-control-panel";
    }

    @PostMapping("/bgc/team/{teamId}/edit")
    public String submitChanges(@ModelAttribute Team team, @PathVariable Long teamId) {
        Team toUpdate = teamService.findById(teamId);
        toUpdate.setLocation(team.getLocation());
        toUpdate.setAbout(team.getAbout());
        teamService.save(toUpdate);
        return "redirect:/bgc/team/" + teamId;
    }
}
