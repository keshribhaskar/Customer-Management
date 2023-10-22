package com.assignment.ekart.custms.controller;

import com.assignment.ekart.custms.model.CartProductDetails;
import com.assignment.ekart.custms.model.CustomerCartDetails;
import com.assignment.ekart.custms.model.CustomerDetails;
import com.assignment.ekart.custms.model.ProductDetails;
import com.assignment.ekart.custms.service.CustomerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.OK;

@SpringBootTest
public class CustomerControllerTest {

    @Autowired
    private CutomerController customerController;
    @Mock
    private CustomerService customerService;

    @Mock
    private RestTemplate restTemplate;

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
        System.out.println(responseEntity.getBody());
        Assertions.assertEquals(expected,actual);
    }

    @Test
    public void addCustomerTest() throws Exception {
        CustomerDetails customer = new CustomerDetails();
        customer.setName("John");
        customer.setAddress("USA");
        customer.setPhoneNumber("9854869784");
        customer.setEmailId("jus@gmail.com");

        ResponseEntity<String> responseEntity = customerController.addCustomer(customer);
        HttpStatusCode expected = OK;
        HttpStatusCode actual = responseEntity.getStatusCode();
        Assertions.assertEquals(expected,actual);
    }
    @Test
    public void deleteCustomerTest() throws Exception {
        String phone = "9840595543";
        ResponseEntity<String> responseEntity = customerController.deleteCustomer(phone);
        HttpStatusCode expected = OK;
        HttpStatusCode actual = responseEntity.getStatusCode();
        Assertions.assertEquals(expected,actual);
    }

    @Test
    public void addProductToCartTest() throws Exception {

        CustomerDetails customer = new CustomerDetails();
        customer.setName("John");
        customer.setAddress("USA");
        customer.setPhoneNumber("9854869784");
        customer.setEmailId("test@gmail.com");
        when(customerService.getCustomerByEmailId("test@gmail.com")).thenReturn(customer);
        Set<CartProductDetails> cartProducts = new HashSet<>();
        CartProductDetails obj = new CartProductDetails();
        ProductDetails productDetails = new ProductDetails();
        productDetails.setProductId(1);
        obj.setProduct(productDetails);
        cartProducts.add(obj);
//        customerController.addProductToCart(new CustomerCartDetails("test@gmail.com", cartProducts));
    }
}
