package com.tfunsal.ecommerce.controllers.customer;

import com.tfunsal.ecommerce.dto.ProductDto;
import com.tfunsal.ecommerce.services.customer.CustomerProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerProductController {

    private final CustomerProductService customerProductService;


    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        List<ProductDto> productDtos = customerProductService.getAllProducts();
        return ResponseEntity.ok(productDtos);
    }


    @GetMapping("/search/{title}")
    public ResponseEntity<List<ProductDto>> getAllProductsByName(@PathVariable String title) {
        List<ProductDto> productDtos = customerProductService.getAllProductsByName(title);
        return ResponseEntity.ok(productDtos);
    }
}
