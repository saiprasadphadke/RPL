package rejolut_league.rpl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import rejolut_league.rpl.model.Category;
import rejolut_league.rpl.repo.CategoryRepo;

@Service
public class CategoryService {

    @Autowired
    CategoryRepo categoryRepo;

    // Create
    public Category createCategory(Category category) {
        return categoryRepo.save(category);
    }

    // Read
    public Category getCategoryById(Integer id) {
        return categoryRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id:" + id));
    }

    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    // Update
    public Category updateCategory(Integer id, Category categoryDetails) {
        Category category = categoryRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));

        category.setName(categoryDetails.getName());
        // update other fields

        return categoryRepo.save(category);
    }

    // Delete
    public void deleteCategory(Integer id) {
        Category category = categoryRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
        categoryRepo.delete(category);
    }

    public List<Category> getBasePrices(String keyword) {
        List<Category> reponse = categoryRepo.getBasePriceByName(keyword);
        return reponse;
    }

}
