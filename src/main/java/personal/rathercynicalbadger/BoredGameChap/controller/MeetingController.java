package personal.rathercynicalbadger.BoredGameChap.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import personal.rathercynicalbadger.BoredGameChap.entity.Meeting;
import personal.rathercynicalbadger.BoredGameChap.entity.Team;
import personal.rathercynicalbadger.BoredGameChap.repository.MeetingRepository;
import personal.rathercynicalbadger.BoredGameChap.repository.TeamRepository;

@Controller
@AllArgsConstructor
public class MeetingController {
    private final MeetingRepository meetingRepo;
    private final TeamRepository teamRepo;

    @GetMapping("/bgc/team/{teamId}/meeting/create")
    public String createMeetingForm(Model model, @PathVariable Long teamId) {
        model.addAttribute("meeting", new Meeting());
        model.addAttribute("teamId", teamId);
        return "/bgc/team/meeting/create-meeting";
    }

    @PostMapping("/bgc/team/{teamId}/meeting/create")
    public String createMeetingAction(@ModelAttribute Meeting meeting, @PathVariable Long teamId) {
        Team team = teamRepo.findById(teamId).orElseThrow(
                () -> new EntityNotFoundException("No team with given id.")
        );
        meeting.setTeam(team);
        meetingRepo.save(meeting);
        return "redirect:/bgc/team/" + teamId;
    }


}
