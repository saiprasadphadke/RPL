package rejolut_league.rpl.controller;

import rejolut_league.rpl.model.Team;
import rejolut_league.rpl.repo.TeamRepo;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class TeamController {
    @Autowired
    TeamRepo repo;
    

    @PostMapping("/team")
    public Team addTeam(@RequestBody Team entity) {

        Team response = repo.save(entity);
        return response;
    }

    @GetMapping("/teams")
    public List<Team> getAllTeams() {

        List<Team> teams = (List<Team>) repo.findAll();

        return teams;

    }

    // @GetMapping("/team/{id}")
    // public Optional<Team> getTeam(@PathVariable long id) {

    //     Optional<Team> response = repo.findById(id);

    //     return response;

    // }

}
