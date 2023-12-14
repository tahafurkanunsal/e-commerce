package com.tfunsal.ecommerce.services.customer.cart;


import com.tfunsal.ecommerce.dto.AddProductInCartDto;
import com.tfunsal.ecommerce.dto.OrderDto;
import org.springframework.http.ResponseEntity;

public interface CartService {

    ResponseEntity<?> addProductToCart(AddProductInCartDto addProductInCartDto);

    OrderDto getCartByUserId(Long userId);
}
