package personal.rathercynicalbadger.BoredGameChap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import personal.rathercynicalbadger.BoredGameChap.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    @Query("SELECT u.id FROM User u WHERE u.username = :username")
    Optional<Long> findIdByUsername(@Param("username") String username);
}
