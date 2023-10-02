package com.assignment.ekart.custms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CustomerDetails")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDetailsEntity {
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String customerId;
    @Column
    private String name;
    @Column
    private String emailId;
    @Column
    private String phoneNumber;
    @Column
    private String address;

}
