package personal.rathercynicalbadger.BoredGameChap.service.impementation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import personal.rathercynicalbadger.BoredGameChap.entity.Poll;
import personal.rathercynicalbadger.BoredGameChap.repository.PollRepository;
import personal.rathercynicalbadger.BoredGameChap.service.PollService;

@Service
@AllArgsConstructor
public class PollServiceImp implements PollService {
    private final PollRepository pollRepo;
    @Override
    public Poll save(Poll poll) {
        return pollRepo.save(poll);
    }

    @Override
    public Poll findById(Long id) {
        return pollRepo.findById(id).orElseThrow();
    }

    @Override
    public void deleteById(Long id) {
        pollRepo.deleteById(id);
    }
}
