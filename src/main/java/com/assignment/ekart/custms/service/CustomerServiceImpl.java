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
import java.util.Optional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepo customerRepo;

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
    public CustomerDetails getCustomerByEmailId(String emailId) throws Exception {
        CustomerDetails cd = new CustomerDetails();
        CustomerDetailsEntity customer = null;
        Optional<CustomerDetailsEntity> cust = customerRepo.findByEmailId(emailId.toLowerCase());
        if(cust.isPresent()){
            customer = cust.get();
        }else{
            throw new Exception("CUSTOMER NOT FOUND");
        }
        cd.setEmailId(customer.getEmailId());
        cd.setName(customer.getName());
        cd.setAddress(customer.getAddress());
        cd.setPhoneNumber(customer.getPhoneNumber());
        return cd;
    }
}
