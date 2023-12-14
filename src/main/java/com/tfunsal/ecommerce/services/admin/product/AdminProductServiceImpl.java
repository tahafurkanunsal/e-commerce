package com.tfunsal.ecommerce.services.admin.product;

import com.tfunsal.ecommerce.dto.ProductDto;
import com.tfunsal.ecommerce.entity.Category;
import com.tfunsal.ecommerce.entity.Product;
import com.tfunsal.ecommerce.repository.CategoryRepository;
import com.tfunsal.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminProductServiceImpl implements AdminProductService{

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;


    public ProductDto addProduct(ProductDto productDto) throws IOException {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImage(productDto.getImage().getBytes());

        Category category = categoryRepository.findById(productDto.getCategoryId()).orElseThrow();

        product.setCategory(category);

        return productRepository.save(product).getDto();

    }

    public List<ProductDto> getAllProducts(){
        List<Product> products = productRepository.findAll();
        return products.stream().map(Product::getDto).collect(Collectors.toList());
    }

    public List<ProductDto> getAllProductsByName(String title){
        List<Product> products = productRepository.findAllByNameContaining(title);
        return products.stream().map(Product::getDto).collect(Collectors.toList());
    }

    public boolean deleteProduct(Long id){
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()){
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }


}
