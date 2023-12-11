package com.tfunsal.ecommerce.services.auth;

import com.tfunsal.ecommerce.dto.SignupRequest;
import com.tfunsal.ecommerce.dto.UserDto;

public interface AuthService {
    UserDto create(SignupRequest signupRequest);

    Boolean hasUserWithEmail(String email);
}
