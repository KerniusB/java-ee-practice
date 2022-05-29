package lt.vu.interfaces;

import java.util.List;

import lt.vu.entities.Player;

public interface PlayerDAOinterface {
    public void persist(Player player);

    public List<Player> loadAll();

    public Player findOne(Integer id);

    public Player findOneByName(String name);

    public Player update(Player player);
}
