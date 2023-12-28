package rejolut_league.rpl.controller;

import rejolut_league.rpl.model.Team;
import rejolut_league.rpl.repo.TeamRepo;
import rejolut_league.rpl.service.TeamService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/team")
public class TeamController {
    @Autowired
    TeamRepo repo;

    @Autowired
    TeamService teamService;

    // @PostMapping("/")
    // public ResponseEntity<Object> registerTeam(@RequestBody
    // TeamService.TeamRegister entity) {
    // try {
    // System.out.println("Starting Now");
    // Team response = teamService.teamRegister(entity);
    // System.out.println(response);
    // return ResponseEntity.ok().body(response);
    // }
    // catch (Exception e) {
    // return ResponseEntity.badRequest().body("Error creating user: " +
    // e.getMessage());
    // }
    // }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> Login(@RequestBody TeamService.LoginBody entity) {
       
        Map<String, String> team = teamService.Login(entity);

        return ResponseEntity.ok().body(team);
    }

    // Create
    @PostMapping("")
    public Team createTeam(@RequestBody TeamService.TeamRegister team) {
        return teamService.createTeam(team);
    }

    // Read
    @GetMapping("/{id}")
    public Team getTeamById(@PathVariable Integer id) {
        return teamService.getTeamById(id);
    }

    @GetMapping("")
    public List<Team> getAllTeams() {
        return teamService.getAllTeams();
    }

    // Update
    @PutMapping("/{id}")
    public Team updateTeam(@PathVariable Integer id, @RequestBody Team team) {
        return teamService.updateTeam(id, team);
    }

    // Delete
    @DeleteMapping("/{id}")
    public void deleteTeam(@PathVariable Integer id) {
        teamService.deleteTeam(id);
    }

}



