package com.codemen.codemenrest.entities.dto;

import com.codemen.codemenrest.entities.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserDto {
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Username cannot be blank")
    private String username;

    @NotBlank(message = "Email cannot be blank")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", flags = Pattern.Flag.CASE_INSENSITIVE, message="Email is invalid" )
    private String email;

    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Invalid phone number")
    private String phone;

    private String website;


    @Valid
    private CompanyDto company;

    @Valid
    private AddressDto address;

    public User toUser() {
        User result = new User()
                .setName(name)
                .setUsername(username)
                .setEmail(email.toLowerCase())
                .setPhone(phone)
                .setWebsite(website);
        // need this to allow company to be set to null
        if (company != null) {
            result.setCompany(company.toCompany());
        }

        if (address != null) {
            result.setAddress(address.toAddress());
        }
        return result;
    }
}