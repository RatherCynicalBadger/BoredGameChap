package personal.rathercynicalbadger.BoredGameChap.service;

import org.springframework.stereotype.Service;
import personal.rathercynicalbadger.BoredGameChap.entity.Team;

@Service
public interface TeamService {
    Team findById(Long id);
    Team findByName(String teamName);
    String findNameById(Long id);
    void save(Team team);
}
