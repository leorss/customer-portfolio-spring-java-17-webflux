package com.customer.portfolio.customerportfoliospring.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.validation.constraints.NotBlank;

public record Customer(
    @Id Long id, 
    @NotBlank String cnpj,
    @NotBlank String name,
    @NotBlank String longitude,
    @NotBlank String latitude,
    @CreatedDate LocalDateTime createdAt,
    @LastModifiedDate LocalDateTime updatedAt
) {

}
