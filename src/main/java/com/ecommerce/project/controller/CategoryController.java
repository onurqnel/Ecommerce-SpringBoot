package com.ecommerce.project.controller;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/*
 * @RestController annotation indicates that this class is a controller in a Spring application.
 * It marks the class as a web controller, meaning its methods will handle HTTP requests.
 * The return values from its methods will be automatically serialized into JSON responses by default.
 */
@RestController
public class CategoryController {

    // Instance of CategoryService (the service layer that handles business logic related to categories)
    private final CategoryService categoryService;

    /*
     * Constructor injection to inject CategoryService instance when CategoryController is created.
     * This is a recommended way of dependency injection in Spring, ensuring that the CategoryService
     * is provided by Spring at runtime and used for handling business logic in this controller.
     */
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /*
     * Handles HTTP GET requests to the endpoint "/api/public/categories".
     * This method retrieves all categories from the service layer and returns them to the client.
     * The return type is ResponseEntity, which represents the entire HTTP response.
     * A list of Category objects is returned along with the HTTP status OK (200).
     *
     * @return ResponseEntity containing the list of categories and an HTTP status of 200 OK.
     */
    @GetMapping("/api/public/categories")
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();  // Fetches the list of all categories
        return new ResponseEntity<>(categories, HttpStatus.OK);  // Returns the categories and a 200 OK status
    }

    /*
     * Handles HTTP POST requests to the endpoint "/api/public/categories".
     * This method allows the client to send a new Category object in the request body as JSON.
     * The @RequestBody annotation is used to map the incoming JSON to the Category object.
     * It then calls the service layer to create/save the new category in the data store.
     *
     * @param category The Category object received in the request body that needs to be created.
     * @return ResponseEntity containing a success message and HTTP status 201 CREATED if the operation is successful.
     */
    @PostMapping("/api/public/categories")
    public ResponseEntity<String> createCategory(@RequestBody Category category) {
        categoryService.createCategory(category);  // Creates and saves the new category
        return new ResponseEntity<>("Category added successfully!", HttpStatus.CREATED);  // Returns a success message with a 201 CREATED status
    }

    /*
     * Handles HTTP DELETE requests to the endpoint "/api/public/categories/{categoryId}".
     * The {categoryId} is a path variable representing the unique ID of the category to be deleted.
     * It tries to delete the category using the service layer and returns a result message.
     * If a ResponseStatusException is thrown (for example, if the category doesn't exist),
     * it catches the exception and returns an error message with the corresponding HTTP status code.
     *
     * @param categoryId The ID of the category to be deleted, received as a path variable.
     * @return ResponseEntity containing a success or error message, with the appropriate HTTP status code.
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

    /*
     * Handles HTTP PUT requests to the endpoint "/api/public/categories/{categoryId}".
     * The {categoryId} is a path variable representing the unique ID of the category to be updated.
     * The method accepts a JSON request body (mapped to a Category object) that contains the updated category data.
     * It uses the service layer to update the category with the given ID and returns a success message with HTTP status 200 (OK).
     * If a ResponseStatusException is thrown (for example, if the category with the given ID doesn't exist),
     * the exception is caught and an error message with the corresponding HTTP status code is returned.
     *
     * @param category   The Category object received in the request body, containing the updated category data.
     * @param categoryId The ID of the category to be updated, received as a path variable.
     * @return ResponseEntity containing a success or error message, with the appropriate HTTP status code.
     */
    @PutMapping("/api/public/categories/{categoryId}")
    public ResponseEntity<String> updateCategory(@RequestBody Category category, @PathVariable Long categoryId) {
        try {
            // Calls the service to update the category and store the updated category object.
            Category savedCategory = categoryService.updateCategory(category, categoryId);

            // Returns a success message with HTTP status 200 (OK).
            return new ResponseEntity<>("Category with id: " + savedCategory.getCategoryId() + " updated successfully", HttpStatus.OK);
        } catch (ResponseStatusException e) {
            // If the category is not found or another error occurs, return the error message and status.
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }
}


