package personal.rathercynicalbadger.BoredGameChap.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String about;
    /**
     * Approximate location of the team.
     */
    private String location;
    private Long teamAdminId;
    @ManyToMany
    @JoinTable(
            name = "user_team",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @JsonIgnore
    private List<User> members = new ArrayList<>();
}
