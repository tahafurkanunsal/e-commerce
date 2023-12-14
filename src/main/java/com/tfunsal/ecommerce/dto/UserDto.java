package com.tfunsal.ecommerce.dto;

import com.tfunsal.ecommerce.enums.UserRole;
import lombok.Data;

@Data
public class UserDto {

    private Long id;

    private String email;

    private String name;

    private UserRole userRole;

}
