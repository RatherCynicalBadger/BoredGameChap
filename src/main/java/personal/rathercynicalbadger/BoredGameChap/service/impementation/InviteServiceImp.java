package personal.rathercynicalbadger.BoredGameChap.service.impementation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import personal.rathercynicalbadger.BoredGameChap.entity.Invite;
import personal.rathercynicalbadger.BoredGameChap.repository.InviteRepository;
import personal.rathercynicalbadger.BoredGameChap.service.InviteService;

import java.util.List;

@Service
@AllArgsConstructor
public class InviteServiceImp implements InviteService {
    private final InviteRepository inviteRepo;
    @Override
    public void save(Invite invite) {
        inviteRepo.save(invite);
    }

    @Override
    public Invite findById(Long id) {
        return inviteRepo.findById(id).orElseThrow();
    }

    @Override
    public void deleteById(Long id) {
        inviteRepo.deleteById(id);
    }

    @Override
    public List<Invite> findAllByInvitedId(Long id) {
        return inviteRepo.findAllByInvitedId(id);
    }
}
