package com.assignment.ekart.custms.service;

import com.assignment.ekart.custms.entity.CustomerDetailsEntity;
import com.assignment.ekart.custms.model.CustomerDetails;

import java.util.List;

public interface CustomerService {
    public String addNewCustomer(CustomerDetails customer);
    public String deleteCustomer(String phoneNumber);

    public List<CustomerDetails> getCustomer();

    public CustomerDetails getCustomerByEmailId(String emailId) throws Exception;
}
