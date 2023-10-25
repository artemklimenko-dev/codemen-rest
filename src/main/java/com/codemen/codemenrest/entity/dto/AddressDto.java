package com.codemen.codemenrest.entity.dto;

import com.codemen.codemenrest.entity.Address;
import com.codemen.codemenrest.entity.Company;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AddressDto {
    @NotBlank(message = "Address street cannot be blank")
    private String street;

    private String suite;

    private String city;

    private String zipcode;
    public Address toAddress() {
        return new Address()
                .setStreet(street)
                .setSuite(suite)
                .setCity(city)
                .setZipcode(zipcode);
    }
}