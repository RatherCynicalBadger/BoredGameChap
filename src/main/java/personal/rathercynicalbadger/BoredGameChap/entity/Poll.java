package personal.rathercynicalbadger.BoredGameChap.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String name1;
    private String name2;
    private String name3;
    private String name4;
    private int score1 = 0;
    private int score2 = 0;
    private int score3 = 0;
    private int score4 = 0;
}
