package com.assignment.ekart.custms.service;


import com.assignment.ekart.custms.entity.CustomerDetailsEntity;
import com.assignment.ekart.custms.model.CustomerDetails;
import com.assignment.ekart.custms.repository.CustomerRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
//@Transactional
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
            Long id = customerDetailsEntity.getCustomerId();
            return "Successfully added customer with id: "+id;
        }catch (Exception e){
            return "Failed to add customer.";
        }
    }

    @Override
    public String deleteCustomer(String phoneNumber) {
        try{
            List<CustomerDetailsEntity> customerData = customerRepo.findByPhoneNumber(phoneNumber);
            if(customerData.isEmpty()){
                throw new SQLException("phone number " + phoneNumber +" not found");
            }else {
                customerRepo.deleteAll(customerData);
            }
//            customerRepo.deleteByPhoneNumber(phoneNumber);
            return "Successfully deleted customer with phone number: "+phoneNumber;
        }catch (Exception e){
            return "Failed to delete customer. Reason: "+e.getMessage();
        }
    }

    @Override
    public List<CustomerDetails> getCustomer() {
        List<CustomerDetailsEntity> cde = customerRepo.findAll();
        CustomerDetails cd = new CustomerDetails();
        List<CustomerDetails> cdl = new ArrayList<>();
        for(CustomerDetailsEntity cden:cde){
            cd.setName(cden.getName());
            cd.setAddress(cden.getAddress());
            cd.setPhoneNumber(cden.getPhoneNumber());
            cd.setEmailId(cden.getEmailId());
            cdl.add(cd);
        }
        return cdl;
    }

    @Override
    public List<CustomerDetails> getCustomerByEmailId(String emailId) {
        return null;
    }

}
