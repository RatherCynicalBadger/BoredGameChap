package personal.rathercynicalbadger.BoredGameChap.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String username;
    private String about;
    private String password;
    @ManyToMany
    @JoinTable(
            name = "user_owned_games",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "owned_games_id")
    )
    private List<Game> ownedGames;
    @ManyToMany
    private List<Team> teams;
}
