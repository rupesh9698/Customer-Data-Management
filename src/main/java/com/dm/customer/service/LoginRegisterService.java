package com.dm.customer.service;

import com.dm.customer.dto.userlogin.LoginRequest;
import com.dm.customer.dto.userlogin.LoginRegisterResponse;

public interface LoginRegisterService {

    public LoginRegisterResponse userLogin(LoginRequest loginRequest);
}
