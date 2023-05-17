package com.dm.customer.dto.customersearch;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Customer search request model class
 */
@Data
@NoArgsConstructor
public class CustomerSearchRequest {
    private String customerId;
    private String customerName;
    private String customerMobileNumber;
    private String customerPanCardNumber;
    private String customerActiveStatus;
    private String customerAddress;
    private String createdDate;
    private String createdBy;
    private String updatedDate;
    private String updatedBy;
}