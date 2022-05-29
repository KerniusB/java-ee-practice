package lt.vu.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Team;

@Getter
@Setter
@AllArgsConstructor
public class TeamListViewDto {
    private String name;

    public TeamListViewDto(Team team) {
        this.name =  team.getName();
    }
}
