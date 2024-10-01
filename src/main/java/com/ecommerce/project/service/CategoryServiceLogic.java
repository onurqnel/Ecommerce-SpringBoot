package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
/**
 * Service class responsible for managing category operations.
 * This class is marked as a Spring service, enabling it to be
 * injected into other components such as controllers.
 */ public class CategoryServiceLogic implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * Retrieves the list of all categories.
     * <p>
     * This method fetches all categories currently stored in the database.
     * It uses the CategoryRepository to find and return all category entities.
     *
     * @return List of all Category objects stored in the database.
     */
    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();  // Returns the current list of categories.
    }

    /**
     * Creates a new category in the system.
     * <p>
     * This method accepts a Category object and saves it to the database.
     * The category ID will be automatically generated if not provided.
     *
     * @param category The Category object to be created and saved.
     */
    @Override
    public void createCategory(Category category) {
        // Save the new category, ID will be generated automatically
        categoryRepository.save(category);
    }

    /**
     * Deletes a category based on its unique identifier.
     * <p>
     * This method attempts to find a Category by its ID and delete it.
     * If the category is not found, a ResponseStatusException is thrown
     * with a NOT_FOUND (404) status.
     *
     * @param categoryId The unique identifier of the category to be deleted.
     * @return A message confirming successful deletion of the category.
     * @throws ResponseStatusException If no category is found with the given ID.
     */
    @Override
    public String deleteCategory(Long categoryId) throws ResponseStatusException {
        Category existingCategory = categoryRepository.findById(categoryId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category with categoryId: " + categoryId + " not found"));
        categoryRepository.delete(existingCategory);
        return "Category with categoryId: " + categoryId + " deleted successfully";
    }

    /**
     * Updates an existing category by its unique ID.
     * <p>
     * This method finds a category by its ID and updates it with new data.
     * If the category is not found, a ResponseStatusException is thrown.
     *
     * @param category   The updated Category object containing new data.
     * @param categoryId The unique identifier of the category to update.
     * @return The updated Category object after it has been saved.
     * @throws ResponseStatusException If no category is found with the given ID.
     */
    @Override
    public Category updateCategory(Category category, Long categoryId) {
        Category existingCategory = categoryRepository.findById(categoryId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category with categoryId: " + categoryId + " not found"));
        existingCategory.setCategoryName(category.getCategoryName());
        return categoryRepository.save(existingCategory);
    }
}



