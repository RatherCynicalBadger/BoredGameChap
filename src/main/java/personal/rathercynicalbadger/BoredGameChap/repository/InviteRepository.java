package personal.rathercynicalbadger.BoredGameChap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import personal.rathercynicalbadger.BoredGameChap.entity.Invite;

import java.util.List;

@Repository
public interface InviteRepository extends JpaRepository<Invite, Long> {

    List<Invite> findAllByInvitedId(Long invitedId);
}
