package com.assignment.ekart.custms.repository;

import com.assignment.ekart.custms.entity.CustomerDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<CustomerDetailsEntity,Long> {
}
