package com.codemen.codemenrest.entities.dto;

import com.codemen.codemenrest.entities.Company;
import jakarta.validation.constraints.NotBlank;
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