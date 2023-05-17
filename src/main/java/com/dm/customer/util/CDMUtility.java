package com.dm.customer.util;

import com.dm.customer.dto.Message;
import com.dm.customer.dto.MessageResponse;
import com.dm.customer.dto.customersearch.CustomerSearchRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.regex.Pattern;

import static java.util.Objects.isNull;

/**
 * CDMUtility class is used for static values and validations
 */
public class CDMUtility {

    public static final String SUCCESS = "success";
    public static final String FAILURE = "failure";
    public static final String CDM_200 = "CDM-200";
    public static final String CDM_400 = "CDM-400";
    public static final String NULL_STR = "null";
    public static final String BLANK_OR_NULL_CUSTOMER_NAME = "CDMValidator.validateCustomerNameRequest :: cdmModel.getCustomerName object is blank or null";
    public static final String BLANK_OR_NULL_MOBILE_NUMBER = "CDMValidator.validateCustomerMobileNumberRequest :: cdmModel.getCustomerMobileNumber object is blank or null";
    public static final String INVALID_MOBILE_NUMBER = "CDMValidator.validateCustomerMobileNumberRequest :: cdmModel.getCustomerMobileNumber object is invalid";
    public static final String BLANK_OR_NULL_PAN_CARD_NUMBER = "CDMValidator.validateCustomerPanCardNumberRequest :: cdmModel.getCustomerPanCardNumber object is blank or null";
    public static final String INVALID_PAN_CARD_NUMBER = "CDMValidator.validateCustomerPanCardNumberRequest :: cdmModel.getCustomerPanCardNumber object is invalid";
    public static final String ALREADY_EXISTS_PAN_CARD_NUMBER = "CDMValidator.validateCustomerPanCardNumberRequest :: cdmModel.getCustomerPanCardNumber object is already exist";
    public static final String BLANK_OR_NULL_ACTIVE_STATUS = "CDMValidator.validateCustomerActiveStatusRequest :: cdmModel.getCustomerActiveStatus object is blank or null";
    public static final String BLANK_OR_NULL_ADDRESS = "CDMValidator.validateCustomerAddressRequest :: cdmModel.getCustomerAddress object is blank or null";
    public static final String BLANK_OR_NULL_CREATED_DATE = "CDMValidator.validateCreatedDateRequest :: cdmModel.getCreatedDate object is blank or null";
    public static final String BLANK_OR_NULL_CREATED_BY = "CDMValidator.validateCreatedByRequest :: cdmModel.getCreatedBy object is blank or null";
    public static final String BLANK_OR_NULL_UPDATED_DATE = "CDMValidator.validateUpdatedDateRequest :: cdmModel.getUpdatedDate object is blank or null";
    public static final String BLANK_OR_NULL_UPDATED_BY = "CDMValidator.validateUpdatedByRequest :: cdmModel.getUpdatedBy object is blank or null";
    public static final String INVALID_ACTIVE_STATUS = "CDMValidator.validateCustomerActiveStatusRequest :: cdmModel.getCustomerActiveStatus object is invalid";
    public static final String BLANK_OR_NULL_USER_NAME_REGISTER = "CDMValidator.validateUserNameRequest :: registerRequest.getUserName object is blank or null";
    public static final String BLANK_OR_NULL_USER_EMAIL_REGISTER = "CDMValidator.validateUserEmailRequest :: registerRequest.getUserEmail object is blank or null";
    public static final String INVALID_USER_EMAIL_REGISTER = "CDMValidator.validateUserEmailRequest :: registerRequest.getUserEmail object is invalid";
    public static final String ALREADY_EXISTS_USER_EMAIL_REGISTER = "CDMValidator.validateUserEmailRequest :: registerRequest.getUserEmail object is already exists";
    public static final String BLANK_OR_NULL_USER_PASSWORD_REGISTER = "CDMValidator.validateUserPasswordRequest :: registerRequest.getPassword object is blank or null";
    public static final String BLANK_OR_NULL_USER_EMAIL_LOGIN = "CDMValidator.validateUserEmailRequest :: loginRequest.getUserEmail object is blank or null";
    public static final String INVALID_USER_EMAIL_LOGIN = "CDMValidator.validateUserEmailRequest :: loginRequest.getUserEmail object is invalid";
    public static final String BLANK_OR_NULL_USER_PASSWORD_LOGIN = "CDMValidator.validateUserPasswordRequest :: loginRequest.getPassword object is blank or null";
    public static final String INVALID_LOGIN_CREDENTIALS = "Invalid Login Credentials";
    private static final String CUSTOMER_ID_PATTERN = "^\\d+$";
    private static final String MOBILE_NUMBER_PATTERN = "^\\d{10}$";
    private static final String PAN_CARD_NUMBER_PATTERN = "[A-Z]{5}\\d{4}[A-Z]";
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9_+&*-]+(?:\\."+ "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";
    private CDMUtility() {
        throw new IllegalStateException("Utility class");
    }

    public static boolean isValidCustomerSearchRequest(final CustomerSearchRequest customerSearchRequest) {
        return isNull(customerSearchRequest) || (isNullBlank(customerSearchRequest.getCustomerId()) && isNullBlank(customerSearchRequest.getCustomerName()) && isNullBlank(customerSearchRequest.getCustomerMobileNumber()) && isNullBlank(customerSearchRequest.getCustomerPanCardNumber()) && isNullBlank(customerSearchRequest.getCustomerActiveStatus()) && isNullBlank(customerSearchRequest.getCreatedDate()) && isNullBlank(customerSearchRequest.getCreatedBy()) && isNullBlank(customerSearchRequest.getUpdatedDate()) && isNullBlank(customerSearchRequest.getUpdatedBy()));
    }

    public static boolean invalidCustomerId(String customerId) {
        boolean isMatch = Pattern.compile(CUSTOMER_ID_PATTERN).matcher(customerId).find();
        return !isMatch;
    }

    public static boolean blankOrNullCustomerName(String customerName) {
        return Objects.equals(customerName, "") || customerName == null;
    }

    public static boolean blankOrNullCustomerMobileNumber(String customerMobileNumber) {
        return Objects.equals(customerMobileNumber, "") || customerMobileNumber == null;
    }

    public static boolean invalidCustomerMobileNumber(String customerMobileNumber) {
        boolean isMatch = Pattern.compile(MOBILE_NUMBER_PATTERN).matcher(customerMobileNumber).find();
        return !isMatch;
    }

    public static boolean blankOrNullCustomerPanCardNumber(String customerPanCardNumber) {
        return Objects.equals(customerPanCardNumber, "") || customerPanCardNumber == null;
    }

    public static boolean invalidCustomerPanCardNumber(String customerPanCardNumber) {
        boolean isMatch = Pattern.compile(PAN_CARD_NUMBER_PATTERN).matcher(customerPanCardNumber).find();
        return !isMatch;
    }

    public static boolean blankOrNullCustomerActiveStatus(String customerActiveStatus) {
        return Objects.equals(customerActiveStatus, "") || customerActiveStatus == null;
    }

    public static boolean invalidCustomerActiveStatus(String customerActiveStatus) {
        return ((!Objects.equals(customerActiveStatus, "True")) && (!Objects.equals(customerActiveStatus, "False")));
    }

    public static boolean blankOrNullCustomerAddress(String customerAddress) {
        return Objects.equals(customerAddress, "") || customerAddress == null;
    }

    public static boolean blankOrNullCreatedDate(String createdDate) {
        return Objects.equals(createdDate, "") || createdDate == null;
    }

    public static boolean blankOrNullCreatedBy(String createdBy) {
        return Objects.equals(createdBy, "") || createdBy == null;
    }

    public static boolean blankOrNullUpdatedDate(String updatedDate) {
        return Objects.equals(updatedDate, "") || updatedDate == null;
    }

    public static boolean blankOrNullUpdatedBy(String updatedBy) {
        return Objects.equals(updatedBy, "") || updatedBy == null;
    }

    public static boolean blankOrNullUserName(String userName) {
        return Objects.equals(userName, "") || userName == null;
    }

    public static boolean blankOrNullUserEmail(String userEmail) {
        return Objects.equals(userEmail, "") || userEmail == null;
    }

    public static boolean invalidUserEmail(String userEmail) {
        boolean isMatch = Pattern.compile(EMAIL_PATTERN).matcher(userEmail).find();
        return !isMatch;
    }

    public static boolean blankOrNullUserPassword(String userPassword) {
        return Objects.equals(userPassword, "") || userPassword == null;
    }

    public static boolean isNullBlank(String in) {
        if (in == null) {
            return true;
        }
        return isTrimEmpty(in) || in.trim()
                .toLowerCase(Locale.getDefault())
                .equals(NULL_STR)
                || in.trim()
                .toLowerCase(Locale.getDefault())
                .equals("none");
    }

    private static boolean isTrimEmpty(final String in) {
        if (in == null) {
            return true;
        }
        for (int i = 0; i < in.length(); i++) {
            if (!Character.isWhitespace(in.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * This method returns the values in message response for success and failure
     * @param status
     * @param cdmEnumList
     * @return
     */
    public static MessageResponse addMessage(String status, List<CDMEnum> cdmEnumList) {

        final MessageResponse messageResponse = new MessageResponse();
        final List<Message> messages = new ArrayList<>();
        messageResponse.setStatus(status);
        for (CDMEnum anCDMEnum : cdmEnumList) {
            messageResponse.setStatusCode(anCDMEnum.getHttpStatus().getCode());
            messageResponse.setCode(anCDMEnum.getCode());
            String message = anCDMEnum.getMessage();
            Message newMessage = new Message();
            newMessage.setMessages(message);
            messages.add(newMessage);
        }
        messageResponse.setMessages(messages);
        return messageResponse;
    }
}
