package lt.vu.view;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lt.vu.entities.Player;
import lt.vu.entities.Team;

@Getter
@Setter
@NoArgsConstructor
public class TeamDto {
    private Integer id;
    private String name;
    private Integer version;

    public TeamDto(Team team) {
        this.id =  team.getId();
        this.name =  team.getName();
    }
}


