package personal.rathercynicalbadger.BoredGameChap.service;

import personal.rathercynicalbadger.BoredGameChap.entity.Invite;

import java.util.List;

public interface InviteService {
    void save(Invite invite);
    Invite findById(Long id);
    void deleteById(Long id);
    List<Invite> findAllByInvitedId(Long id);
}
