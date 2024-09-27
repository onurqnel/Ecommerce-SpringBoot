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

    /*
     * Retrieves the list of all categories.
     *
     * @return The current list of categories stored in memory.
     *         This simulates fetching all categories from a database.
     */
    @Override
    public List<Category> getAllCategories() {
        return categories;  // Returns the current list of categories.
    }

    /*
     * Creates a new category by assigning a unique ID and adding it to the list.
     *
     * @param category The Category object to be created.
     *                 The method assigns a unique ID and adds the category to the list.
     */
    @Override
    public void createCategory(Category category) {
        category.setCategoryId(nextId++);  // Assigns a unique ID to the new category and increments the ID counter.
        categories.add(category);  // Adds the category to the in-memory list.
    }

    /*
     * Deletes a category by its ID.
     *
     * @param categoryId The unique identifier of the category to be deleted.
     *
     * @return A success message indicating that the category has been deleted.
     *
     * @throws ResponseStatusException If the category with the given ID is not found, an exception is thrown
     *                                 with a 404 (Not Found) status.
     */
    @Override
    public String deleteCategory(Long categoryId) throws ResponseStatusException {
        // Loops through the list of categories to find the one with the matching ID.
        for (int i = 0; i < categories.size(); i++) {
            Category category = categories.get(i);
            // If the category ID matches, the category is removed from the list.
            if (category.getCategoryId() == categoryId) {
                categories.remove(i);  // Removes the category from the list.
                return "Category with categoryId: " + categoryId + " deleted successfully";
            }
        }
        // If no category is found, a ResponseStatusException is thrown with a 404 Not Found status.
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category with categoryId: " + categoryId + " not found");
    }

    /*
     * Updates an existing category by its ID.
     *
     * @param category   The new category data to update.
     *                   This will replace the existing category data.
     *
     * @param categoryId The unique identifier of the category to be updated.
     *
     * @return The updated Category object.
     *
     * @throws ResponseStatusException If the category with the given ID is not found, a 404 (Not Found) status is returned.
     */
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


