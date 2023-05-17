package com.dm.customer.repository;

import com.dm.customer.model.CDMModel;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * CDMRepository Interface which extends JpaRepository
 */
@Repository
public interface CDMRepository extends JpaRepository<CDMModel, Integer> {

    /**
     * Used in Customer Data Search filter by searching with customer id
     * @param customerId
     * @return
     */
    List<CDMModel> findByCustomerId(int customerId);

    /**
     * Used in Customer Data Search filter by searching with customer name
     * @param customerName
     * @return
     */
    List<CDMModel> findByCustomerName(String customerName);

    /**
     * Used in Customer Data Search filter by searching with customer mobile number
     * @param customerMobileNumber
     * @return
     */
    List<CDMModel> findByCustomerMobileNumber(String customerMobileNumber);

    /**
     * Used in Customer Data Search filter by searching with customer pan card number
     * @param customerPanCardNumber
     * @return
     */
    List<CDMModel> findByCustomerPanCardNumber(String customerPanCardNumber);

    /**
     * Used in Customer Data Search filter by searching with customer active status
     * @param customerActiveStatus
     * @return
     */
    List<CDMModel> findByCustomerActiveStatus(String customerActiveStatus);

    /**
     * Used in Customer Data Search filter by searching with customer address
     * @param customerAddress
     * @return
     */
    List<CDMModel> findByCustomerAddress(String customerAddress);

    /**
     * Used in Customer Data Search filter by searching with created date
     * @param createdDate
     * @return
     */
    List<CDMModel> findByCreatedDate(String createdDate);

    /**
     * Used in Customer Data Search filter by searching with created by
     * @param createdBy
     * @return
     */
    List<CDMModel> findByCreatedBy(String createdBy);

    /**
     * Used in Customer Data Search filter by searching with updated date
     * @param updatedDate
     * @return
     */
    List<CDMModel> findByUpdatedDate(String updatedDate);

    /**
     * Used in Customer Data Search filter by searching with updated by
     * @param updatedBy
     * @return
     */
    List<CDMModel> findByUpdatedBy(String updatedBy);

    /**
     * Check if a given mobile number already exists in the database.
     * @param mobileNumber The mobile number to check.
     * @return Optional of CDMModel representing the customer with the given mobile number, or empty if not found.
     */
    Optional<CDMModel> findByCustomerMobileNumberIgnoreCase(String mobileNumber);

    /**
     * Check if a given mobile number already exists in the database except current data updating
     * @param mobileNumber The mobile number to check.
     * @return Optional of CDMModel representing the customer with the given mobile number, or empty if not found.
     */
    Optional<CDMModel> findByCustomerMobileNumberIgnoreCaseAndCustomerIdNot(String mobileNumber, int id);

    /**
     * Check if a given pan card number already exists in the database.
     * @param panCardNumber The pan card number to check.
     * @return Optional of CDMModel representing the customer with the given pan card number, or empty if not found.
     */
    Optional<CDMModel> findByCustomerPanCardNumberIgnoreCase(String panCardNumber);

    /**
     * Check if a given pan card number already exists in the database except current data updating
     * @param panCardNumber The pan card number to check.
     * @return Optional of CDMModel representing the customer with the given pan card number, or empty if not found.
     */
    Optional<CDMModel> findByCustomerPanCardNumberIgnoreCaseAndCustomerIdNot(String panCardNumber, int id);
}