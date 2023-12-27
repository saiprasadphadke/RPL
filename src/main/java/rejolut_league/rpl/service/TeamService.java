package rejolut_league.rpl.service;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import rejolut_league.rpl.model.Team;
import rejolut_league.rpl.repo.TeamRepo;

@Service
public class TeamService {

    public static class TeamRegister {
        public String team_name;
        public String team_symbol;
        public String team_login_id;
        public String team_password;
    }

    public static class LoginBody {
        public String login_id;
        public String password;
    }

    @Autowired
    TeamRepo repo;

    // public Team teamRegister (TeamRegister body) {

    // Team team = new Team();
    // String hashedPassword = BCrypt.hashpw(body.team_password,
    // BCrypt.gensalt(10));

    // team.setTeam_name(body.team_name);
    // team.setWin(0);
    // team.setLoss(0);
    // team.setDraw(0);
    // team.setTeam_total_match(0);
    // team.setTeam_image_url(null);
    // team.setTeam_login_id(body.team_login_id);
    // team.setPassword(hashedPassword);
    // team.setTeam_symbol(body.team_symbol);

    // Team response = repo.save(team);

    // return response;
    // }

    // public Team login(LoginBody body) {
    // Optional<Team> team = repo.getTeamByLoginId(body.login_id);

    // if (team.isEmpty()) {
    // throw new RuntimeException("User not found");
    // }

    // if (!BCrypt.checkpw(body.password, team.get().getPassword())) {
    // throw new RuntimeException("Invalid password");
    // }

    // return team.get();
    // }

    // Create
    public Team createTeam(Team team) {
        return repo.save(team);
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

}
