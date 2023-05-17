package com.dm.customer.util;

import io.micronaut.http.HttpStatus;

import static com.dm.customer.util.CDMUtility.CDM_200;
import static com.dm.customer.util.CDMUtility.CDM_400;

/**
 * CDMEnum enum for code message and http status
 */
public enum CDMEnum {
    ERROR_REQUEST_CUSTOMER_ID_IS_INVALID(CDM_400, "Bad-Request - Error request customer id is invalid", HttpStatus.BAD_REQUEST),
    ERROR_REQUEST_CUSTOMER_NAME_BLANK_OR_NULL(CDM_400, "Bad-Request - Error request customer name blank or null", HttpStatus.BAD_REQUEST),
    ERROR_REQUEST_CUSTOMER_MOBILE_NUMBER_BLANK_OR_NULL(CDM_400, "Bad-Request - Error request customer mobile number blank or null", HttpStatus.BAD_REQUEST),
    ERROR_REQUEST_CUSTOMER_MOBILE_NUMBER_IS_INVALID(CDM_400, "Bad-Request - Error request customer mobile number is invalid", HttpStatus.BAD_REQUEST),
    ERROR_REQUEST_CUSTOMER_MOBILE_NUMBER_ALREADY_EXIST(CDM_400, "Bad-Request - Error request customer mobile number already exists", HttpStatus.BAD_REQUEST),
    ERROR_REQUEST_CUSTOMER_PAN_CARD_NUMBER_BLANK_OR_NULL(CDM_400, "Bad-Request - Error request customer pan card number blank or null", HttpStatus.BAD_REQUEST),
    ERROR_REQUEST_CUSTOMER_PAN_CARD_NUMBER_IS_INVALID(CDM_400, "Bad-Request - Error request customer pan card number is invalid", HttpStatus.BAD_REQUEST),
    ERROR_REQUEST_CUSTOMER_PAN_CARD_NUMBER_ALREADY_EXIST(CDM_400, "Bad-Request - Error request customer pan card number already exists", HttpStatus.BAD_REQUEST),
    ERROR_REQUEST_CUSTOMER_ACTIVE_STATUS_BLANK_OR_NULL(CDM_400, "Bad-Request - Error request customer active status blank or null", HttpStatus.BAD_REQUEST),
    ERROR_REQUEST_CUSTOMER_ACTIVE_STATUS_IS_INVALID(CDM_400, "Bad-Request - Error request customer active status is invalid", HttpStatus.BAD_REQUEST),
    ERROR_REQUEST_CUSTOMER_ADDRESS_BLANK_OR_NULL(CDM_400, "Bad-Request - Error request customer address blank or null", HttpStatus.BAD_REQUEST),
    ERROR_REQUEST_CREATED_DATE_BLANK_OR_NULL(CDM_400, "Bad-Request - Error request created date blank or null", HttpStatus.BAD_REQUEST),
    ERROR_REQUEST_CREATED_BY_BLANK_OR_NULL(CDM_400, "Bad-Request - Error request created by blank or null", HttpStatus.BAD_REQUEST),
    ERROR_REQUEST_UPDATED_DATE_BLANK_OR_NULL(CDM_400, "Bad-Request - Error request updated date blank or null", HttpStatus.BAD_REQUEST),
    ERROR_REQUEST_UPDATED_BY_BLANK_OR_NULL(CDM_400, "Bad-Request - Error request updated by blank or null", HttpStatus.BAD_REQUEST),
    ERROR_REQUEST_BODY_IS_MISSING_OR_MALFORMED(CDM_400, "Bad-Request - Required request body is missing or malformed", HttpStatus.BAD_REQUEST),
    ERROR_REQUEST_USER_NAME_BLANK_OR_NULL(CDM_400, "Bad-Request - Error request user name blank or null", HttpStatus.BAD_REQUEST),
    ERROR_REQUEST_USER_EMAIL_BLANK_OR_NULL(CDM_400, "Bad-Request - Error request user email blank or null", HttpStatus.BAD_REQUEST),
    ERROR_REQUEST_USER_EMAIL_IS_INVALID(CDM_400, "Bad-Request - Error request user email is invalid", HttpStatus.BAD_REQUEST),
    ERROR_REQUEST_USER_EMAIL_IS_ALREADY_EXISTS(CDM_400, "Bad-Request - Error request user email is already exists", HttpStatus.BAD_REQUEST),
    ERROR_REQUEST_USER_PASSWORD_BLANK_OR_NULL(CDM_400, "Bad-Request - Error request user password blank or null", HttpStatus.BAD_REQUEST),
    ERROR_REQUEST_INVALID_LOGIN_CREDENTIALS(CDM_400, "Bad-Request - Error request invalid login credentials", HttpStatus.BAD_REQUEST),
    CUSTOMER_DATA_NOT_EXISTS(CDM_400, "Bad-Request - Customer Data not found", HttpStatus.BAD_REQUEST),
    CUSTOMER_DATA_VALIDATED_AND_ADDED_SUCCESSFULLY(CDM_200, "Success - Customer data validated and added successfully", HttpStatus.OK),
    CUSTOMER_DATA_VALIDATED_AND_UPDATED_SUCCESSFULLY(CDM_200, "Success - Customer data validated and updated successfully", HttpStatus.OK),
    CUSTOMER_DATA_DELETED_SUCCESSFULLY(CDM_200, "Success - Customer data deleted successfully", HttpStatus.OK);

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;

    CDMEnum(String code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
