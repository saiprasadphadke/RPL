package rejolut_league.rpl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import rejolut_league.rpl.model.Category;
import rejolut_league.rpl.repo.CategoryRepo;
import rejolut_league.rpl.service.CategoryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    // @PostMapping("/category")
    // public Category addCategory(@RequestBody Category category) {
    //     return catRepo.save(category);
    // }

    // @GetMapping("/category")
    // public List<Category> getCategories() {
    //     return (List<Category>) catRepo.findAll();
    // }

    // Create
    @PostMapping("")
    public Category createCategory(@RequestBody Category category) {
        return categoryService.createCategory(category);
    }

    // Read
    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Integer id) {
        return categoryService.getCategoryById(id);
    }

    @GetMapping("")
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    // Update
    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable Integer id, @RequestBody Category category) {
        return categoryService.updateCategory(id, category);
    }

    // Delete
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Integer id) {
        categoryService.deleteCategory(id);
    }


    @GetMapping("/{name}/baseprice")
    public List<Category> getBasePrices(@PathVariable String name) {
        return categoryService.getBasePrices(name);
    }

}
