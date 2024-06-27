package personal.rathercynicalbadger.BoredGameChap.service;

import org.springframework.stereotype.Service;
import personal.rathercynicalbadger.BoredGameChap.entity.Meeting;

import java.util.List;

@Service
public interface MeetingService {
    Meeting save(Meeting meeting);
    Meeting findById(Long id);
    List<Meeting> findAllByTeamId(Long teamId);
}
