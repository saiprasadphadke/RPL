package rejolut_league.rpl.controller;

import org.springframework.web.bind.annotation.RestController;

import rejolut_league.rpl.model.Player;
import rejolut_league.rpl.repo.PlayerRepo;
import rejolut_league.rpl.service.PlayerService;

import java.util.List;
// import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

// @RestController
// @CrossOrigin
// @RequestMapping("/player")
// public class PlayerController {

//     @Autowired
//     PlayerRepo repo;

//     @Autowired
//     private UserService userService;

//     @PostMapping("/")
//     public ResponseEntity<Object> addUser(@RequestBody UserService.Register addUserBody) {
//         try {
//             Player response = userService.createPlayer(addUserBody);
//             return ResponseEntity.ok().body(response);
//         } catch (Exception e) {
//             return ResponseEntity.badRequest().body("Error creating player: " + e.getMessage());
//         }

//     }

//     @GetMapping("/{id}")
//     public ResponseEntity<Object> getUser(@PathVariable Integer id) {
//         Player response = repo.getUserById(id);

//         return ResponseEntity.ok().body(response);

//     }

//     @GetMapping("/all")
//     public Iterable<Player> getAllUsers() {
//         Iterable<Player> response = repo.findAll();
//         return response;
//     }

// }

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





