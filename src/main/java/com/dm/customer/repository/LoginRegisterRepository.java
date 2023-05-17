package com.dm.customer.repository;

import com.dm.customer.model.LoginRegisterModel;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface LoginRegisterRepository extends JpaRepository<LoginRegisterModel, Integer> {

    /**
     * Used for finding the email address in the database
     * @param userEmail
     * @return
     */
    Optional<LoginRegisterModel> findByUserEmail(String userEmail);

    boolean existsByUserEmail(String userEmail);
}
