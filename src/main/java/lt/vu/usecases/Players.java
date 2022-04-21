package lt.vu.usecases;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import lombok.Getter;
import lt.vu.entities.Player;
import lt.vu.persistance.PlayersDAO;

@Model
public class Players {

    @Inject
    private PlayersDAO playersDAO;

    @Getter
    private List<Player> allPlayers;

    @PostConstruct
    public void init(){
        loadAllPlayers();
    }

    private void loadAllPlayers(){
        allPlayers = playersDAO.loadAll();
    }
}
