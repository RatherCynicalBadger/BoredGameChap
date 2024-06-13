package personal.rathercynicalbadger.BoredGameChap.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String title;
    private String description;
    @Min(1)
    private short minPlayers;
    private short maxPlayers;
    private LocalDate released;
    /**
     * Average play time in minutes.
     */
    private int playtime;
    @OneToMany
    private List<Expansion> expansions = new ArrayList<>();
}
