package rejolut_league.rpl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import rejolut_league.rpl.model.Category;
import rejolut_league.rpl.repo.CategoryRepo;
import rejolut_league.rpl.service.CategoryService;


@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

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
