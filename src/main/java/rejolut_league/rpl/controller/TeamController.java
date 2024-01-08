package rejolut_league.rpl.controller;

import rejolut_league.rpl.model.Team;
import rejolut_league.rpl.repo.TeamRepo;
import rejolut_league.rpl.service.TeamService;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RestController;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/team")
public class TeamController {
    @Autowired
    TeamRepo repo;

    @Autowired
    TeamService teamService;

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

    @GetMapping("/balances")
    public List<Map<String, Object>> getBalances() {
        List<Map<String, Object>> response = teamService.getBalances();
        return response;
    }
    

}



