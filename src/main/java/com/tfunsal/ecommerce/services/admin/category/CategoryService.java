package com.tfunsal.ecommerce.services.admin.category;

import com.tfunsal.ecommerce.dto.CategoryDto;
import com.tfunsal.ecommerce.entity.Category;

import java.util.List;

public interface CategoryService {

    Category create(CategoryDto categoryDto);
    List<Category> getAllCategories();
}
