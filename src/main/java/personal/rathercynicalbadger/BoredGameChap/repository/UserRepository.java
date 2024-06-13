package personal.rathercynicalbadger.BoredGameChap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import personal.rathercynicalbadger.BoredGameChap.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
