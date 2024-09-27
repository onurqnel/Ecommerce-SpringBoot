package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
// Marks this class as a Spring service, which means it can be injected into other components like controllers.
public class CategoryServiceLogic implements CategoryService {

    // List to store categories, simulates a database.
    private final List<Category> categories = new ArrayList<>();

    // Variable for auto-incrementing IDs for categories.
    private Long nextId = 1L;

    // This method retrieves the list of all categories.
    @Override
    public List<Category> getAllCategories() {
        return categories;  // Returns the current list of categories.
    }

    // This method adds a new category to the list.
    @Override
    public void createCategory(Category category) {
        category.setCategoryId(nextId++);  // Assigns a unique ID to the new category and increments the ID counter.
        categories.add(category);  // Adds the category to the in-memory list.
    }

    // This method deletes a category by its ID and throws an exception if the category is not found.
    @Override
    public String deleteCategory(Long categoryId) throws ResponseStatusException {
        // Loops through the list of categories to find the one with the matching ID.
        for (int i = 0; i < categories.size(); i++) {
            Category category = categories.get(i);
            // If the category ID matches, the category is removed from the list.
            if (category.getCategoryId() == categoryId) {
                categories.remove(i);
                return "Category with categoryId: " + categoryId + " deleted successfully";
            }
        }
        // If no category is found, a ResponseStatusException is thrown with a 404 Not Found status.
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category with categoryId: " + categoryId + " not found");
    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {
        // Initialize a variable to hold the found category
        Category existingCategory = null;

        // Iterate through the list of categories
        for (Category c : categories) {
            // Check if the category ID matches the given ID
            if (c.getCategoryId() == categoryId) {
                existingCategory = c; // Found the category, assign it to existingCategory
                break; // Exit the loop as we found the category
            }
        }
        // Check if a category was found
        if (existingCategory != null) {
            // Update the category name of the found category
            existingCategory.setCategoryName(category.getCategoryName());
            // Return the updated category
            return existingCategory;
        } else {
            // If no category was found, throw a 404 NOT FOUND exception
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found");
        }
    }
}

