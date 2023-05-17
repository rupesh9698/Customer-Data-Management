package com.dm.customer.util;

import com.dm.customer.dto.MessageResponse;
import com.dm.customer.dto.customersearch.CustomerSearchRequest;
import com.dm.customer.repository.CDMRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.dm.customer.util.CDMUtility.FAILURE;
import static com.dm.customer.util.CDMUtility.addMessage;

/**
 * CDMValidator class validates the requests to get the filtered response
 */
@Singleton
public class CDMValidator {

    final String className = getClass().getSimpleName();
    @Inject
    CDMLogger cdmLogger;
    @Inject
    CDMRepository cdmRepository;

    /**
     * validateCustomerSearchRequestFormat validates the customer name, mobile number and pan card
     * @param customerSearchRequest
     * @return
     */
    public MessageResponse validateCustomerSearchRequestFormat(final CustomerSearchRequest customerSearchRequest) {
        final List<CDMEnum> cdmEnumList = new ArrayList<>();
        final String methodName = "validateCustomerSearchRequestFormat";

        if (Optional.ofNullable(customerSearchRequest.getCustomerId()).isPresent() && CDMUtility.invalidCustomerId(customerSearchRequest.getCustomerId())) {
            cdmLogger.logs(className, methodName, "CDMValidator.validateCustomerIdRequest :: cdmModel.getCustomerId object is invalid");
            cdmEnumList.add(CDMEnum.ERROR_REQUEST_CUSTOMER_ID_IS_INVALID);
        }
        if (Optional.ofNullable(customerSearchRequest.getCustomerMobileNumber()).isPresent() && CDMUtility.invalidCustomerMobileNumber(customerSearchRequest.getCustomerMobileNumber())) {
            cdmLogger.logs(className, methodName, "CDMValidator.validateCustomerMobileNumberRequest :: cdmModel.getCustomerMobileNumber object is invalid");
            cdmEnumList.add(CDMEnum.ERROR_REQUEST_CUSTOMER_MOBILE_NUMBER_IS_INVALID);
        }
        if (Optional.ofNullable(customerSearchRequest.getCustomerPanCardNumber()).isPresent() && CDMUtility.invalidCustomerPanCardNumber(customerSearchRequest.getCustomerPanCardNumber())) {
            cdmLogger.logs(className, methodName, "CDMValidator.validateCustomerPanCardNumberRequest :: cdmModel.getCustomerPanCardNumber object is invalid");
            cdmEnumList.add(CDMEnum.ERROR_REQUEST_CUSTOMER_PAN_CARD_NUMBER_IS_INVALID);
        }
        if (Optional.ofNullable(customerSearchRequest.getCustomerActiveStatus()).isPresent() && CDMUtility.invalidCustomerActiveStatus(customerSearchRequest.getCustomerActiveStatus())) {
            cdmLogger.logs(className, methodName, "CDMValidator.validateCustomerActiveStatusRequest :: cdmModel.getCustomerActiveStatus object is invalid");
            cdmEnumList.add(CDMEnum.ERROR_REQUEST_CUSTOMER_ACTIVE_STATUS_IS_INVALID);
        }
        if (!cdmEnumList.isEmpty()) {
            return addMessage(FAILURE, cdmEnumList);
        }
        return null;
    }
}
