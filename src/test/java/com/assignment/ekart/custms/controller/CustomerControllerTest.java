package com.assignment.ekart.custms.controller;

import com.assignment.ekart.custms.CustmsApplication;
import com.assignment.ekart.custms.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;


//@RunWith(SpringRunner.class)
@WebMvcTest(CustmsApplication.class)
@AutoConfigureMockMvc
public class CustomerControllerTest {

    @Autowired
    private MockMvc mvc;

    @Mock
    private CustomerService customerService;

//    @Mock
//    private RestTemplate restTemplate;

    @Test
    public void getCustomerTest() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .get("/customers/getCustomers")
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
//        String content = mvcResult.getResponse().getContentAsString();
//        CustomerDetails[] customerList = super.mapFromJson(content, CustomerDetails[].class);
//        assertTrue(customerList.length == 1);

    }

//    @Test
//    public void addCustomerTest() throws Exception {
//        CustomerDetails customer = new CustomerDetails();
//        customer.setName("John");
//        customer.setAddress("USA");
//        customer.setPhoneNumber("9854869784");
//        customer.setEmailId("jus@gmail.com");
//
//        ResponseEntity<String> responseEntity = customerController.addCustomer(customer);
//        HttpStatusCode expected = OK;
//        HttpStatusCode actual = responseEntity.getStatusCode();
//        Assertions.assertEquals(expected,actual);
//    }
//    @Test
//    public void deleteCustomerTest() throws Exception {
//        String phone = "9840595543";
//        ResponseEntity<String> responseEntity = customerController.deleteCustomer(phone);
//        HttpStatusCode expected = OK;
//        HttpStatusCode actual = responseEntity.getStatusCode();
//        Assertions.assertEquals(expected,actual);
//    }
//
//    @Test
//    public void addProductToCartTest() throws Exception {
//
//        CustomerDetails customer = new CustomerDetails();
//        customer.setName("John");
//        customer.setAddress("USA");
//        customer.setPhoneNumber("9854869784");
//        customer.setEmailId("test@gmail.com");
//        when(customerService.getCustomerByEmailId("test@gmail.com")).thenReturn(customer);
//        Set<CartProductDetails> cartProducts = new HashSet<>();
//        CartProductDetails obj = new CartProductDetails();
//        ProductDetails productDetails = new ProductDetails();
//        productDetails.setProductId(1);
//        obj.setProduct(productDetails);
//        cartProducts.add(obj);
//    }
}
