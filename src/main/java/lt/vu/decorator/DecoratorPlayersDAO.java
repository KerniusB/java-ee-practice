package lt.vu.decorator;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import lt.vu.entities.Player;
import lt.vu.interfaces.PlayerDAOinterface;

@Decorator
public abstract class DecoratorPlayersDAO implements PlayerDAOinterface {

    @Inject
    @Delegate
    @Any
    PlayerDAOinterface playerDAOinterface;

    @Inject
    private EntityManager em;

    public Player findOneByName(String name) {
        System.out.println("Decorator method called");
        return em.createNamedQuery("Player.findByName", Player.class).setParameter("name", name).getSingleResult();
    }
}
