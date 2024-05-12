package com.programming.ecommerce.service.admin.admincategory;

import com.programming.ecommerce.models.Category;
import com.programming.ecommerce.payload.request.CategoryRequest;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Category addCategory(CategoryRequest categoryRequest);

    List<Category> getAllCategories();

    Optional<Category> getCategoryById(String id);
}
