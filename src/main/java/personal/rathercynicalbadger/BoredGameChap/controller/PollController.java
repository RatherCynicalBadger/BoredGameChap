package personal.rathercynicalbadger.BoredGameChap.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import personal.rathercynicalbadger.BoredGameChap.entity.Poll;
import personal.rathercynicalbadger.BoredGameChap.repository.PollRepository;

@Controller
@AllArgsConstructor
public class PollController {
    private final PollRepository pollRepo;

    @GetMapping("/create")
    public String createPoll(Model model) {
        model.addAttribute("newPoll", new Poll());
        return "group/meeting/poll/create-poll";
    }

    @PostMapping("/create")
    public void savePoll(@ModelAttribute Poll newPoll) {
        pollRepo.save(newPoll);
    }

    public void deletePoll(long pollId) {
        pollRepo.deleteById(pollId);
    }

    public void updatePoll(Poll poll) {
        pollRepo.save(poll);
    }
}
