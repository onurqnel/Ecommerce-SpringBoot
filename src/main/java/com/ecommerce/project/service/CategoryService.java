package com.ecommerce.project.service;

import com.ecommerce.project.model.Category; // Importing the Category model class

import java.util.List; // Importing List from Java's Collection framework

/*
 * This is a service interface that defines the methods related to
 * the business logic of category management in the e-commerce project.
 * The service layer is responsible for processing data and
 * implementing the core business rules of the application.
 */
public interface CategoryService {

    /*
     * Retrieves a list of all categories.
     * This method returns a List of Category objects representing
     * all available categories.
     */
    List<Category> getAllCategories();

    /*
     * Creates a new category.
     * The method takes a Category object as a parameter and performs
     * the logic to save it (probably to a database).
     */
    void createCategory(Category category);

    /*
     * Deletes a category by its ID.
     * The method takes a Long type categoryId as a parameter and deletes
     * the corresponding category.
     * It returns a String status message indicating success or failure.
     */
    String deleteCategory(Long categoryId);
}
