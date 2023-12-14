package com.tfunsal.ecommerce.repository;

import com.tfunsal.ecommerce.entity.Order;
import com.tfunsal.ecommerce.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order , Long> {
    Order findByUserIdAndOrderStatus(Long userId, OrderStatus orderStatus);
}
