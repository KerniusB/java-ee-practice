package lt.vu.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Team;
import lt.vu.persistance.TeamsDAO;
import lt.vu.view.TeamDto;
import lt.vu.view.TeamListViewDto;

@ApplicationScoped
@Path("/teams")
public class TeamController {

    @Inject @Getter @Setter TeamsDAO teamsDAO;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTeams() {
        List<TeamListViewDto> teams = teamsDAO.loadAll()
                .stream()
                .map(TeamListViewDto::new)
                .collect(Collectors.toList());
        return Response.ok(teams).build();
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Long id) {
        Team team = teamsDAO.findOne(id.intValue());
        if (team == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(new TeamDto(team)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response addTeam(TeamDto teamDto) {
        if (teamDto.getName() == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        Team team = new Team();
        team.setName(teamDto.getName());
        teamsDAO.persist(team);
        return Response.ok().build();
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(@PathParam("id") Long id, TeamDto teamDto) {
        if (teamDto.getName() == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        try {
            Team existingTeam = teamsDAO.findOne(id.intValue());
            if (existingTeam == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            existingTeam.setName(teamDto.getName());
            teamsDAO.updateTeamName(existingTeam);
            return Response.ok().build();
        } catch (Exception exception) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }
}
