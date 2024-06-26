package personal.rathercynicalbadger.BoredGameChap.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import personal.rathercynicalbadger.BoredGameChap.entity.Invite;
import personal.rathercynicalbadger.BoredGameChap.entity.Team;
import personal.rathercynicalbadger.BoredGameChap.entity.User;
import personal.rathercynicalbadger.BoredGameChap.repository.InviteRepository;
import personal.rathercynicalbadger.BoredGameChap.repository.TeamRepository;
import personal.rathercynicalbadger.BoredGameChap.repository.UserRepository;

@Controller
@AllArgsConstructor
public class InviteController {
    private final InviteRepository inviteRepo;
    private final UserRepository userRepo;
    private final TeamRepository teamRepo;

    @PostMapping("/bgc/team/invite")
    public String sendInvite(@RequestParam String username, @RequestParam Long teamId) {
        Long invitedId = userRepo.findIdByUsername(username).orElseThrow(
                () -> new EntityNotFoundException("No user with given name.")
        );
        Invite invite = new Invite();
        invite.setInvitedId(invitedId);
        invite.setTeamId(teamId);
        invite.setTeamName(teamRepo.findNameById(teamId));
        inviteRepo.save(invite);
        return "redirect:/bgc/team/" + teamId + "/admin";
    }

    @PostMapping("/bgc/invite/accept")
    public String acceptInvite(@RequestParam Long inviteId) {
        Invite invite = inviteRepo.getReferenceById(inviteId);
        Team team = teamRepo.findById(invite.getTeamId()).orElseThrow(
                () -> new EntityNotFoundException("No team with given id")
        );
        User invited = userRepo.findById(invite.getInvitedId()).orElseThrow(
                () -> new EntityNotFoundException("No user with given id")
        );
        team.getMembers().add(invited);
        teamRepo.save(team);
        inviteRepo.deleteById(inviteId);
        return "redirect:/bgc/team/" + team.getId();
    }
    @PostMapping("/bgc/invite/reject")
    public String rejectInvite(@RequestParam Long inviteId) {
        inviteRepo.deleteById(inviteId);
        return "redirect:/bgc";
    }
}
