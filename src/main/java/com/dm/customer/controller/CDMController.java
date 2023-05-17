package com.dm.customer.controller;

import com.dm.customer.dto.MessageResponse;
import com.dm.customer.dto.customersearch.CustomerSearchRequest;
import com.dm.customer.dto.customersearch.CustomerSearchResponse;
import com.dm.customer.exception.RestApiResponse;
import com.dm.customer.httpclient.HttpResponseWrapper;
import com.dm.customer.model.CDMModel;
import com.dm.customer.repository.CDMRepository;
import com.dm.customer.service.CDMService;
import com.dm.customer.service.impl.CDMServiceImpl;
import com.dm.customer.util.CDMEnum;
import com.dm.customer.util.CDMLogger;
import com.dm.customer.util.CDMUtility;
import com.dm.customer.util.CDMValidator;
import io.micronaut.core.annotation.ReflectiveAccess;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.inject.Inject;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.dm.customer.util.APIRoute.*;
import static com.dm.customer.util.CDMUtility.*;
import static java.util.Objects.isNull;

/**
 * Customer Data Management Controller
 */
@Controller
@CrossOrigin//(origins = {"http://localhost:3000"})
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CDMController {

    final String className = getClass().getSimpleName();
    @Inject
    CDMLogger cdmLogger;
    @Inject
    CDMValidator cdmValidator;
    @Inject
    CDMService cdmService;
    @Inject
    CDMServiceImpl cdmServiceImpl;
    @Inject
    CDMRepository cdmRepository;

    /**
     * Validate and Post Customer Data into postgresql
     * @param cdmModel
     * @return
     */
    @ReflectiveAccess
    @Post(value = CUSTOMER_DATA_POST)
    @ApiResponse(responseCode = "200", description = "Success", content = @Content(schema = @Schema(implementation = HttpResponseWrapper.class)))
    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = HttpResponseWrapper.class)))
    public HttpResponse<RestApiResponse> postCustomerData(@Body CDMModel cdmModel) {
        final String methodName = "postCustomerData";
        final List<CDMEnum> cdmEnumList = new ArrayList<>();
        Optional<CDMModel> existingCustomerMobileNumber = cdmRepository.findByCustomerMobileNumberIgnoreCase(cdmModel.getCustomerMobileNumber());
        Optional<CDMModel> existingCustomerPanCardNumber = cdmRepository.findByCustomerPanCardNumberIgnoreCase(cdmModel.getCustomerPanCardNumber());

        if (CDMUtility.blankOrNullCustomerName(cdmModel.getCustomerName())) {
            cdmLogger.logs(className, methodName, BLANK_OR_NULL_CUSTOMER_NAME);
            cdmEnumList.add(CDMEnum.ERROR_REQUEST_CUSTOMER_NAME_BLANK_OR_NULL);
        }
        if (CDMUtility.blankOrNullCustomerMobileNumber(cdmModel.getCustomerMobileNumber())) {
            cdmLogger.logs(className, methodName, BLANK_OR_NULL_MOBILE_NUMBER);
            cdmEnumList.add(CDMEnum.ERROR_REQUEST_CUSTOMER_MOBILE_NUMBER_BLANK_OR_NULL);
        } else if (CDMUtility.invalidCustomerMobileNumber(cdmModel.getCustomerMobileNumber())) {
            cdmLogger.logs(className, methodName, INVALID_MOBILE_NUMBER);
            cdmEnumList.add(CDMEnum.ERROR_REQUEST_CUSTOMER_MOBILE_NUMBER_IS_INVALID);
        } else if (existingCustomerMobileNumber.isPresent()) {
            cdmLogger.logs(className, methodName, "Customer mobile number already exist");
            cdmEnumList.add(CDMEnum.ERROR_REQUEST_CUSTOMER_MOBILE_NUMBER_ALREADY_EXIST);
        }
        if (CDMUtility.blankOrNullCustomerPanCardNumber(cdmModel.getCustomerPanCardNumber())) {
            cdmLogger.logs(className, methodName, BLANK_OR_NULL_PAN_CARD_NUMBER);
            cdmEnumList.add(CDMEnum.ERROR_REQUEST_CUSTOMER_PAN_CARD_NUMBER_BLANK_OR_NULL);
        } else if (CDMUtility.invalidCustomerPanCardNumber(cdmModel.getCustomerPanCardNumber())) {
            cdmLogger.logs(className, methodName, INVALID_PAN_CARD_NUMBER);
            cdmEnumList.add(CDMEnum.ERROR_REQUEST_CUSTOMER_PAN_CARD_NUMBER_IS_INVALID);
        } else if (existingCustomerPanCardNumber.isPresent()) {
            cdmLogger.logs(className, methodName, ALREADY_EXISTS_PAN_CARD_NUMBER);
            cdmEnumList.add(CDMEnum.ERROR_REQUEST_CUSTOMER_PAN_CARD_NUMBER_ALREADY_EXIST);
        }
        if (CDMUtility.blankOrNullCustomerActiveStatus(cdmModel.getCustomerActiveStatus())) {
            cdmLogger.logs(className, methodName, BLANK_OR_NULL_ACTIVE_STATUS);
            cdmEnumList.add(CDMEnum.ERROR_REQUEST_CUSTOMER_ACTIVE_STATUS_BLANK_OR_NULL);
        } else if (CDMUtility.invalidCustomerActiveStatus(cdmModel.getCustomerActiveStatus())) {
            cdmLogger.logs(className, methodName, INVALID_ACTIVE_STATUS);
            cdmEnumList.add(CDMEnum.ERROR_REQUEST_CUSTOMER_ACTIVE_STATUS_IS_INVALID);
        }
        if (CDMUtility.blankOrNullCustomerAddress(cdmModel.getCustomerAddress())) {
            cdmLogger.logs(className, methodName, BLANK_OR_NULL_ADDRESS);
            cdmEnumList.add(CDMEnum.ERROR_REQUEST_CUSTOMER_ADDRESS_BLANK_OR_NULL);
        }
        if (CDMUtility.blankOrNullCreatedDate(cdmModel.getCreatedDate())) {
            cdmLogger.logs(className, methodName, BLANK_OR_NULL_CREATED_DATE);
            cdmEnumList.add(CDMEnum.ERROR_REQUEST_CREATED_DATE_BLANK_OR_NULL);
        }
        if (CDMUtility.blankOrNullCreatedBy(cdmModel.getCreatedBy())) {
            cdmLogger.logs(className, methodName, BLANK_OR_NULL_CREATED_BY);
            cdmEnumList.add(CDMEnum.ERROR_REQUEST_CREATED_BY_BLANK_OR_NULL);
        }
        if (CDMUtility.blankOrNullUpdatedDate(cdmModel.getUpdatedDate())) {
            cdmLogger.logs(className, methodName, BLANK_OR_NULL_UPDATED_DATE);
            cdmEnumList.add(CDMEnum.ERROR_REQUEST_UPDATED_DATE_BLANK_OR_NULL);
        }
        if (CDMUtility.blankOrNullUpdatedBy(cdmModel.getUpdatedBy())) {
            cdmLogger.logs(className, methodName, BLANK_OR_NULL_UPDATED_BY);
            cdmEnumList.add(CDMEnum.ERROR_REQUEST_UPDATED_BY_BLANK_OR_NULL);
        }
        if (!cdmEnumList.isEmpty()) return HttpResponse.badRequest(addMessage(FAILURE, cdmEnumList));
        else {
            cdmServiceImpl.postCustomerData(cdmModel);
            cdmLogger.logs(className, methodName, "Customer data validated and added successfully");
            cdmEnumList.add(CDMEnum.CUSTOMER_DATA_VALIDATED_AND_ADDED_SUCCESSFULLY);
            return HttpResponse.ok(addMessage(SUCCESS, cdmEnumList));
        }
    }

    /**
     * Validate and filter customer data
     * @param customerSearchRequest
     * @return
     */
    @Post(value = CUSTOMER_DATA_SEARCH)
    @ApiResponse(responseCode = "200", description = "Success", content = @Content(schema = @Schema(implementation = CustomerSearchResponse.class)))
    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = MessageResponse.class)))
    @ApiResponse(responseCode = "204", description = "Not found - No Content found")
    public HttpResponse<RestApiResponse> getCustomerData(@Body CustomerSearchRequest customerSearchRequest) {
        CustomerSearchResponse customerSearchResponse;
        MessageResponse messageResponse;
        String methodName = "getCustomerData";
        cdmLogger.logs(className, methodName, String.format("Received request for customer search, request = {%s}", customerSearchRequest));
        if (isValidCustomerSearchRequest(customerSearchRequest)) {
            cdmLogger.logs(className, methodName, String.format("get Customer object for CustomerSearch  = {%s}", customerSearchRequest));
            messageResponse = addMessage(FAILURE, List.of(CDMEnum.ERROR_REQUEST_BODY_IS_MISSING_OR_MALFORMED));
            return HttpResponseWrapper.badRequest(messageResponse);
        } else {
            messageResponse = cdmValidator.validateCustomerSearchRequestFormat(customerSearchRequest);
            if (!isNull(messageResponse)) {
                cdmLogger.logs(className, methodName, String.format("invalid format for customer search request  = {%s}", customerSearchRequest));
                return HttpResponseWrapper.badRequest(messageResponse);
            }
        }
        customerSearchResponse = cdmService.customerSearch(customerSearchRequest);
        if (Optional.ofNullable(customerSearchResponse).isEmpty() || Optional.ofNullable(customerSearchResponse.getData()).isEmpty() || customerSearchResponse.getData().isEmpty()) {
            cdmLogger.logs(className, methodName, String.format("No data found for request = {%s}", customerSearchRequest));
            return HttpResponseWrapper.noContentFound();
        }
        cdmLogger.logs(className, methodName, "Successfully received response from customer data");
        return HttpResponseWrapper.ok(customerSearchResponse);
    }

    /**
     * Get all the customer data
     * @return
     */
    @Get(value = CUSTOMER_DATA_GET)
    @ApiResponse(responseCode = "200", description = "Success", content = @Content(schema = @Schema(implementation = CDMModel.class)))
    public List<CDMModel> getAllCustomerData() {
        return cdmServiceImpl.getCustomerData();
    }

    /**
     * Update specific existing customer data using customer id
     * @param id
     * @param cdmModel
     * @return
     */
    @ReflectiveAccess
    @Put(value = CUSTOMER_DATA_UPDATE + "/{id}")
    @ApiResponse(responseCode = "200", description = "Success", content = @Content(schema = @Schema(implementation = MessageResponse.class)))
    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = MessageResponse.class)))
    public HttpResponse<RestApiResponse> updateCustomerData(@PathVariable String id, @Body CDMModel cdmModel) {
        final List<CDMEnum> cdmEnumList = new ArrayList<>();
        final String methodName = "updateCustomerData";
        Optional<CDMModel> existingCustomerMobileNumber = cdmRepository.findByCustomerMobileNumberIgnoreCaseAndCustomerIdNot(cdmModel.getCustomerMobileNumber(), Integer.valueOf(id));
        Optional<CDMModel> existingCustomerPanCardNumber = cdmRepository.findByCustomerPanCardNumberIgnoreCaseAndCustomerIdNot(cdmModel.getCustomerPanCardNumber(), Integer.valueOf(id));

        if (CDMUtility.blankOrNullCustomerName(cdmModel.getCustomerName())) {
            cdmLogger.logs(className, methodName, BLANK_OR_NULL_CUSTOMER_NAME);
            cdmEnumList.add(CDMEnum.ERROR_REQUEST_CUSTOMER_NAME_BLANK_OR_NULL);
        }
        if (CDMUtility.blankOrNullCustomerMobileNumber(cdmModel.getCustomerMobileNumber())) {
            cdmLogger.logs(className, methodName, BLANK_OR_NULL_MOBILE_NUMBER);
            cdmEnumList.add(CDMEnum.ERROR_REQUEST_CUSTOMER_MOBILE_NUMBER_BLANK_OR_NULL);
        } else if (CDMUtility.invalidCustomerMobileNumber(cdmModel.getCustomerMobileNumber())) {
            cdmLogger.logs(className, methodName, INVALID_MOBILE_NUMBER);
            cdmEnumList.add(CDMEnum.ERROR_REQUEST_CUSTOMER_MOBILE_NUMBER_IS_INVALID);
        } else if (existingCustomerMobileNumber.isPresent()) {
            cdmLogger.logs(className, methodName, "Customer mobile number already exist");
            cdmEnumList.add(CDMEnum.ERROR_REQUEST_CUSTOMER_MOBILE_NUMBER_ALREADY_EXIST);
        }
        if (CDMUtility.blankOrNullCustomerPanCardNumber(cdmModel.getCustomerPanCardNumber())) {
            cdmLogger.logs(className, methodName, BLANK_OR_NULL_PAN_CARD_NUMBER);
            cdmEnumList.add(CDMEnum.ERROR_REQUEST_CUSTOMER_PAN_CARD_NUMBER_BLANK_OR_NULL);
        } else if (CDMUtility.invalidCustomerPanCardNumber(cdmModel.getCustomerPanCardNumber())) {
            cdmLogger.logs(className, methodName, INVALID_PAN_CARD_NUMBER);
            cdmEnumList.add(CDMEnum.ERROR_REQUEST_CUSTOMER_PAN_CARD_NUMBER_IS_INVALID);
        } else if (existingCustomerPanCardNumber.isPresent()) {
            cdmLogger.logs(className, methodName, "Customer pan card number already exist");
            cdmEnumList.add(CDMEnum.ERROR_REQUEST_CUSTOMER_PAN_CARD_NUMBER_ALREADY_EXIST);
        }
        if (CDMUtility.blankOrNullCustomerActiveStatus(cdmModel.getCustomerActiveStatus())) {
            cdmLogger.logs(className, methodName, BLANK_OR_NULL_ACTIVE_STATUS);
            cdmEnumList.add(CDMEnum.ERROR_REQUEST_CUSTOMER_ACTIVE_STATUS_BLANK_OR_NULL);
        } else if (CDMUtility.invalidCustomerActiveStatus(cdmModel.getCustomerActiveStatus())) {
            cdmLogger.logs(className, methodName, INVALID_ACTIVE_STATUS);
            cdmEnumList.add(CDMEnum.ERROR_REQUEST_CUSTOMER_ACTIVE_STATUS_IS_INVALID);
        }
        if (CDMUtility.blankOrNullCustomerAddress(cdmModel.getCustomerAddress())) {
            cdmLogger.logs(className, methodName, BLANK_OR_NULL_ADDRESS);
            cdmEnumList.add(CDMEnum.ERROR_REQUEST_CUSTOMER_ADDRESS_BLANK_OR_NULL);
        }
        if (CDMUtility.blankOrNullUpdatedDate(cdmModel.getUpdatedDate())) {
            cdmLogger.logs(className, methodName, BLANK_OR_NULL_UPDATED_DATE);
            cdmEnumList.add(CDMEnum.ERROR_REQUEST_UPDATED_DATE_BLANK_OR_NULL);
        }
        if (CDMUtility.blankOrNullUpdatedBy(cdmModel.getUpdatedBy())) {
            cdmLogger.logs(className, methodName, BLANK_OR_NULL_UPDATED_BY);
            cdmEnumList.add(CDMEnum.ERROR_REQUEST_UPDATED_BY_BLANK_OR_NULL);
        }
        if (!cdmEnumList.isEmpty()) {
            return HttpResponse.badRequest(addMessage(FAILURE, cdmEnumList));
        } else {
            cdmServiceImpl.updateCustomerData(id, cdmModel);
            cdmLogger.logs(className, methodName, "Customer data validated and updated successfully");
            cdmEnumList.add(CDMEnum.CUSTOMER_DATA_VALIDATED_AND_UPDATED_SUCCESSFULLY);
            return HttpResponse.ok(addMessage(SUCCESS, cdmEnumList));
        }
    }

    /**
     * Delete specific customer data using customer id
     * @param id
     * @return
     */
    @Delete(value = CUSTOMER_DATA_DELETE + "/{id}")
    @ApiResponse(responseCode = "200", description = "Success", content = @Content(schema = @Schema(implementation = MessageResponse.class)))
    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = MessageResponse.class)))
    public HttpResponse<RestApiResponse> deleteCustomerData(@PathVariable("id") String id) {
        final List<CDMEnum> cdmEnumList = new ArrayList<>();
        final String methodName = "deleteCustomerData";

        if (cdmRepository.existsById(Integer.valueOf(id))) {
            cdmServiceImpl.deleteCustomerData(id);
            cdmLogger.logs(className, methodName, "Customer data deleted successfully");
            cdmEnumList.add(CDMEnum.CUSTOMER_DATA_DELETED_SUCCESSFULLY);
            return HttpResponse.ok(addMessage(SUCCESS, cdmEnumList));
        } else {
            cdmLogger.logs(className, methodName, "Customer data not exists");
            cdmEnumList.add(CDMEnum.CUSTOMER_DATA_NOT_EXISTS);
            return HttpResponse.badRequest(addMessage(FAILURE, cdmEnumList));
        }
    }
}