package lt.vu.persistance;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import lt.vu.entities.Player;

@ApplicationScoped
public class PlayersDAO {

    @Inject
    private EntityManager em;

    public void persist(Player player) {
        this.em.persist(player);
    }

    public List<Player> loadAll() {
        return em.createNamedQuery("Player.findAll", Player.class).getResultList();
    }

    public Player findOne(Integer id) {
        return em.find(Player.class, id);
    }

    public Player findOneByName(String name) {
        return em.createNamedQuery("Player.findByName", Player.class).setParameter("name", name).getSingleResult();
    }

    public Player update(Player player) {
        return em.merge(player);
    }
}
