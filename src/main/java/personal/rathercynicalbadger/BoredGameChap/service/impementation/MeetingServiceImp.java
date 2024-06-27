package personal.rathercynicalbadger.BoredGameChap.service.impementation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import personal.rathercynicalbadger.BoredGameChap.entity.Meeting;
import personal.rathercynicalbadger.BoredGameChap.repository.MeetingRepository;
import personal.rathercynicalbadger.BoredGameChap.service.MeetingService;

import java.util.List;

@Service
@AllArgsConstructor
public class MeetingServiceImp implements MeetingService {
    private final MeetingRepository meetingRepo;
    @Override
    public Meeting save(Meeting meeting) {
        return meetingRepo.save(meeting);
    }

    @Override
    public Meeting findById(Long id) {
        return meetingRepo.findById(id).orElseThrow();
    }

    @Override
    public List<Meeting> findAllByTeamId(Long teamId) {
        return meetingRepo.findAllByTeam(teamId);
    }
}
