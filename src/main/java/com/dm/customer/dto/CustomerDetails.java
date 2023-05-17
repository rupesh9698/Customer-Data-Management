package com.dm.customer.dto;

import lombok.Data;

/**
 * Customer details class used in customer search response
 */
@Data
public class CustomerDetails {

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
