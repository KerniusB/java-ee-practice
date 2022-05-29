package lt.vu.persistance;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Specializes;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import lt.vu.entities.Team;
import lt.vu.interceptor.LoggedInvocation;

@Specializes
@Model
public class AlternativeTeamsDAO extends TeamsDAO{

    @Inject
    private EntityManager em;

    @LoggedInvocation
    @Override
    public Team updateTeamName(Team team){
        return em.merge(team);
    }
}
