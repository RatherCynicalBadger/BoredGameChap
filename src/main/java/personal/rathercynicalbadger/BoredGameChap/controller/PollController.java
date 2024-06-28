package personal.rathercynicalbadger.BoredGameChap.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import personal.rathercynicalbadger.BoredGameChap.entity.Poll;
import personal.rathercynicalbadger.BoredGameChap.security.CurrentUser;
import personal.rathercynicalbadger.BoredGameChap.service.MeetingService;
import personal.rathercynicalbadger.BoredGameChap.service.PollService;
import personal.rathercynicalbadger.BoredGameChap.service.UserService;

@Controller
@Secured("ROLE_USER")
@AllArgsConstructor
public class PollController {
    private final PollService pollService;
    private final UserService userService;
    private final MeetingService meetingService;

    @GetMapping("/bgc/team/{teamId}/meeting/{meetingId}/poll")
    public String createPoll(Model model, @PathVariable Long teamId, @PathVariable Long meetingId) {
        model.addAttribute("newPoll", new Poll());
        model.addAttribute("teamId", teamId);
        model.addAttribute("meetingId", meetingId);
        return "/bgc/team/meeting/poll/create-poll";
    }

    @PostMapping("/bgc/team/{teamId}/meeting/{meetingId}/poll")
    public String savePoll(@ModelAttribute Poll newPoll,
                           @PathVariable Long teamId,
                           @PathVariable Long meetingId,
                           @AuthenticationPrincipal CurrentUser currentUser) {
        newPoll.setMeeting(meetingService.findById(meetingId));
        newPoll.setCreator(userService.findById(currentUser.getUser().getId()));
        pollService.save(newPoll);
        return "redirect:/bgc/team/" + teamId;
    }

    @PostMapping("/bgc/poll/{pollId}/vote")
    public String registerVote(@PathVariable Long pollId, @RequestParam String g) {
        Poll poll = pollService.findById(pollId);
        switch (g) {
            case "g1" -> poll.setScore1(poll.getScore1() + 1);
            case "g2" -> poll.setScore1(poll.getScore2() + 1);
            case "g3" -> poll.setScore1(poll.getScore3() + 1);
            case "g4" -> poll.setScore1(poll.getScore4() + 1);
        }

        return "redirect:/bgc/team/" + poll.getMeeting().getTeam().getId();
    }
}
