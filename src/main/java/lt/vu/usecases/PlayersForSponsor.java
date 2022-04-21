package lt.vu.usecases;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Player;
import lt.vu.entities.Sponsor;
import lt.vu.persistance.PlayersDAO;
import lt.vu.persistance.SponsorsDAO;

@Model
public class PlayersForSponsor implements Serializable {
    @Inject
    private PlayersDAO playersDAO;
    @Inject
    private SponsorsDAO sponsorsDAO;

    @Getter @Setter
    private Player playerToAdd = new Player();

    @Getter @Setter
    private Sponsor sponsor;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer sponsorId = Integer.parseInt(requestParameters.get("sponsorId"));
        this.sponsor = sponsorsDAO.findOne(sponsorId);
    }

    @Transactional
    public void addSponsor(String playerName) {

        Player player = playersDAO.findOneByName(playerName);

        sponsor.getPlayers().add(player);
        player.getSponsors().add(sponsor);
    }

}
