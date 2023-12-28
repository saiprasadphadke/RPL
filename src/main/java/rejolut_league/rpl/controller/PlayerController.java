package rejolut_league.rpl.controller;

import rejolut_league.rpl.model.Player;
import rejolut_league.rpl.service.PlayerService;

import java.util.List;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/player")
public class PlayerController {

    

    @Autowired
    private PlayerService playerService;

    @PostMapping("")
    public Player createUser(@RequestBody PlayerService.CreatePlayerRequest player) {
        return playerService.createUser(player);
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

}





