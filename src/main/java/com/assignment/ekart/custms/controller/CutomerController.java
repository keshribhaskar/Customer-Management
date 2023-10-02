package com.assignment.ekart.custms.controller;

import com.assignment.ekart.custms.model.CustomerDetails;
import com.assignment.ekart.custms.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@Slf4j
@RestController
@RequestMapping(value = "/customers")
public class CutomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping(value = "/getCustomers")
    public ResponseEntity<Integer> authenticateCustomer() throws Exception {
        return null;
    }

    @GetMapping(value = "/getCustomers/{id}")
    public ResponseEntity<Integer> authenticateCustomer(@PathVariable Long id) throws Exception {
        return null;
    }

    @PostMapping(value = "/addCustomer")
    public ResponseEntity<String> addCustomer(@Valid @RequestBody CustomerDetails customer) throws Exception {

        log.info("CUSTOMER TRYING TO REGISTER. CUSTOMER EMAIL ID: " + customer.getEmailId());
        String registeredWithEmailID = customerService.addNewCustomer(customer);
//        registeredWithEmailID = environment.getProperty("CustomerAPI.CUSTOMER_REGISTRATION_SUCCESS")
//                + registeredWithEmailID;
        return new ResponseEntity<>(registeredWithEmailID, HttpStatus.OK);
    }

}

