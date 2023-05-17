package com.dm.customer.dto;

import lombok.Data;

/**
 * User details class used in login response
 */
@Data
public class UserDetails {

    private String userId;
    private String userName;
    private String userEmail;
    private String password;
}
