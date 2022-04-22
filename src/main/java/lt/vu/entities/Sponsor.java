package lt.vu.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@NamedQueries({
        @NamedQuery(name = "Sponsor.findAll", query = "select s from Sponsor as s ORDER BY s.amount DESC")
})
@Table(name = "SPONSORS")
@Getter
@Setter
@NoArgsConstructor
public class Sponsor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Integer amount;

    @ManyToMany(mappedBy = "sponsors")
    @Column(name="PLAYERS_ID")
    private List<Player> players;
}
