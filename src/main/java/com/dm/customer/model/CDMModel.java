package com.dm.customer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * CDMModel model class
 */
@Entity
@Table(name = "customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CDMModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private int customerId;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_mobile_number")
    private String customerMobileNumber;

    @Column(name = "customer_pan_card_number")
    private String customerPanCardNumber;

    @Column(name = "customer_active_status")
    private String customerActiveStatus;

    @Column(name = "customer_address")
    private String customerAddress;

    @Column(name = "created_date")
    private String createdDate;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_date")
    private String updatedDate;

    @Column(name = "updated_by")
    private String updatedBy;
}
