package personal.rathercynicalbadger.BoredGameChap.service.impementation;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import personal.rathercynicalbadger.BoredGameChap.entity.User;
import personal.rathercynicalbadger.BoredGameChap.repository.RoleRepository;
import personal.rathercynicalbadger.BoredGameChap.repository.UserRepository;
import personal.rathercynicalbadger.BoredGameChap.service.UserService;

@Service
@AllArgsConstructor
public class UserServiceImp implements UserService {
    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final BCryptPasswordEncoder pswEncoder;

    @Override
    public User findById(Long userId) {
        return userRepo.findById(userId).orElseThrow();
    }

    @Override
    public User findByName(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public Long findIdByName(String username) {
        return userRepo.findIdByUsername(username).orElseThrow();
    }

    @Override
    public void save(User user) {
        user.setPassword(pswEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        user.getRoles().add(
                roleRepo.findByName("ROLE_USER")
        );
        userRepo.save(user);
    }
}
