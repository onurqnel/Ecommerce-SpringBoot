package com.ecommerce.project.model;

/*
 * The Category class represents a model for the category entity.
 * It contains two fields: categoryId (a unique identifier for each category)
 * and categoryName (the name of the category).
 * This class provides a constructor to initialize these fields, as well as
 * getter and setter methods to access and modify them.
 */
public class Category {

    // The unique identifier for the category
    private long categoryId;

    // The name of the category
    private String categoryName;

    /*
     * Constructor for creating a Category object with a specified id and name.
     *
     * @param categoryId The unique identifier for the category.
     * @param categoryName The name of the category.
     */
    public Category(long categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    /*
     * Getter method for categoryId.
     *
     * @return The unique identifier of the category.
     */
    public long getCategoryId() {
        return categoryId;
    }

    /*
     * Setter method for categoryId.
     * Allows modifying the unique identifier of the category.
     *
     * @param categoryId The new unique identifier for the category.
     */
    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    /*
     * Getter method for categoryName.
     *
     * @return The name of the category.
     */
    public String getCategoryName() {
        return categoryName;
    }

    /*
     * Setter method for categoryName.
     * Allows modifying the name of the category.
     *
     * @param categoryName The new name for the category.
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
