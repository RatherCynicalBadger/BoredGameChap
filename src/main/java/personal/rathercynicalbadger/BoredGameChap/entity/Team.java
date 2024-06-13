package personal.rathercynicalbadger.BoredGameChap.entity;

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
     * Approximate location of the group.
     */
    private String location;
    /**
     * True if anybody can join, false if private.
     */
    private boolean isOpen;
    @ManyToMany
    private List<User> members = new ArrayList<>();
    @OneToOne
    private User groupAdmin;
}
