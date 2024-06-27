package personal.rathercynicalbadger.BoredGameChap.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
public class CurrentUser extends org.springframework.security.core.userdetails.User {
    private final personal.rathercynicalbadger.BoredGameChap.entity.User user;

    public CurrentUser(String username, String password, Collection<? extends GrantedAuthority> authorities,
                       personal.rathercynicalbadger.BoredGameChap.entity.User user) {
        super(username, password, authorities);
        this.user = user;
    }
}
