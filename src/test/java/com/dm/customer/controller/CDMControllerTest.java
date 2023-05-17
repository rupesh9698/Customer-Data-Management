package com.dm.customer.controller;

import com.dm.customer.dto.CustomerDetails;
import com.dm.customer.dto.customersearch.CustomerSearchRequest;
import com.dm.customer.dto.customersearch.CustomerSearchResponse;
import com.dm.customer.exception.RestApiResponse;
import com.dm.customer.model.CDMModel;
import com.dm.customer.repository.CDMRepository;
import com.dm.customer.service.CDMService;
import com.dm.customer.service.impl.CDMServiceImpl;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.dm.customer.util.CDMUtility.FAILURE;
import static com.dm.customer.util.CDMUtility.SUCCESS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@MicronautTest
class CDMControllerTest {

    @Inject
    CDMController cdmController;

    @Inject
    CDMServiceImpl cdmServiceImpl;

    @Inject
    CDMService cdmService;

    @Inject
    CDMRepository cdmRepository;

    @MockBean(CDMRepository.class)
    CDMRepository cdmRepository() {
        return mock(CDMRepository.class);
    }

    @MockBean(CDMServiceImpl.class)
    CDMServiceImpl cdmServiceImpl() {
        return mock(CDMServiceImpl.class);
    }

    @Test
    @DisplayName("should return bad request when customer data has missing or invalid fields")
    void postCustomerDataWithMissingOrInvalidFieldsReturnsBadRequest() {
        CDMModel cdmModel = new CDMModel();
        cdmModel.setCustomerName("");
        cdmModel.setCustomerMobileNumber("");
        cdmModel.setCustomerPanCardNumber("");
        cdmModel.setCustomerActiveStatus("");
        cdmModel.setCustomerAddress("");
        cdmModel.setCreatedDate("");
        cdmModel.setCreatedBy("");
        cdmModel.setUpdatedDate("");
        cdmModel.setUpdatedBy("");
        HttpResponse<RestApiResponse> response = cdmController.postCustomerData(cdmModel);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatus());
    }

    @Test
    void postCustomerDataWithExistingMobileNumberReturnsBadRequest() {
        CDMModel cdmModel = new CDMModel();
        cdmModel.setCustomerName("Customer");
        cdmModel.setCustomerMobileNumber("9876543210");
        cdmModel.setCustomerPanCardNumber("ABCDE1234F");
        cdmModel.setCustomerActiveStatus("False");
        cdmModel.setCustomerAddress("Maharashtra");
        cdmModel.setCreatedDate("18/03/2023");
        cdmModel.setCreatedBy("User");
        cdmModel.setUpdatedDate("18/03/2023");
        cdmModel.setUpdatedBy("User");
        when(cdmRepository.findByCustomerMobileNumberIgnoreCase(cdmModel.getCustomerMobileNumber())).thenReturn(java.util.Optional.of(cdmModel));
        HttpResponse<RestApiResponse> response = cdmController.postCustomerData(cdmModel);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatus());
    }

    @Test
    void postCustomerDataWithExistingPanCardNumberReturnsBadRequest() {
        CDMModel cdmModel = new CDMModel();
        cdmModel.setCustomerName("Customer");
        cdmModel.setCustomerMobileNumber("9876543210");
        cdmModel.setCustomerPanCardNumber("ABCDE1234F");
        cdmModel.setCustomerActiveStatus("False");
        cdmModel.setCustomerAddress("Maharashtra");
        cdmModel.setCreatedDate("18/03/2023");
        cdmModel.setCreatedBy("User");
        cdmModel.setUpdatedDate("18/03/2023");
        cdmModel.setUpdatedBy("User");
        when(cdmRepository.findByCustomerPanCardNumberIgnoreCase(cdmModel.getCustomerPanCardNumber())).thenReturn(java.util.Optional.of(cdmModel));
        HttpResponse<RestApiResponse> response = cdmController.postCustomerData(cdmModel);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatus());
    }

    @ParameterizedTest
    @CsvSource({"1,Customer,987654321O,ABCDE1234F,False,Maharashtra,18/03/2023,User,18/03/2023,User", "1,Customer,9876543210,ABCDE12345,False,Maharashtra,18/03/2023,User,18/03/2023,User", "1,Customer,9876543210,ABCDE1234F,No,Maharashtra,18/03/2023,User,18/03/2023,User"})
    void postCustomerDataWhenInputIsInvalidThenReturnBadRequest(String customerId, String customerName, String customerMobileNumber, String customerPanCardNumber, String customerActiveStatus, String customerAddress, String createdDate, String createdBy, String updatedDate, String updatedBy) {
        CDMModel cdmModel = new CDMModel();
        cdmModel.setCustomerId(Integer.parseInt(customerId));
        cdmModel.setCustomerName(customerName);
        cdmModel.setCustomerMobileNumber(customerMobileNumber);
        cdmModel.setCustomerPanCardNumber(customerPanCardNumber);
        cdmModel.setCustomerActiveStatus(customerActiveStatus);
        cdmModel.setCustomerAddress(customerAddress);
        cdmModel.setCreatedDate(createdDate);
        cdmModel.setCreatedBy(createdBy);
        cdmModel.setUpdatedDate(updatedDate);
        cdmModel.setUpdatedBy(updatedBy);
        HttpResponse<RestApiResponse> response = cdmController.postCustomerData(cdmModel);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatus());
    }
    @Test
    void postCustomerDataWhenSuccess() {
        CDMModel cdmModel = new CDMModel();
        cdmModel.setCustomerId(Integer.parseInt("1"));
        cdmModel.setCustomerName("Customer");
        cdmModel.setCustomerMobileNumber("9876543210");
        cdmModel.setCustomerPanCardNumber("ABCDE1234F");
        cdmModel.setCustomerActiveStatus("False");
        cdmModel.setCustomerAddress("Maharashtra");
        cdmModel.setCreatedDate("18/03/2023");
        cdmModel.setCreatedBy("User");
        cdmModel.setUpdatedDate("18/03/2023");
        cdmModel.setUpdatedBy("User");
        HttpResponse<RestApiResponse> response = cdmController.postCustomerData(cdmModel);
        assertEquals(HttpStatus.OK, response.getStatus());
    }

    @Test
    void testGetCustomerSearchResult_failure_requestBodyNullFields() {
        CustomerSearchRequest customerSearchRequest = new CustomerSearchRequest();
        CustomerSearchResponse customerSearchResponse = new CustomerSearchResponse();
        customerSearchResponse.setStatus(FAILURE);
        when(cdmService.customerSearch(Mockito.any())).thenReturn(customerSearchResponse);
        HttpResponse<RestApiResponse> response = cdmController.getCustomerData(customerSearchRequest);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatus());
    }

    @Test
    void testGetCustomerSearchResult_failure_invalidRequestBodyFormat() {
        CustomerSearchRequest customerSearchRequest = new CustomerSearchRequest();
        customerSearchRequest.setCustomerId("1R");
        customerSearchRequest.setCustomerMobileNumber("987654321O");
        customerSearchRequest.setCustomerPanCardNumber("ABCDE12345");
        customerSearchRequest.setCustomerActiveStatus("No");
        HttpResponse<RestApiResponse> response = cdmController.getCustomerData(customerSearchRequest);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatus());
    }

    @Test
    void testGetCustomerSearchResult_failure_resultNotFound() {
        CustomerSearchRequest customerSearchRequest = new CustomerSearchRequest();
        customerSearchRequest.setCustomerId("1");
        customerSearchRequest.setCustomerName("Customer");
        customerSearchRequest.setCustomerMobileNumber("9876543210");
        customerSearchRequest.setCustomerPanCardNumber("ABCDE1234F");
        customerSearchRequest.setCustomerActiveStatus("False");
        customerSearchRequest.setCustomerAddress("Maharashtra");
        customerSearchRequest.setCreatedDate("18/03/2023");
        customerSearchRequest.setCreatedBy("User");
        customerSearchRequest.setUpdatedDate("18/03/2023");
        customerSearchRequest.setUpdatedBy("User");
        when(cdmService.customerSearch(Mockito.any())).thenReturn(null);
        HttpResponse response = cdmController.getCustomerData(customerSearchRequest);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatus());
    }

    @Test
    void testGetCustomerSearchResult_success() {
        CustomerSearchRequest customerSearchRequest = new CustomerSearchRequest();
        customerSearchRequest.setCustomerId("1");
        customerSearchRequest.setCustomerName("Customer");
        customerSearchRequest.setCustomerMobileNumber("9876543210");
        customerSearchRequest.setCustomerPanCardNumber("ABCDE1234F");
        customerSearchRequest.setCustomerActiveStatus("False");
        customerSearchRequest.setCustomerAddress("Maharashtra");
        customerSearchRequest.setCreatedDate("18/03/2023");
        customerSearchRequest.setCreatedBy("User");
        customerSearchRequest.setUpdatedDate("18/03/2023");
        customerSearchRequest.setUpdatedBy("User");
        List<CustomerDetails> list = new ArrayList<>();
        CustomerDetails customerDetails = new CustomerDetails();
        customerDetails.setCustomerId("1");
        customerDetails.setCustomerName("Customer");
        customerDetails.setCustomerMobileNumber("9876543210");
        customerDetails.setCustomerPanCardNumber("ABCDE1234F");
        customerDetails.setCustomerActiveStatus("False");
        customerDetails.setCustomerAddress("Maharashtra");
        customerDetails.setCreatedDate("18/03/2023");
        customerDetails.setCreatedBy("User");
        customerDetails.setUpdatedDate("18/03/2023");
        customerDetails.setUpdatedBy("User");
        list.add(customerDetails);
        CustomerSearchResponse customerSearchResponse = new CustomerSearchResponse();
        customerSearchResponse.setStatus(SUCCESS);
        customerSearchResponse.setData(list);
        when(cdmService.customerSearch(Mockito.any())).thenReturn(customerSearchResponse);
        HttpResponse<RestApiResponse> restApiResponseHttpResponse = cdmController.getCustomerData(customerSearchRequest);
        assertEquals(HttpStatus.OK, restApiResponseHttpResponse.getStatus());
    }

    @Test
    void testGetCustomerData() {
        CDMModel model1 = new CDMModel();
        model1.setCustomerId(Integer.parseInt("1"));
        model1.setCustomerName("Customer 1");
        model1.setCustomerMobileNumber("9876543210");
        model1.setCustomerPanCardNumber("ABCDE1234F");
        model1.setCustomerActiveStatus("False");
        model1.setCustomerAddress("Maharashtra");
        model1.setCreatedDate("18/03/2023");
        model1.setCreatedBy("User 1");
        model1.setUpdatedDate("18/03/2023");
        model1.setUpdatedBy("User 2");
        CDMModel model2 = new CDMModel();
        model2.setCustomerId(Integer.parseInt("2"));
        model2.setCustomerName("Customer 2");
        model2.setCustomerMobileNumber("9876598765");
        model2.setCustomerPanCardNumber("AABBA1212A");
        model2.setCustomerActiveStatus("True");
        model2.setCustomerAddress("Maharashtra");
        model2.setCreatedDate("18/03/2023");
        model2.setCreatedBy("User 2");
        model2.setUpdatedDate("18/03/2023");
        model2.setUpdatedBy("User 1");
        List<CDMModel> expectedList = Arrays.asList(model1, model2);
        when(cdmServiceImpl.getCustomerData()).thenReturn(expectedList);
        List<CDMModel> actualList = cdmController.getAllCustomerData();
        assertEquals(expectedList.size(), actualList.size());
        assertEquals(expectedList.get(0).getCustomerId(), actualList.get(0).getCustomerId());
        assertEquals(expectedList.get(0).getCustomerName(), actualList.get(0).getCustomerName());
        assertEquals(expectedList.get(0).getCustomerMobileNumber(), actualList.get(0).getCustomerMobileNumber());
        assertEquals(expectedList.get(0).getCustomerPanCardNumber(), actualList.get(0).getCustomerPanCardNumber());
        assertEquals(expectedList.get(0).getCustomerActiveStatus(), actualList.get(0).getCustomerActiveStatus());
        assertEquals(expectedList.get(0).getCustomerAddress(), actualList.get(0).getCustomerAddress());
        assertEquals(expectedList.get(0).getCreatedDate(), actualList.get(0).getCreatedDate());
        assertEquals(expectedList.get(0).getCreatedBy(), actualList.get(0).getCreatedBy());
        assertEquals(expectedList.get(0).getUpdatedDate(), actualList.get(0).getUpdatedDate());
        assertEquals(expectedList.get(0).getUpdatedBy(), actualList.get(0).getUpdatedBy());
        assertEquals(expectedList.get(1).getCustomerId(), actualList.get(1).getCustomerId());
        assertEquals(expectedList.get(1).getCustomerName(), actualList.get(1).getCustomerName());
        assertEquals(expectedList.get(1).getCustomerMobileNumber(), actualList.get(1).getCustomerMobileNumber());
        assertEquals(expectedList.get(1).getCustomerPanCardNumber(), actualList.get(1).getCustomerPanCardNumber());
        assertEquals(expectedList.get(1).getCustomerActiveStatus(), actualList.get(1).getCustomerActiveStatus());
        assertEquals(expectedList.get(1).getCustomerAddress(), actualList.get(1).getCustomerAddress());
        assertEquals(expectedList.get(1).getCreatedDate(), actualList.get(1).getCreatedDate());
        assertEquals(expectedList.get(1).getCreatedBy(), actualList.get(1).getCreatedBy());
        assertEquals(expectedList.get(1).getUpdatedDate(), actualList.get(1).getUpdatedDate());
        assertEquals(expectedList.get(1).getUpdatedBy(), actualList.get(1).getUpdatedBy());
    }

    @ParameterizedTest
    @CsvSource({", 9876543210, ABCDE1234F, False, Maharashtra, 18/03/2023, User, 18/03/2023, User", "Customer, , ABCDE1234F, False, Maharashtra, 18/03/2023, User, 18/03/2023, User", "Customer, 987654321O, ABCDE1234F, False, Maharashtra, 18/03/2023, User, 18/03/2023, User", "Customer, 987654321O, , False, Maharashtra, 18/03/2023, User, 18/03/2023, User", "Customer, 987654321O, ABCDE12345, False, Maharashtra, 18/03/2023, User, 18/03/2023, User", "Customer, 987654321O, ABCDE1234F, , Maharashtra, 18/03/2023, User, 18/03/2023, User", "Customer, 987654321O, ABCDE1234F, No, Maharashtra, 18/03/2023, User, 18/03/2023, User", "Customer, 987654321O, ABCDE1234F, No, , 18/03/2023, User, 18/03/2023, User", "Customer, 987654321O, ABCDE1234F, No, Maharashtra, 18/03/2023, User, , User", "Customer, 987654321O, ABCDE1234F, No, Maharashtra, 18/03/2023, User, 18/03/2023, ",})
    void updateCustomerWhenInputIsInvalid(String name, String mobileNumber, String panCardNumber, String activeStatus, String address, String createdDate, String createdBy, String updatedDate, String updatedBy) {
        CDMModel cdmModel = new CDMModel();
        cdmModel.setCustomerName(name);
        cdmModel.setCustomerMobileNumber(mobileNumber);
        cdmModel.setCustomerPanCardNumber(panCardNumber);
        cdmModel.setCustomerActiveStatus(activeStatus);
        cdmModel.setCustomerAddress(address);
        cdmModel.setCreatedDate(createdDate);
        cdmModel.setCreatedBy(createdBy);
        cdmModel.setUpdatedDate(updatedDate);
        cdmModel.setUpdatedBy(updatedBy);
        HttpResponse<RestApiResponse> response = cdmController.updateCustomerData("1", cdmModel);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatus());
    }

    @Test
    void updateCustomerDataWithExistingMobileNumberReturnsBadRequest() {
        CDMModel cdmModel = new CDMModel();
        cdmModel.setCustomerName("Customer");
        cdmModel.setCustomerMobileNumber("9876543210");
        cdmModel.setCustomerPanCardNumber("ABCDE1234F");
        cdmModel.setCustomerActiveStatus("False");
        cdmModel.setCustomerAddress("Maharashtra");
        cdmModel.setCreatedDate("18/03/2023");
        cdmModel.setCreatedBy("User");
        cdmModel.setUpdatedDate("18/03/2023");
        cdmModel.setUpdatedBy("User");
        when(cdmRepository.findByCustomerMobileNumberIgnoreCaseAndCustomerIdNot(cdmModel.getCustomerMobileNumber(), Integer.parseInt("1"))).thenReturn(java.util.Optional.of(cdmModel));
        HttpResponse<RestApiResponse> response = cdmController.updateCustomerData("1", cdmModel);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatus());
    }

    @Test
    void updateCustomerDataWithExistingPanCardNumberReturnsBadRequest() {
        CDMModel cdmModel = new CDMModel();
        cdmModel.setCustomerName("Customer");
        cdmModel.setCustomerMobileNumber("9876543210");
        cdmModel.setCustomerPanCardNumber("ABCDE1234F");
        cdmModel.setCustomerActiveStatus("False");
        cdmModel.setCustomerAddress("Maharashtra");
        cdmModel.setCreatedDate("18/03/2023");
        cdmModel.setCreatedBy("User");
        cdmModel.setUpdatedDate("18/03/2023");
        cdmModel.setUpdatedBy("User");
        when(cdmRepository.findByCustomerPanCardNumberIgnoreCaseAndCustomerIdNot(cdmModel.getCustomerPanCardNumber(), Integer.parseInt("1"))).thenReturn(java.util.Optional.of(cdmModel));
        HttpResponse<RestApiResponse> response = cdmController.updateCustomerData("1", cdmModel);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatus());
    }

    @Test
    void updateCustomerDataWhenSuccess() {
        CDMModel cdmModel = new CDMModel();
        cdmModel.setCustomerName("Customer");
        cdmModel.setCustomerMobileNumber("9876543210");
        cdmModel.setCustomerPanCardNumber("ABCDE1234F");
        cdmModel.setCustomerActiveStatus("False");
        cdmModel.setCustomerAddress("Maharashtra");
        cdmModel.setCreatedDate("18/03/2023");
        cdmModel.setCreatedBy("User");
        cdmModel.setUpdatedDate("18/03/2023");
        cdmModel.setUpdatedBy("User");
        HttpResponse<RestApiResponse> response = cdmController.updateCustomerData("1", cdmModel);
        assertEquals(HttpStatus.OK, response.getStatus());
    }

    @Test
    void deleteCustomerDataWhenCustomerDoesNotExist() {
        String id = "1";
        when(cdmRepository.existsById(Integer.parseInt(id))).thenReturn(false);
        HttpResponse<RestApiResponse> response = cdmController.deleteCustomerData(id);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatus());
    }

    @Test
    void deleteCustomerDataWhenCustomerExists() {
        String id = "1";
        when(cdmRepository.existsById(Integer.parseInt(id))).thenReturn(true);
        HttpResponse<RestApiResponse> response = cdmController.deleteCustomerData(id);
        assertEquals(HttpStatus.OK, response.getStatus());
        verify(cdmServiceImpl, times(1)).deleteCustomerData(id);
    }
}