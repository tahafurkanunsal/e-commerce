package com.tfunsal.ecommerce.services.customer;

import com.tfunsal.ecommerce.dto.ProductDto;

import java.util.List;

public interface CustomerProductService {

    List<ProductDto> getAllProducts();

    List<ProductDto> getAllProductsByName(String title);
}
