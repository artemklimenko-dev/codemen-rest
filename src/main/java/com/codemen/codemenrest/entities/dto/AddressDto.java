package com.codemen.codemenrest.entities.dto;

import com.codemen.codemenrest.entities.Address;
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