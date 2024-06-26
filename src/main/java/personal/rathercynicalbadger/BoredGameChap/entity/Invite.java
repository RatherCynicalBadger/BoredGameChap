package personal.rathercynicalbadger.BoredGameChap.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Invite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long teamId;
    private String teamName;
    private Long invitedId;
    private LocalDateTime sent;

    @PrePersist
    public void prePersist() {
        sent = LocalDateTime.now();
    }
}
