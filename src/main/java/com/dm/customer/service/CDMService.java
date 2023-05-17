package com.dm.customer.service;

import com.dm.customer.dto.customersearch.CustomerSearchRequest;
import com.dm.customer.dto.customersearch.CustomerSearchResponse;

/**
 * CDMService interface for CDMServiceImpl
 */
public interface CDMService {
    public CustomerSearchResponse customerSearch(CustomerSearchRequest customerSearchRequest);
}
