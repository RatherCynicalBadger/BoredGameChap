package personal.rathercynicalbadger.BoredGameChap.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import personal.rathercynicalbadger.BoredGameChap.repository.MeetingRepository;

@Controller
@AllArgsConstructor
public class MeetingController {
    private final MeetingRepository meetingRepo;
}
