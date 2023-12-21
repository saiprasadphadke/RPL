package rejolut_league.rpl.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rejolut_league.rpl.controller.CategoryController;
import rejolut_league.rpl.model.Category;
import rejolut_league.rpl.model.User;
import rejolut_league.rpl.repo.CategoryRepo;
import rejolut_league.rpl.repo.UserRepo;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    public static class AddUserBody {
        public String user_name;
        public String email;
        public int age;
        public String user_image_url;
        public Integer category;
    }

    
    public User createUser(AddUserBody addUserBody) {

        User user = new User();
        user.setUser_name(addUserBody.user_name);
        user.setEmail(addUserBody.email);
        user.setAge(addUserBody.age);
        user.setUser_image_url(addUserBody.user_image_url);

        Object categoryData = categoryRepo.getCategoryById(addUserBody.category);
        System.out.println(categoryData);
        user.setCategory((Category) categoryData);

        System.out.println(user);
        return userRepo.save(user);
    }

    
}
