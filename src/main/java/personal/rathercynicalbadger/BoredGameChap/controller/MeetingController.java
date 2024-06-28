package personal.rathercynicalbadger.BoredGameChap.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import personal.rathercynicalbadger.BoredGameChap.entity.Meeting;
import personal.rathercynicalbadger.BoredGameChap.entity.Team;
import personal.rathercynicalbadger.BoredGameChap.service.MeetingService;
import personal.rathercynicalbadger.BoredGameChap.service.TeamService;

@Controller
@Secured("ROLE_USER")
@AllArgsConstructor
public class MeetingController {
    private final MeetingService meetingService;
    private final TeamService teamService;

    @GetMapping("/bgc/team/{teamId}/meeting/create")
    public String createMeetingForm(Model model, @PathVariable Long teamId) {
        model.addAttribute("meeting", new Meeting());
        model.addAttribute("teamId", teamId);
        return "/bgc/team/meeting/create-meeting";
    }

    @PostMapping("/bgc/team/{teamId}/meeting/create")
    public String createMeetingAction(@ModelAttribute Meeting meeting, @PathVariable Long teamId) {
        Team team = teamService.findById(teamId);
        meeting.setTeam(team);
        meetingService.save(meeting);
        return "redirect:/bgc/team/" + teamId;
    }

    @GetMapping("/bgc/team/{teamId}/meeting/{meetingId}/details")
    public String showMeetingDetails(@PathVariable Long teamId, @PathVariable Long meetingId, Model model) {
        Meeting m = meetingService.findById(meetingId);
        model.addAttribute("meeting", m);
        if (m.getPoll() != null) {
            model.addAttribute("poll", m.getPoll());
        }
        return "/bgc/team/" + teamId + "meeting/" + meetingId + "/details";
    }
}
