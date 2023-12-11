package com.tfunsal.ecommerce.controllers.admin;

import com.tfunsal.ecommerce.dto.CategoryDto;
import com.tfunsal.ecommerce.entity.Category;
import com.tfunsal.ecommerce.services.admin.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminCategoryController {

    private final CategoryService categoryService;

    @PostMapping("category")
    public ResponseEntity<Category> create(@RequestBody CategoryDto categoryDto){
        Category category = categoryService.create(categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }


}
