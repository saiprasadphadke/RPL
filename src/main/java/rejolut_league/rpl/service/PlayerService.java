package rejolut_league.rpl.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonProperty;

import rejolut_league.rpl.model.Category;
import rejolut_league.rpl.model.Player;
import rejolut_league.rpl.repo.CategoryRepo;
import rejolut_league.rpl.repo.PlayerRepo;

import java.util.List;

// public class PlayerService {

//     @Autowired
//     private PlayerRepo userRepo;

//     @Autowired
//     private CategoryRepo categoryRepo;

//     public static class Register {
//         public String user_name;
//         public String email;
//         public int age;
//         public String user_image_url;
//         public Integer category;
//     }

//     public Player createUser(Register addUserBody) {

//         Player user = new Player();
//         user.setName(addUserBody.user_name);
//         user.setEmail(addUserBody.email);
//         user.setAge(addUserBody.age);
//         user.setUser_image_url(addUserBody.user_image_url);

//         Object categoryData = categoryRepo.getCategoryById(addUserBody.category);

//         user.setCategory((Category) categoryData);

//         return userRepo.save(user);
//     }

// }

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

    @Autowired
    private PlayerRepo userRepository;

    @Autowired
    private CategoryRepo categoryRepository;

    public Player createUser(CreatePlayerRequest player) {
        Player newPlayer = new Player();
        newPlayer.setName(player.getName());
        newPlayer.setAge(player.getAge());

        Category category = (Category) categoryRepository.findById(player.getCategory())
                .orElseThrow(() -> {
                    throw new ResourceNotFoundException("Category not found with id: " + player.getCategory());
                });
        newPlayer.setCategory(category);
        Player response = userRepository.save(newPlayer);
        // Add Player back to the list of players in Category
        // category.getPlayers().add(response);
        // categoryRepository.save(category);
        return response;
    }

    public Player getUserById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> {
                    throw new ResourceNotFoundException("Player not found with id:" + id);
                });
    }

    public List<Player> getAllUsers() {
        return userRepository.findAll();
    }

    public Player updateUser(Integer id, Player userDetails) {
        Player player = userRepository.findById(id)
                .orElseThrow(() -> {
                    throw new ResourceNotFoundException("Player not found with id: " + id);
                });

        // player.setEmail(userDetails.getEmail());
        player.setAge(userDetails.getAge());

        return userRepository.save(player);
    }

    public void deleteUser(Integer id) {
        Player player = userRepository.findById(id)
                .orElseThrow(() -> {
                    throw new ResourceNotFoundException("Player not found with id: " + id);
                });
        userRepository.delete(player);
    }

}

