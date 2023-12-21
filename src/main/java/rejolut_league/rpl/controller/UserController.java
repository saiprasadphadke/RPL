package rejolut_league.rpl.controller;

import org.springframework.web.bind.annotation.RestController;

import rejolut_league.rpl.model.User;
import rejolut_league.rpl.repo.UserRepo;
import rejolut_league.rpl.service.UserService;

// import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
public class UserController {

    
    @Autowired
    UserRepo repo;
    
    @Autowired
    private UserService userService;



    @PostMapping("/user")
    public ResponseEntity<Object> addUser(@RequestBody UserService.AddUserBody addUserBody) {
        try {
            User response = userService.createUser(addUserBody);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error creating user: " + e.getMessage());
        }
        
    }
    

    // @PostMapping("/user")
    // public ResponseEntity<Object> createUser(@RequestBody ResponseEntity<Object> userRequest) {
    //     // Retrieve the Category entity based on the category_id from the request
    //     Object responseBody = userRequest.getBody();
    //     System.out.println(responseBody);
    //     // Category category = catRepo.findById(userRequest.category);

    //     // if (category == null) {
    //     //     return ResponseEntity.badRequest().body("Category not found");
    //     // }

    //     // // Create a User object and set the Category
    //     // User user = new User();
    //     // user.setUserName(userRequest.getUserName());
    //     // user.setEmail(userRequest.getEmail());
    //     // user.setAge(userRequest.getAge());
    //     // user.setUserImageUrl(userRequest.getUserImageUrl());
    //     // user.setCategory(category);

    //     // // Process the user object, save to the database, etc.
    //     // // Example: userService.saveUser(user);

    //     // // Return a response indicating success
    //     return ResponseEntity.ok("User created successfully");
    // }
    
    @GetMapping("/users")
    public Iterable<User> getAllUsers() {
        Iterable<User> response = repo.findAll();
        return response;
    }


    // @GetMapping("/user/{id}")
    // public User getUser(@PathVariable Long id) {
    //     User response = repo.findById(id).get();
    //     return response;

    // }
    
    
}
