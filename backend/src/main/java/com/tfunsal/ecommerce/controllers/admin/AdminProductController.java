package com.tfunsal.ecommerce.controllers.admin;

import com.tfunsal.ecommerce.dto.ProductDto;
import com.tfunsal.ecommerce.services.admin.product.AdminProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminProductController {

    private final AdminProductService adminProductService;


    @PostMapping("/product")
    public ResponseEntity<ProductDto> create(@ModelAttribute ProductDto productDto) throws IOException {
        ProductDto pDto = adminProductService.addProduct(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(pDto);
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        List<ProductDto> productDtos = adminProductService.getAllProducts();
        return ResponseEntity.ok(productDtos);
    }
}
