package rejolut_league.rpl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import rejolut_league.rpl.model.Category;
import rejolut_league.rpl.repo.CategoryRepo;

@RestController
@CrossOrigin
public class CategoryController {

    @Autowired
	CategoryRepo catRepo;

    @PostMapping("/category")
    public Category addCategory(@RequestBody Category category) {
        return catRepo.save(category);
    }

    @GetMapping("/category")
    public List<Category> getCategories() {
        return (List<Category>) catRepo.findAll();
    }

}
