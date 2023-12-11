package com.tfunsal.ecommerce.services.admin.category;

import com.tfunsal.ecommerce.dto.CategoryDto;
import com.tfunsal.ecommerce.entity.Category;

public interface CategoryService {

    Category create(CategoryDto categoryDto);
}
