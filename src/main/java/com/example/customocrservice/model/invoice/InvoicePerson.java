package com.example.customocrservice.model.invoice;

import lombok.Data;

@Data
public class InvoicePerson {
    private String name;
    private String ICO;
    private String DIC;
    private Address address;
}
