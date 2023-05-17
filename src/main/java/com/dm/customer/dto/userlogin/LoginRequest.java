package com.dm.customer.dto.userlogin;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User login request model class
 */
@Data
@NoArgsConstructor
public class LoginRequest {
    private String userEmail;
    private String password;
}