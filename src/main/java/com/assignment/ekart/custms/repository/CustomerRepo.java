package com.assignment.ekart.custms.repository;

import com.assignment.ekart.custms.entity.CustomerDetailsEntity;
import com.assignment.ekart.custms.model.CustomerDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepo extends JpaRepository<CustomerDetailsEntity,Long> {
    List<CustomerDetailsEntity> findByPhoneNumber(String phoneNumber);
    void deleteByPhoneNumber(String phoneNumber);

}
