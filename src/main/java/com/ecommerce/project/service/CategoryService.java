package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;

import java.util.List;

/*
 * This is a service interface that defines the methods related to
 * the business logic of category management in the e-commerce project.
 * The service layer is responsible for processing data and
 * implementing the core business rules of the application.
 */
public interface CategoryService {

    /*
     * Retrieves a list of all categories.
     *
     * @return A List of Category objects representing all available categories.
     *         This method fetches all categories from the data source (e.g., database)
     *         and returns them as a list.
     */
    List<Category> getAllCategories();

    /*
     * Creates a new category.
     *
     * @param category The Category object containing data for the new category.
     *                 This object is passed to the method and will be processed
     *                 and saved (likely to a database) as part of the business logic.
     */
    void createCategory(Category category);

    /*
     * Deletes a category by its ID.
     *
     * @param categoryId The unique identifier of the category to be deleted.
     *                   This is a Long type, which allows handling null values
     *                   and works well with databases.
     *
     * @return A String message indicating the success or failure of the deletion process.
     *         If the category is deleted successfully, it returns a success message.
     *         If the category cannot be found or deleted, an appropriate failure message is returned.
     */
    String deleteCategory(Long categoryId);

    /*
     * Updates an existing category by its ID.
     *
     * @param category   The Category object containing the updated data.
     *                   This object will replace the existing category data.
     *
     * @param categoryId The unique identifier of the category to be updated.
     *                   This is used to locate the category in the data source.
     *
     * @return The updated Category object after the update operation is performed.
     *         If the category is not found, an exception may be thrown.
     */
    Category updateCategory(Category category, Long categoryId);
}
