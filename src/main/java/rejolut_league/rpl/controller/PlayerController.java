package rejolut_league.rpl.controller;

import rejolut_league.rpl.model.Player;
import rejolut_league.rpl.service.PlayerService;

import java.util.*;

import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @PostMapping("")
    public ResponseEntity<Map<String, String>>  createUser(@RequestBody PlayerService.CreatePlayerRequest player) {
        System.out.println(player);
        Map<String, String> response = playerService.createUser(player);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    public Player getUserById(@PathVariable Integer id) {
        return playerService.getUserById(id);
    }

    @GetMapping("")
    public List<Player> getAllUsers() {
        return playerService.getAllUsers();
    }

    @PutMapping("/{id}")
    public Player updateUser(@PathVariable Integer id, @RequestBody Player player) {
        return playerService.updateUser(id, player);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id) {
        playerService.deleteUser(id);
    }

    @PostMapping("/{id}/team")
    public Player changePlayerTeam(@PathVariable Integer id, @RequestBody PlayerService.ChangeTeamRequest entity) {
        Player response = playerService.changeTeam(id, entity);
        return response;
    }
    


}





