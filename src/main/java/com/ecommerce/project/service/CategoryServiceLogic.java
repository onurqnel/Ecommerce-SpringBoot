package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceLogic implements CategoryService {
    private final List<Category> categories = new ArrayList<>();
    private Long nextId = 1L;

    @Override
    public List<Category> getAllCategories() {
        return categories;
    }

    @Override
    public void createCategory(Category category) {
        category.setCategoryId(nextId++);
        categories.add(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        try {
            for (int i = 0; i < categories.size(); i++) {
                Category category = categories.get(i);
                if (category.getCategoryId() == categoryId) {
                    categories.remove(i);
                    return "Category with categoryId: " + categoryId + " deleted successfully";
                }
            }
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category with categoryId: " + categoryId + " not found");
        } catch (ResponseStatusException e) {
            throw e;
        }
    }
}

