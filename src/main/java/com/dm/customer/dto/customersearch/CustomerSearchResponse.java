package com.dm.customer.dto.customersearch;

import com.dm.customer.dto.CustomerDetails;
import com.dm.customer.exception.RestApiResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Customer search response model class
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerSearchResponse implements RestApiResponse {

    private String status;
    private List<CustomerDetails> data;
}
