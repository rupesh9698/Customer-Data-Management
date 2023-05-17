package com.dm.customer.dto.userlogin;

import com.dm.customer.dto.UserDetails;
import com.dm.customer.exception.RestApiResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Login response model class
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginRegisterResponse implements RestApiResponse {

    private String status;
    private List<UserDetails> data;
}
