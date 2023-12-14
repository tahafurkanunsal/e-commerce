package com.tfunsal.ecommerce.repository;

import com.tfunsal.ecommerce.entity.User;
import com.tfunsal.ecommerce.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User , Long> {

    Optional<User> findFirstByEmail(String email);

    User findByRole(UserRole userRole);
}
