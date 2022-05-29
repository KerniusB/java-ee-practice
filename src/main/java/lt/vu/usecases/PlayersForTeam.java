package lt.vu.usecases;

import java.io.Serializable;
import java.util.Map;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Player;
import lt.vu.entities.Team;
import lt.vu.persistance.PlayersDAO;
import lt.vu.persistance.TeamsDAO;

@ViewScoped
@Setter
@Getter
@Named
public class PlayersForTeam implements Serializable {

    @Inject
    private TeamsDAO teamsDAO;

    @Inject
    private PlayersDAO playersDAO;

    @Getter @Setter
    private Team team;

    @Getter @Setter
    private Player playerToCreate = new Player();

    @Getter @Setter
    private String teamName;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer teamId = Integer.parseInt(requestParameters.get("teamId"));
        this.team = teamsDAO.findOne(teamId);
    }

    @Transactional
    public void createPlayer() {
        playerToCreate.setTeam(this.team);
        playersDAO.persist(playerToCreate);
    }

    @Transactional
    public String updateTeamName() {
        team.setName(teamName);
        try {
            teamsDAO.updateTeamName(team);
        } catch (OptimisticLockException e) {
            return "players.xhtml?teamId=" + this.team.getId() + "&error=optimistic-lock-exception&faces-redirect=true";
        }
        return "players.xhtml?teamId=" + this.team.getId() + "&faces-redirect=true";
    }
}
