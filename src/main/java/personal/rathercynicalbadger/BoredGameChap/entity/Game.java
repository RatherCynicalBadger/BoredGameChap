package personal.rathercynicalbadger.BoredGameChap.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @Column
    private String title;
    @Column(length = 2000)
    private String description;
    @Min(1)
    private byte minPlayers;
    @Min(1)
    private byte maxPlayers;
    private Year released;
    /**
     * Average play time in minutes.
     */
    private int playtime;
    @ManyToMany(mappedBy = "ownedGames", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<User> owners;
}
