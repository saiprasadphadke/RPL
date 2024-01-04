package rejolut_league.rpl.service;

import java.util.*;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import rejolut_league.rpl.Constants;
import rejolut_league.rpl.model.Team;
import rejolut_league.rpl.repo.TeamRepo;

@Service
public class TeamService {

    public static class TeamRegister {
        public String name;
        public String loginId;
        public String password;
    }

    public static class LoginBody {
        public String loginId;
        public String password;
    }

    @Autowired
    TeamRepo repo;
    
    // Create
    public Team createTeam(TeamRegister body) {
        Team team = new Team();
        String hashedPassword = BCrypt.hashpw(body.password, BCrypt.gensalt(10));

        team.setName(body.name);
        team.setMatchesWon(0);
        team.setMatchesLost(0);
        team.setMatchesDrawn(0);
        team.setTotalMatches(0);
        team.setTeamLoginId(body.loginId);
        team.setPassword(hashedPassword);
        team.setBalance(250000000.0);

        return repo.save(team);
    }

    // Login
    public Map<String, String> Login(LoginBody body) {
        Team team = repo.getTeamByLoginId(body.loginId);

        // Chec if the team is present
        if (team == null) {
            throw new RuntimeException("Team not found");
        }

        if (!BCrypt.checkpw(body.password, team.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        Map<String, String> response = generateJWT(team);

        return response;
    }

    // Read
    public Team getTeamById(Integer id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Team not found with id:" + id));
    }

    public List<Team> getAllTeams() {
        return repo.findAll();
    }

    // Update
    public Team updateTeam(Integer id, Team teamDetails) {
        Team team = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Team not found with id: " + id));

        team.setName(teamDetails.getName());
        // update other fields

        return repo.save(team);
    }

    // Delete
    public void deleteTeam(Integer id) {
        Team team = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Team not found with id: " + id));
        repo.delete(team);
    }

    private Map<String, String> generateJWT(Team team) {

        System.out.println("Generating JWT");
        
        long timestamp = System.currentTimeMillis();

        String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, Constants.API_SECRET_KEY)
        .setIssuedAt(new Date(timestamp))
        .setExpiration(new Date (timestamp + Constants.TOKEN_VALIDITY_SECONDS))
        .claim("id", team.getId())
        .claim("name", team.getName())
        .claim("loginId", team.getTeamLoginId())
        .compact();

        Map<String, String> response = new HashMap<>();
        response.put("token", token);

        return response;

    }   

}


