package com.dm.customer.service.impl;

import com.dm.customer.dto.CustomerDetails;
import com.dm.customer.dto.customersearch.CustomerSearchRequest;
import com.dm.customer.dto.customersearch.CustomerSearchResponse;
import com.dm.customer.model.CDMModel;
import com.dm.customer.repository.CDMRepository;
import com.dm.customer.service.CDMService;
import com.dm.customer.util.CDMLogger;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.dm.customer.util.CDMUtility.SUCCESS;

/**
 * CDMServiceImpl class which implements CDMService interface
 */
@Singleton
public class CDMServiceImpl implements CDMService {

    final String className = getClass().getSimpleName();
    @Inject
    CDMLogger cdmLogger;
    @Inject
    private CDMRepository cdmRepository;

    /**
     * Method for postCustomerData to save the data in database
     * @param cdmModel
     * @return
     */
    public CDMModel postCustomerData(CDMModel cdmModel) {
        return cdmRepository.save(cdmModel);
    }

    /**
     * Method for getCustomerData to get the all data from database
     * @return
     */
    public List<CDMModel> getCustomerData() {
        return cdmRepository.findAll();
    }

    /**
     * Method for updateCustomerData to update the specific data in database
     * @param id
     * @param cdmModel
     * @return
     */
    public CDMModel updateCustomerData(String id, CDMModel cdmModel) {
        cdmModel.setCustomerId(Integer.valueOf(id));
        return cdmRepository.update(cdmModel);
    }

    /**
     * Method for deleteCustomerData to delete the specific data from database
     * @param id
     */
    public void deleteCustomerData(String id) {
        cdmRepository.deleteById(Integer.valueOf(id));
    }

    /**
     * Method for customerSearch for searching the customer data
     * @param customerSearchRequest
     * @return
     */
    @Override
    public CustomerSearchResponse customerSearch(CustomerSearchRequest customerSearchRequest) {
        CustomerSearchResponse customerSearchResponse = new CustomerSearchResponse();
        final String methodName = "processGetCustomerSearchRequest";
        cdmLogger.logs(className, methodName, "Processing Customer Search Request");

        List<CDMModel> searchResults = null;
        if (Optional.ofNullable(customerSearchRequest.getCustomerId()).isPresent()) {
            searchResults = cdmRepository.findByCustomerId(Integer.valueOf(customerSearchRequest.getCustomerId()));
        } else if (Optional.ofNullable(customerSearchRequest.getCustomerName()).isPresent()) {
            searchResults = cdmRepository.findByCustomerName(customerSearchRequest.getCustomerName());
        } else if (Optional.ofNullable(customerSearchRequest.getCustomerMobileNumber()).isPresent()) {
            searchResults = cdmRepository.findByCustomerMobileNumber(customerSearchRequest.getCustomerMobileNumber());
        } else if (Optional.ofNullable(customerSearchRequest.getCustomerPanCardNumber()).isPresent()) {
            searchResults = cdmRepository.findByCustomerPanCardNumber(customerSearchRequest.getCustomerPanCardNumber());
        } else if (Optional.ofNullable(customerSearchRequest.getCustomerActiveStatus()).isPresent()) {
            searchResults = cdmRepository.findByCustomerActiveStatus(customerSearchRequest.getCustomerActiveStatus());
        }else if (Optional.ofNullable(customerSearchRequest.getCustomerAddress()).isPresent()) {
            searchResults = cdmRepository.findByCustomerAddress(customerSearchRequest.getCustomerAddress());
        }else if (Optional.ofNullable(customerSearchRequest.getCreatedDate()).isPresent()) {
            searchResults = cdmRepository.findByCreatedDate(customerSearchRequest.getCreatedDate());
        }else if (Optional.ofNullable(customerSearchRequest.getCreatedBy()).isPresent()) {
            searchResults = cdmRepository.findByCreatedBy(customerSearchRequest.getCreatedBy());
        }else if (Optional.ofNullable(customerSearchRequest.getUpdatedDate()).isPresent()) {
            searchResults = cdmRepository.findByUpdatedDate(customerSearchRequest.getUpdatedDate());
        }else if (Optional.ofNullable(customerSearchRequest.getUpdatedBy()).isPresent()) {
            searchResults = cdmRepository.findByUpdatedBy(customerSearchRequest.getUpdatedBy());
        }
        if (searchResults != null && !searchResults.isEmpty()) {
            List<CustomerDetails> customerDetailsList = new ArrayList<>();
            for (CDMModel details : searchResults) {
                CustomerDetails customerDetails = new CustomerDetails();
                customerDetails.setCustomerId(String.valueOf(details.getCustomerId()));
                customerDetails.setCustomerName(details.getCustomerName());
                customerDetails.setCustomerMobileNumber(details.getCustomerMobileNumber());
                customerDetails.setCustomerPanCardNumber(details.getCustomerPanCardNumber());
                customerDetails.setCustomerActiveStatus(details.getCustomerActiveStatus());
                customerDetails.setCustomerAddress(details.getCustomerAddress());
                customerDetails.setCreatedDate(details.getCreatedDate());
                customerDetails.setCreatedBy(details.getCreatedBy());
                customerDetails.setUpdatedDate(details.getUpdatedDate());
                customerDetails.setUpdatedBy(details.getUpdatedBy());
                customerDetailsList.add(customerDetails);
            }
            customerSearchResponse.setStatus(SUCCESS);
            customerSearchResponse.setData(customerDetailsList);
        }
        return customerSearchResponse;
    }
}