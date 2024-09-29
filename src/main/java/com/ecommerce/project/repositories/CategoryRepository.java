package com.ecommerce.project.repositories;

import com.ecommerce.project.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing Category entities.
 * This interface extends JpaRepository, which provides basic CRUD operations
 * as well as pagination and sorting functionality for the Category entity.
 * It is automatically implemented by Spring Data JPA, allowing interaction
 * with the database without the need to write explicit SQL queries.
 *
 * @see JpaRepository
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
