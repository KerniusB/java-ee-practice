package lt.vu.persistance;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import lt.vu.entities.Player;
import lt.vu.interfaces.PlayerDAOinterface;

@Alternative
@ApplicationScoped
public class AlternativePlayersDAO implements PlayerDAOinterface {
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
