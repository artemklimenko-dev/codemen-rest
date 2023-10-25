package com.codemen.codemenrest.entity.dto;

import com.codemen.codemenrest.entity.Company;
import com.codemen.codemenrest.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;


@Data
public class CompanyDto {
    @NotBlank(message = "Company name cannot be blank")
    private String name;

    private String catchPhrase;

    private String bs;

    public Company toCompany() {
        return new Company()
                .setName(name)
                .setCatchPhrase(catchPhrase)
                .setBs(bs);
    }
}