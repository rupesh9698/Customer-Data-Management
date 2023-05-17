package com.dm.customer.service;

import com.dm.customer.dto.customersearch.CustomerSearchRequest;
import com.dm.customer.dto.customersearch.CustomerSearchResponse;
import com.dm.customer.service.impl.CDMServiceImpl;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
class CDMServiceImplTest {

    @Inject
    CDMServiceImpl cdmServiceImpl;

    @Test
    void searchCustomerByCustomerId_success() {
        CustomerSearchRequest customerSearchRequest = new CustomerSearchRequest();
        customerSearchRequest.setCustomerId("1");

        CustomerSearchResponse customerSearchResponse = cdmServiceImpl.customerSearch(customerSearchRequest);
        assertNotNull(customerSearchResponse);
        assertNotNull(customerSearchResponse.getData());

        assertTrue(customerSearchResponse.getData().stream().anyMatch(response -> "1".equals(response.getCustomerId())));
    }

    @Test
    void searchCustomerByCustomerName_success() {
        CustomerSearchRequest customerSearchRequest = new CustomerSearchRequest();
        customerSearchRequest.setCustomerName("Rupesh Bagde");

        CustomerSearchResponse customerSearchResponse = cdmServiceImpl.customerSearch(customerSearchRequest);
        assertNotNull(customerSearchResponse);
        assertNotNull(customerSearchResponse.getData());
        assertTrue(customerSearchResponse.getData().stream().anyMatch(response -> "Rupesh Bagde".equals(response.getCustomerName())));
    }

    @Test
    void searchCustomerByCustomerMobileNumber_success() {
        CustomerSearchRequest customerSearchRequest = new CustomerSearchRequest();
        customerSearchRequest.setCustomerMobileNumber("9876543210");

        CustomerSearchResponse customerSearchResponse = cdmServiceImpl.customerSearch(customerSearchRequest);
        assertNotNull(customerSearchResponse);
        assertNotNull(customerSearchResponse.getData());
        assertTrue(customerSearchResponse.getData().stream().anyMatch(response -> "9876543210".equals(response.getCustomerMobileNumber())));
    }

    @Test
    void searchCustomerByCustomerPanCardNumber_success() {
        CustomerSearchRequest customerSearchRequest = new CustomerSearchRequest();
        customerSearchRequest.setCustomerPanCardNumber("ABCDE1234F");

        CustomerSearchResponse customerSearchResponse = cdmServiceImpl.customerSearch(customerSearchRequest);
        assertNotNull(customerSearchResponse);
        assertNotNull(customerSearchResponse.getData());
        assertTrue(customerSearchResponse.getData().stream().anyMatch(response -> "ABCDE1234F".equals(response.getCustomerPanCardNumber())));
    }

    @Test
    void searchCustomerByCustomerActiveStatus_success() {
        CustomerSearchRequest customerSearchRequest = new CustomerSearchRequest();
        customerSearchRequest.setCustomerActiveStatus("False");

        CustomerSearchResponse customerSearchResponse = cdmServiceImpl.customerSearch(customerSearchRequest);
        assertNotNull(customerSearchResponse);
        assertNotNull(customerSearchResponse.getData());
        assertTrue(customerSearchResponse.getData().stream().anyMatch(response -> "False".equals(response.getCustomerActiveStatus())));
    }

    @Test
    void searchCustomerByCreatedDate_success() {
        CustomerSearchRequest customerSearchRequest = new CustomerSearchRequest();
        customerSearchRequest.setCreatedDate("18/03/2023");

        CustomerSearchResponse customerSearchResponse = cdmServiceImpl.customerSearch(customerSearchRequest);
        assertNotNull(customerSearchResponse);
        assertNotNull(customerSearchResponse.getData());
        assertTrue(customerSearchResponse.getData().stream().anyMatch(response -> "18/03/2023".equals(response.getCreatedDate())));
    }

    @Test
    void searchCustomerByCreatedBy_success() {
        CustomerSearchRequest customerSearchRequest = new CustomerSearchRequest();
        customerSearchRequest.setCreatedBy("User");

        CustomerSearchResponse customerSearchResponse = cdmServiceImpl.customerSearch(customerSearchRequest);
        assertNotNull(customerSearchResponse);
        assertNotNull(customerSearchResponse.getData());
        assertTrue(customerSearchResponse.getData().stream().anyMatch(response -> "User".equals(response.getCreatedBy())));
    }

    @Test
    void searchCustomerByUpdatedDateStatus_success() {
        CustomerSearchRequest customerSearchRequest = new CustomerSearchRequest();
        customerSearchRequest.setUpdatedDate("18/03/2023");

        CustomerSearchResponse customerSearchResponse = cdmServiceImpl.customerSearch(customerSearchRequest);
        assertNotNull(customerSearchResponse);
        assertNotNull(customerSearchResponse.getData());
        assertTrue(customerSearchResponse.getData().stream().anyMatch(response -> "18/03/2023".equals(response.getUpdatedDate())));
    }

    @Test
    void searchCustomerByUpdatedBy_success() {
        CustomerSearchRequest customerSearchRequest = new CustomerSearchRequest();
        customerSearchRequest.setUpdatedBy("User");

        CustomerSearchResponse customerSearchResponse = cdmServiceImpl.customerSearch(customerSearchRequest);
        assertNotNull(customerSearchResponse);
        assertNotNull(customerSearchResponse.getData());
        assertTrue(customerSearchResponse.getData().stream().anyMatch(response -> "User".equals(response.getUpdatedBy())));
    }

    @Test
    void searchCustomer_failure_validationFailure() {
        CustomerSearchRequest customerSearchRequest = new CustomerSearchRequest();
        customerSearchRequest.setCustomerId(null);
        customerSearchRequest.setCustomerName(null);
        customerSearchRequest.setCustomerMobileNumber(null);
        customerSearchRequest.setCustomerPanCardNumber(null);
        CustomerSearchResponse response = cdmServiceImpl.customerSearch(customerSearchRequest);
        assertNull(response.getStatus());
        assertNull(response.getData());
    }
}