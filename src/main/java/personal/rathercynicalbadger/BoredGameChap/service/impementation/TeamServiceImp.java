package personal.rathercynicalbadger.BoredGameChap.service.impementation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import personal.rathercynicalbadger.BoredGameChap.entity.Team;
import personal.rathercynicalbadger.BoredGameChap.repository.TeamRepository;
import personal.rathercynicalbadger.BoredGameChap.service.TeamService;

@Service
@AllArgsConstructor
public class TeamServiceImp implements TeamService {
    private TeamRepository teamRepo;

    @Override
    public Team findById(Long teamId) {
        return teamRepo.findById(teamId).orElseThrow();
    }

    @Override
    public Team findByName(String teamName) {
        return teamRepo.findByName(teamName);
    }

    @Override
    public String findNameById(Long id) {
        return teamRepo.findNameById(id);
    }

    @Override
    public void save(Team team) {
        teamRepo.save(team);
    }
}
