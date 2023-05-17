package com.dm.customer.service.impl;

import com.dm.customer.dto.UserDetails;
import com.dm.customer.dto.userlogin.LoginRequest;
import com.dm.customer.dto.userlogin.LoginRegisterResponse;
import com.dm.customer.model.LoginRegisterModel;
import com.dm.customer.repository.LoginRegisterRepository;
import com.dm.customer.service.LoginRegisterService;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.dm.customer.util.CDMUtility.SUCCESS;

@Singleton
public class LoginRegisterServiceImpl implements LoginRegisterService {

    @Inject
    LoginRegisterRepository loginRegisterRepository;

    /**
     * Method for postUserRegister to save the data in database
     * @param loginRegisterModel
     * @return
     */
    public LoginRegisterModel postUserRegister(LoginRegisterModel loginRegisterModel) {
        return loginRegisterRepository.save(loginRegisterModel);
    }

    @Override
    public LoginRegisterResponse userLogin(LoginRequest loginRequest) {
        Optional<LoginRegisterModel> loginResult = null;
        LoginRegisterResponse loginRegisterResponse = new LoginRegisterResponse();

        if (Optional.ofNullable(loginRequest.getUserEmail()).isPresent() && Optional.ofNullable(loginRequest.getPassword()).isPresent()) {
            loginResult = loginRegisterRepository.findByUserEmail(loginRequest.getUserEmail());
        }

        Optional<LoginRegisterModel> loginModel = loginRegisterRepository.findByUserEmail(loginRequest.getUserEmail());
        List<UserDetails> userDetailsList = new ArrayList<>();
        if (loginModel.isPresent() && loginModel.get().getPassword().equals(loginRequest.getPassword())) {

            LoginRegisterModel details = loginResult.get();
            UserDetails userDetails = new UserDetails();
            userDetails.setUserId(String.valueOf(details.getUserId()));
            userDetails.setUserName(details.getUserName());
            userDetails.setUserEmail(details.getUserEmail());
            userDetails.setPassword(details.getPassword());
            userDetailsList.add(userDetails);

            loginRegisterResponse.setStatus(SUCCESS);
            loginRegisterResponse.setData(userDetailsList);
        }
        return loginRegisterResponse;
    }
}
