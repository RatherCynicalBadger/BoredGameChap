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

    /**
     * For fetching poll details using id
     * @param pollId - id of poll to fetch
     * @return - poll object
     */
    @GetMapping("/bgc/get-poll/{pollId}")
    public Poll pollDetails(@PathVariable Long pollId) {
        return pollService.findById(pollId);
    }

    public void deletePoll(long pollId) {
        pollService.deleteById(pollId);
    }

    public void updatePoll(Poll poll) {
        pollService.save(poll);
    }
}
