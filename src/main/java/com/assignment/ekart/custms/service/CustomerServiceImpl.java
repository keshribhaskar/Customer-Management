package com.assignment.ekart.custms.service;


import com.assignment.ekart.custms.entity.CustomerDetailsEntity;
import com.assignment.ekart.custms.model.CustomerDetails;
import com.assignment.ekart.custms.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    CustomerRepo customerRepo;

    public String addNewCustomer(CustomerDetails customer) {
        try{
            CustomerDetailsEntity customerDetailsEntity = CustomerDetailsEntity.builder()
                    .name(customer.getName())
                    .address(customer.getAddress())
                    .phoneNumber(customer.getPhoneNumber())
                    .emailId(customer.getEmailId())
                    .build();
            customerRepo.save(customerDetailsEntity);
            return "Success";
        }catch (Exception e){
            return "Failed";
        }
    }
}
