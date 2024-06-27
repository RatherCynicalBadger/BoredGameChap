package personal.rathercynicalbadger.BoredGameChap.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import personal.rathercynicalbadger.BoredGameChap.entity.Poll;
import personal.rathercynicalbadger.BoredGameChap.repository.MeetingRepository;
import personal.rathercynicalbadger.BoredGameChap.repository.PollRepository;
import personal.rathercynicalbadger.BoredGameChap.repository.UserRepository;

@Controller
@AllArgsConstructor
public class PollController {
    private final PollRepository pollRepo;
    private final UserRepository userRepo;
    private final MeetingRepository meetingRepo;

    private final Long TEMP_USER_ID = 1L;

    @GetMapping("/bgc/team/{teamId}/meeting/{meetingId}/poll")
    public String createPoll(Model model, @PathVariable Long teamId, @PathVariable Long meetingId) {
        model.addAttribute("newPoll", new Poll());
        model.addAttribute("teamId", teamId);
        model.addAttribute("meetingId", meetingId);
        return "/bgc/team/meeting/poll/create-poll";
    }

    @PostMapping("/bgc/team/{teamId}/meeting/{meetingId}/poll")
    public String savePoll(@ModelAttribute Poll newPoll, @PathVariable Long teamId, @PathVariable Long meetingId) {
        newPoll.setMeeting(meetingRepo.getReferenceById(meetingId));
        newPoll.setCreator(userRepo.getReferenceById(TEMP_USER_ID));
        pollRepo.save(newPoll);
        return "redirect:/bgc/team/" + teamId;
    }

    /**
     * For fetching poll details using id
     * @param pollId - id of poll to fetch
     * @return - poll object
     */
    @GetMapping("/bgc/get-poll/{pollId}")
    public Poll pollDetails(@PathVariable Long pollId) {
        return pollRepo.findById(pollId).orElseThrow();
    }

    public void deletePoll(long pollId) {
        pollRepo.deleteById(pollId);
    }

    public void updatePoll(Poll poll) {
        pollRepo.save(poll);
    }
}
