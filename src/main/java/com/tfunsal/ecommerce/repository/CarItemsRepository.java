package com.tfunsal.ecommerce.repository;

import com.tfunsal.ecommerce.entity.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarItemsRepository extends JpaRepository<CartItems , Long> {
    Optional<CartItems> findByProductIdAndOrderIdAndUserId(Long productId, Long id, Long userId);
}
