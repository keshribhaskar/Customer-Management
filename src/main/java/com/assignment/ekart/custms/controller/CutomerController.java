package com.assignment.ekart.custms.controller;

import com.assignment.ekart.custms.entity.CustomerDetailsEntity;
import com.assignment.ekart.custms.model.CartProductDetails;
import com.assignment.ekart.custms.model.CustomerCartDetails;
import com.assignment.ekart.custms.model.CustomerDetails;
import com.assignment.ekart.custms.model.ProductDetails;
import com.assignment.ekart.custms.service.CustomerService;
import jakarta.validation.constraints.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Validated
@RestController
@RequestMapping(value = "/customers")
public class CutomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    RestTemplate template;

    @GetMapping(value = "/getCustomers")
    public ResponseEntity<List<CustomerDetails>> getCustomer() throws Exception {
        List<CustomerDetails> registeredCustomer = customerService.getCustomer();
        return new ResponseEntity<>(registeredCustomer, HttpStatus.OK);
    }

    @GetMapping(value = "/getCustomers/{id}")
    public ResponseEntity<Integer> getCustomerById(@PathVariable Long id) throws Exception {
        return null;
    }

    @PostMapping(value = "/addCustomers")
    public ResponseEntity<String> addCustomer(@Valid @RequestBody CustomerDetails customer) throws Exception {

        log.info("CUSTOMER TRYING TO REGISTER. CUSTOMER EMAIL ID: " + customer.getEmailId());
        String registeredWithEmailID = customerService.addNewCustomer(customer);
//        registeredWithEmailID = environment.getProperty("CustomerAPI.CUSTOMER_REGISTRATION_SUCCESS")
//                + registeredWithEmailID;
        return new ResponseEntity<>(registeredWithEmailID, HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteCustomers/{ph_no}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("ph_no") @NotBlank @Size(max = 10,min=10)
                                                     @Pattern(regexp = "[0-9]+", message = "{\"Invalid phone number\"}")
                                                     String ph_no) throws Exception {

        String registeredWithEmailID = customerService.deleteCustomer(ph_no);
        return new ResponseEntity<>(registeredWithEmailID, HttpStatus.OK);
    }

    @PostMapping(value = "/ekart/add-product")
    public ResponseEntity<String> addProductToCart(@Valid @RequestBody CustomerCartDetails customerCartDetails)
            throws Exception {

        customerService.getCustomerByEmailId(customerCartDetails.getCustomerEmailId());
        Set<CartProductDetails> cartProductDetails = new HashSet<>();
        for (CartProductDetails cartProductDetail : customerCartDetails.getCartProducts()) {

            ProductDetails productDetails = template.getForEntity("http://localhost:8082" + "/products"
                    +cartProductDetail.getProduct().getProductId(), ProductDetails.class).getBody();
//            cartProductDTO.setProduct(productDTO);
            // We are calling the product API using hard-coded URI
            // Replace this call with the appropriate MS name
            // Product API is upscaled (available in 2 numbers). Hence, use load balanced
            // template to make call to the Product API
        }
        // We are calling the Cart API using hard-coded URI
        // Replace this call with the appropriate MS name
        // CartMS is not an upscaled one (available in 1 number) , still load-balanced
        // rest template should be used here to make the call
        // Don't create and autowire a normal rest template because load balanced
        // template is already in config file
//        customerCartDTO.setCartProducts(cartProductDTOS);
        return template.postForEntity("http://localhost:8080" + "/kartApi/products",
                customerCartDetails, String.class);
    }

}

