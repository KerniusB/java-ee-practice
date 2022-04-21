package lt.vu.usecases;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Sponsor;
import lt.vu.persistance.SponsorsDAO;

@Model
public class Sponsors {
    @Inject
    private SponsorsDAO sponsorsDAO;
    @Getter
    private List<Sponsor> allSponsors;
    @Getter @Setter
    private Sponsor sponsorToCreate = new Sponsor();

    @PostConstruct
    public void init() {
        loadAllSponsors();
    }

    @Transactional
    public void createSponsor() {
        this.sponsorsDAO.persist(sponsorToCreate);
    }

    private void loadAllSponsors() {
        allSponsors = sponsorsDAO.loadAll();
    }

}
