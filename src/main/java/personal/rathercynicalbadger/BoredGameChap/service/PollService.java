package personal.rathercynicalbadger.BoredGameChap.service;

import personal.rathercynicalbadger.BoredGameChap.entity.Poll;

public interface PollService {
    Poll save(Poll poll);
    Poll findById(Long id);
    void deleteById(Long id);
}
