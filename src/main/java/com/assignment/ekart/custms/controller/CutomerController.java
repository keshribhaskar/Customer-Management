package com.assignment.ekart.custms.controller;

import com.assignment.ekart.custms.model.CartProductDetails;
import com.assignment.ekart.custms.model.CustomerCartDetails;
import com.assignment.ekart.custms.model.CustomerDetails;
import com.assignment.ekart.custms.model.ProductDetails;
import com.assignment.ekart.custms.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.constraints.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@CrossOrigin
@Validated
@RestController
@RequestMapping(value = "/customers")
public class CutomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private RestTemplate template;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${application.product.name}")
    private String productApp;

    @Value("${application.kart.name}")
    private String kartApp;

    @Value("${application.customer.name}")
    private String customerApp;

    @GetMapping(value = "/getCustomers")
    public ResponseEntity<List<CustomerDetails>> getCustomer() throws Exception {
        List<CustomerDetails> registeredCustomer = customerService.getCustomer();
        return new ResponseEntity<>(registeredCustomer, HttpStatus.OK);
    }

    @PostMapping(value = "/addCustomers")
    public ResponseEntity<String> addCustomer(@Valid @RequestBody CustomerDetails customer) throws Exception {

        log.info("CUSTOMER TRYING TO REGISTER. CUSTOMER EMAIL ID: " + customer.getEmailId());
        String registeredWithEmailID = customerService.addNewCustomer(customer);
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

        CustomerDetails customerDetails = customerService.getCustomerByEmailId(customerCartDetails.getCustomerEmailId());
        Set<CartProductDetails> cartProductDetails = new HashSet<>();
        for (CartProductDetails cartProductDetail : customerCartDetails.getCartProducts()) {

            ProductDetails productDetails = template.getForEntity("http://"+productApp+"/productApi/product/"
                    +cartProductDetail.getProduct().getProductId(), ProductDetails.class).getBody();

            cartProductDetail.setProduct(productDetails);
            cartProductDetails.add(cartProductDetail);
        }

        customerCartDetails.setCartProducts(cartProductDetails);
        String cartDet = objectMapper.writeValueAsString(customerCartDetails);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<String>(cartDet, headers);

        return template.postForEntity("http://"+ kartApp + "/kartApi/products", request, String.class);
    }

}

