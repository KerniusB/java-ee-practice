package lt.vu.usecases;

import java.util.List;
import java.util.Random;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RandomTeam {
    public int pickRandomTeam(List<Integer> teamIds) {
        Random random = new Random();
        try {
            Thread.sleep(4000L);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return random.nextInt(teamIds.size());
    }
}

