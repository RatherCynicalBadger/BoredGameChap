package personal.rathercynicalbadger.BoredGameChap.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import personal.rathercynicalbadger.BoredGameChap.entity.Invite;
import personal.rathercynicalbadger.BoredGameChap.entity.Team;
import personal.rathercynicalbadger.BoredGameChap.entity.User;
import personal.rathercynicalbadger.BoredGameChap.service.InviteService;
import personal.rathercynicalbadger.BoredGameChap.service.TeamService;
import personal.rathercynicalbadger.BoredGameChap.service.UserService;

@Controller
@Secured("ROLE_USER")
@AllArgsConstructor
public class InviteController {
    private final InviteService inviteService;
    private final UserService userService;
    private final TeamService teamService;

    @PostMapping("/bgc/team/invite")
    public String sendInvite(@RequestParam String username, @RequestParam Long teamId) {
        Long invitedId = userService.findIdByName(username);
        Invite invite = new Invite();
        invite.setInvitedId(invitedId);
        invite.setTeamId(teamId);
        invite.setTeamName(teamService.findNameById(teamId));
        inviteService.save(invite);
        return "redirect:/bgc/team/" + teamId + "/admin";
    }

    @PostMapping("/bgc/invite/accept")
    public String acceptInvite(@RequestParam Long inviteId) {
        Invite invite = inviteService.findById(inviteId);
        Team team = teamService.findById(invite.getTeamId());
        User invited = userService.findById(invite.getInvitedId());
        team.getMembers().add(invited);
        teamService.save(team);
        inviteService.deleteById(inviteId);
        return "redirect:/bgc/team/" + team.getId();
    }

    @PostMapping("/bgc/invite/reject")
    public String rejectInvite(@RequestParam Long inviteId) {
        inviteService.deleteById(inviteId);
        return "redirect:/bgc";
    }
}
