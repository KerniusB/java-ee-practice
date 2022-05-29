package lt.vu.usecases;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import lt.vu.entities.Team;
import lt.vu.persistance.TeamsDAO;

@SessionScoped
@Named
public class PickRandomTeam implements Serializable {

    @Inject
    private RandomTeam randomTeam;
    @Inject
    private TeamsDAO teamsDAO;

    private CompletableFuture<Integer> pickerTask = null;

    public String pickRandomTeam() {
        List<Integer> teamIds = teamsDAO.loadAll().stream().map(Team::getId).collect(Collectors.toList());
        pickerTask = CompletableFuture.supplyAsync(() -> randomTeam.pickRandomTeam(teamIds));
        return "index.xhtml?faces-redirect=true";
    }

    public boolean isRunning() {
        return pickerTask != null && !pickerTask.isDone();
    }

    public String getTeamLink() throws ExecutionException, InterruptedException {
        if (pickerTask == null) {
            return null;
        }
        if (isRunning()) {
            return "Fetching data!";
        }
        Integer picked = pickerTask.get();

        return "<a href='players.xhtml?teamId=" + picked + "'>Go to team:" + picked + "</a>";
    }
}
