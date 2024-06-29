package personal.rathercynicalbadger.BoredGameChap.service;

import personal.rathercynicalbadger.BoredGameChap.entity.User;

public interface UserService {
    User findById(Long id);

    User findByName(String name);

    Long findIdByName(String name);

    void save(User user);

    void delete(User user);
}
