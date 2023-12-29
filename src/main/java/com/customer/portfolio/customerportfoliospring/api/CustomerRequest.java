package com.customer.portfolio.customerportfoliospring.api;

import jakarta.validation.constraints.NotBlank;

public record CustomerRequest(
    @NotBlank String cnpj, 
    @NotBlank String name, 
    @NotBlank String longitude,
    @NotBlank String latitude
    ) {

}
