package com.ecommerce.project.controller;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/*
 * @RestController annotation indicates that this class is a controller in a Spring application
 * The return values from its methods will be used to build JSON responses  by default.
 */
@RestController
public class CategoryController {

    // Instance of CategoryService (the service layer that handles business logic related to categories)
    private final CategoryService categoryService;

    // Constructor injection to inject CategoryService instance when CategoryController is created.
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /*
     * Handles HTTP GET requests to the endpoint "/api/public/categories"
     * This method retrieves all categories from the service.
     * The return type is ResponseEntity, which represents the entire HTTP response.
     * A list of Category objects is returned along with the HTTP status OK (200).
     */
    @GetMapping("/api/public/categories")
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();  // Fetches the list of all categories
        return new ResponseEntity<>(categories, HttpStatus.OK);  // Returns the categories and a 200 OK status
    }

    /*
     * Handles HTTP POST requests to the endpoint "/api/public/categories"
     * This method allows the client to send a new Category object in the request body as JSON.
     * It then calls the service layer to create/save the new category.
     * A success message and a 201 CREATED status are returned if the operation is successful.
     */
    @PostMapping("/api/public/categories")
    public ResponseEntity<String> createCategory(@RequestBody Category category) {
        categoryService.createCategory(category);  // Creates and saves the new category
        return new ResponseEntity<>("Category added successfully!", HttpStatus.CREATED);  // Returns a success message with a 201 CREATED status
    }

    /*
     * Handles HTTP DELETE requests to the endpoint "/api/public/categories/{categoryId}"
     * The {categoryId} is a path variable representing the unique ID of the category to be deleted.
     * It tries to delete the category using the service layer and returns the result message.
     * If a ResponseStatusException is thrown (for example, if the category doesn't exist), it catches the exception and returns an error message and corresponding status code.
     */
    @DeleteMapping("/api/public/categories/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId) {
        try {
            String status = categoryService.deleteCategory(categoryId);  // Deletes the category by ID
            return new ResponseEntity<>(status, HttpStatus.OK);  // Returns a success message with a 200 OK status
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());  // Returns an error message and status if an exception occurs
        }
    }

    @PutMapping("/api/public/categories/{categoryId}")
    public ResponseEntity<String> updateCategory(@RequestBody Category category, @PathVariable Long categoryId) {
        try {
            Category savedCategory = categoryService.updateCategory(category, categoryId);
            return new ResponseEntity<>("Category with id: " + savedCategory.getCategoryId() + " updated successfully", HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());

        }
    }
}


