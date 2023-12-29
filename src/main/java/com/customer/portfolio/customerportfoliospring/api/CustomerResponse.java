package com.customer.portfolio.customerportfoliospring.api;

import java.time.LocalDateTime;

public record CustomerResponse(
    Long id,
    String cnpj, 
    String name, 
    String longitude,
    String latitude, 
    LocalDateTime createdAt, 
    LocalDateTime updatedAt
    ) {
    
}
