package rejolut_league.rpl.controller;

import org.springframework.web.bind.annotation.RestController;

import rejolut_league.rpl.model.User;
import rejolut_league.rpl.repo.UserRepo;
import rejolut_league.rpl.service.UserService;

// import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepo repo;

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<Object> addUser(@RequestBody UserService.Register addUserBody) {
        try {
            User response = userService.createUser(addUserBody);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error creating user: " + e.getMessage());
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUser(@PathVariable Integer id) {
        User response = repo.getUserById(id);

        return ResponseEntity.ok().body(response);

    }

    @GetMapping("/all")
    public Iterable<User> getAllUsers() {
        Iterable<User> response = repo.findAll();
        return response;
    }

}
