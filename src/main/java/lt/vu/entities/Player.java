package lt.vu.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@NamedQueries({
        @NamedQuery(name = "Player.findAll", query = "select a from Player as a"),
        @NamedQuery(name = "Player.findByName", query = "select a from Player as a where a.name=:name")

})
@Table(name = "PLAYER")
@Getter
@Setter
@NoArgsConstructor
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50)
    @Column(name = "NAME")
    private String name;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @ManyToMany
    @JoinTable(name="SPONSORS_PALYERS", joinColumns = @JoinColumn(name = "PLAYER_ID"),
            inverseJoinColumns = @JoinColumn(name = "SPONSOR_ID"))
    @Column(name="SPONSOR_ID")
    private List<Sponsor> sponsors;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(id, player.id) &&
                Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
