package com.assignment.ekart.custms.controller;

import com.assignment.ekart.custms.model.CustomerDetails;
import com.assignment.ekart.custms.service.CustomerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.OK;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {

    @InjectMocks
    CutomerController customerController;
    @Mock
    private CustomerService customerService;
    @Mock
    private RestTemplate template;

    @Test
    public void getCustomerTest() throws Exception {
        List<CustomerDetails> customerDetails = new ArrayList<>();
        CustomerDetails customer = new CustomerDetails();
        customer.setName("John");
        customer.setAddress("USA");
        customer.setPhoneNumber("9854869784");
        customer.setEmailId("jus@gmail.com");
        customerDetails.add(customer);

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(customerService.getCustomer()).thenReturn(customerDetails);

        HttpStatusCode expected = OK;
        ResponseEntity<List<CustomerDetails>> responseEntity = customerController.getCustomer();
        HttpStatusCode actual = responseEntity.getStatusCode();
        Assertions.assertEquals(expected,actual);
    }

    @Test
    public void addCustomerTest() throws Exception {
        CustomerDetails customer = new CustomerDetails();
        customer.setName("John");
        customer.setAddress("USA");
        customer.setPhoneNumber("9854869784");
        customer.setEmailId("jus@gmail.com");

//        MockHttpServletRequest request = new MockHttpServletRequest();
//        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        ResponseEntity<String> responseEntity = customerController.addCustomer(customer);
        HttpStatusCode expected = OK;
        HttpStatusCode actual = responseEntity.getStatusCode();
        Assertions.assertEquals(expected,actual);
    }

}
