package rejolut_league.rpl.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonProperty;

import rejolut_league.rpl.model.Category;
import rejolut_league.rpl.model.Player;
import rejolut_league.rpl.model.Team;
import rejolut_league.rpl.repo.CategoryRepo;
import rejolut_league.rpl.repo.PlayerRepo;
import rejolut_league.rpl.repo.TeamRepo;

import java.util.List;

@Service
public class PlayerService {

    public static class CreatePlayerRequest {
        @JsonProperty("name")
        private String name;

        @JsonProperty("age")
        private int age;

        @JsonProperty("category")
        private int category;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public int getCategory() {
            return category;
        }

        public void setCategory(int category) {
            this.category = category;
        }
    }

    public static class ChangeTeamRequest {
        public Integer teamId;
    }

    @Autowired
    private PlayerRepo playerRepo;

    @Autowired
    private CategoryRepo categoryRepository;

    @Autowired
    private TeamRepo teamRepo;

    public Player createUser(CreatePlayerRequest player) {
        Player newPlayer = new Player();
        newPlayer.setName(player.getName());
        newPlayer.setAge(player.getAge());

        Category category = (Category) categoryRepository.findById(player.getCategory())
                .orElseThrow(() -> {
                    throw new RuntimeException("Category not found with id: " + player.getCategory());
                });
        newPlayer.setCategory(category);
        Player response = playerRepo.save(newPlayer);
        // Add Player back to the list of players in Category
        category.getPlayers().add(response);
        categoryRepository.save(category);
        return response;
    }

    public Player getUserById(Integer id) {
        return playerRepo.findById(id)
                .orElseThrow(() -> {
                    throw new RuntimeException("Player not found with id:" + id);
                });
    }

    public List<Player> getAllUsers() {
        return playerRepo.findAll();
    }

    public Player updateUser(Integer id, Player userDetails) {
        Player player = playerRepo.findById(id)
                .orElseThrow(() -> {
                    throw new RuntimeException("Player not found with id: " + id);
                });

        // player.setEmail(userDetails.getEmail());
        player.setAge(userDetails.getAge());

        return playerRepo.save(player);
    }

    public void deleteUser(Integer id) {
        Player player = playerRepo.findById(id)
                .orElseThrow(() -> {
                    throw new RuntimeException("Player not found with id: " + id);
                });
        playerRepo.delete(player);
    }

    public Player changeTeam(Integer id, ChangeTeamRequest entity) {
        Player player = playerRepo.findById(id)
                .orElseThrow(() -> {
                    throw new RuntimeException("Player not found with id: " + id);
                });
        Team team = teamRepo.findById(entity.teamId)
                .orElseThrow(() -> {
                    throw new RuntimeException("Team not found with id: " + entity.teamId);
                });
        // Remove player from old team
        Team oldTeam = player.getTeam();
        if (oldTeam != null) {
            oldTeam.getPlayers().remove(player);
            teamRepo.save(oldTeam);
        }
        // Add player to new team
        player.setTeam(team);
        team.getPlayers().add(player);
        teamRepo.save(team);
        return player;

    }

}


