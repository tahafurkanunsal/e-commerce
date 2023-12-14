package com.tfunsal.ecommerce.services.customer.cart;

import com.tfunsal.ecommerce.dto.AddProductInCartDto;
import com.tfunsal.ecommerce.dto.CartItemsDto;
import com.tfunsal.ecommerce.dto.OrderDto;
import com.tfunsal.ecommerce.entity.CartItems;
import com.tfunsal.ecommerce.entity.Order;
import com.tfunsal.ecommerce.entity.Product;
import com.tfunsal.ecommerce.entity.User;
import com.tfunsal.ecommerce.enums.OrderStatus;
import com.tfunsal.ecommerce.repository.CarItemsRepository;
import com.tfunsal.ecommerce.repository.OrderRepository;
import com.tfunsal.ecommerce.repository.ProductRepository;
import com.tfunsal.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarItemsRepository carItemsRepository;

    @Autowired
    private ProductRepository productRepository;


    public ResponseEntity<?> addProductToCart(AddProductInCartDto addProductInCartDto){
        Order activeOrder = orderRepository.findByUserIdAndOrderStatus(addProductInCartDto.getUserId() , OrderStatus.Pending);
        Optional<CartItems> cartItems = carItemsRepository.findByProductIdAndOrderIdAndUserId(addProductInCartDto.getProductId() ,
                activeOrder.getId() , addProductInCartDto.getUserId());

        if(cartItems.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }else {
            Optional<Product> optionalProduct = productRepository.findById(addProductInCartDto.getProductId());
            Optional<User> optionalUser = userRepository.findById(addProductInCartDto.getUserId());

            if (optionalProduct.isPresent() && optionalUser.isPresent()){
                CartItems cart = new CartItems();
                cart.setProduct(optionalProduct.get());
                cart.setPrice(optionalProduct.get().getPrice());
                cart.setQuantity(1L);
                cart.setUser(optionalUser.get());
                cart.setOrder(activeOrder);

                CartItems updatedCart = carItemsRepository.save(cart);

                activeOrder.setTotalAmount(activeOrder.getAmount() + cart.getPrice());
                activeOrder.setAmount(activeOrder.getAmount() + cart.getPrice());
                activeOrder.getCartItems().add(cart);
                orderRepository.save(activeOrder);

                return ResponseEntity.status(HttpStatus.CREATED).body(cart);
            }else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or product not found");
            }
        }
    }

    public OrderDto getCartByUserId(Long userId){
        Order activeOrder = orderRepository.findByUserIdAndOrderStatus(userId , OrderStatus.Pending);
        List<CartItemsDto> cartItemsDtoList = activeOrder.getCartItems().stream().map(CartItems::getCartDto).collect(Collectors.toList());


        OrderDto orderDto = new OrderDto();
        orderDto.setAmount(activeOrder.getAmount());
        orderDto.setId(activeOrder.getId());
        orderDto.setOrderStatus(activeOrder.getOrderStatus());
        orderDto.setDiscount(activeOrder.getDiscount());
        orderDto.setCartItems(cartItemsDtoList);

        return orderDto;
    }
}
