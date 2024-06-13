package personal.rathercynicalbadger.BoredGameChap.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Poll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Meeting meeting;
    @OneToOne
    private User creator;
    @ManyToMany
    private List<Game> suggestedGames = new ArrayList<>();
}
