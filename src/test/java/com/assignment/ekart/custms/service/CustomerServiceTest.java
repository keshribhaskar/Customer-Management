package com.assignment.ekart.custms.service;

import com.assignment.ekart.custms.model.CartProductDetails;
import com.assignment.ekart.custms.model.CustomerCartDetails;
import com.assignment.ekart.custms.model.CustomerDetails;
import com.assignment.ekart.custms.model.ProductDetails;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private ObjectMapper mapper;

    CustomerDetails customer = new CustomerDetails();

    @BeforeAll
    public void setup(){
        customer.setName("John");
        customer.setAddress("USA");
        customer.setPhoneNumber("9854869784");
        customer.setEmailId("jus@gmail.com");
    }
    @Test
    @Order(1)
    public void addNewCustomerSuccessTest(){
        String expected = "Successfully added customer with id: 1";
        String actual = customerService.addNewCustomer(customer);
        Assertions.assertEquals(expected,actual);
    }

    @Test
    public void deleteCustomerSuccessTest(){
        String expected = "Successfully deleted customer with phone number: 9854869784";
        String actual = customerService.deleteCustomer("9854869784");
        Assertions.assertEquals(expected,actual);
    }

    @Test
    @Order(2)
    public void getCustomerSuccessTest() throws JsonProcessingException {
        String expected = "[{\"name\":\"John\",\"emailId\":\"jus@gmail.com\",\"phoneNumber\":\"9854869784\",\"address\":\"USA\"}]";
        List<CustomerDetails> customerDetails = customerService.getCustomer();
        String actual = mapper.writeValueAsString(customerDetails);
        Assertions.assertEquals(expected,actual);
    }

    @Test
    @Order(3)
    public void getCustomerByEmailIdSuccessTest() throws Exception {
        String expected = "{\"name\":\"John\",\"emailId\":\"jus@gmail.com\",\"phoneNumber\":\"9854869784\",\"address\":\"USA\"}";
        CustomerDetails customerData = customerService.getCustomerByEmailId("jus@gmail.com");
        String actual = mapper.writeValueAsString(customerData);
        Assertions.assertEquals(expected,actual);
    }

    @Test
    @Order(4)
    public void addNewCustomerFailedTest(){
        CustomerDetails customer1 = null;
        String expected = "Failed to add customer.";
        String actual = customerService.addNewCustomer(customer1);
        Assertions.assertEquals(expected,actual);
    }

    @Test
    public void deleteCustomerFailedTest(){
        String expected = "Deletion failed phone number not found";
        String actual = customerService.deleteCustomer("9854884");
        Assertions.assertEquals(expected,actual);
    }

    @Test
    public void getCustomerByEmailIdFailedTest() throws Exception {
//        Exception exception = assertThrows(java.lang.Exception.class, () -> {
//            customerService.getCustomerByEmailId("dummy@gmail.com");
//        });
        CustomerDetails errorMsg = customerService.getCustomerByEmailId("dummy@gmail.com");
        String actual = errorMsg.getError();
        String expected = "Customer not found";
//        String actualMessage = exception.getMessage();
//        assertTrue(actualMessage.contains(expectedMessage));
        Assertions.assertEquals(expected,actual);
    }

    @Test
    public void getProductsTest() throws JsonProcessingException {
        String expected = "Customer not found";
        Set<CartProductDetails> cartProducts = new HashSet<>();
        CustomerCartDetails customerCartDetails = new CustomerCartDetails();
        CartProductDetails cartProduct = new CartProductDetails();
        ProductDetails product = new ProductDetails();
        product.setProductId(1);
        cartProduct.setCartProductId(1);
        cartProduct.setQuantity(5);
        cartProduct.setProduct(product);
        customerCartDetails.setCartId(1);
        customerCartDetails.setCustomerEmailId("k@gmail.com");
        cartProducts.add(cartProduct);
        customerCartDetails.setCartProducts(cartProducts);
        ResponseEntity<String> response = customerService.updateProductsToKart(customerCartDetails);
        String actual = response.getBody();
        Assertions.assertEquals(expected,actual);
    }
    @Test
    public void getProductsFailedTest() throws JsonProcessingException {
        String expected = "Customer not found";
        Set<CartProductDetails> cartProducts = new HashSet<>();
        CustomerCartDetails customerCartDetails = new CustomerCartDetails();
        CartProductDetails cartProduct = new CartProductDetails();
        ProductDetails product = new ProductDetails();
        product.setProductId(1);
        cartProduct.setCartProductId(1);
        cartProduct.setQuantity(5);
        customerCartDetails.setCartId(1);
        customerCartDetails.setCustomerEmailId("k@gmail.com");
        cartProducts.add(cartProduct);
        customerCartDetails.setCartProducts(cartProducts);
        ResponseEntity<String> response = customerService.updateProductsToKart(customerCartDetails);
        String actual = response.getBody();
        Assertions.assertEquals(expected,actual);
    }
}
