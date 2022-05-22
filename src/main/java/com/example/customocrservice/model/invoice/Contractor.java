package com.example.customocrservice.model.invoice;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Contractor extends InvoicePerson {
    private String name;
    private String ICO;
    private String DIC;
    private Address address;
}
