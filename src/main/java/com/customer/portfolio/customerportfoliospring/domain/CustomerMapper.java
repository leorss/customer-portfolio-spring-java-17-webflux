package com.customer.portfolio.customerportfoliospring.domain;

import org.springframework.util.StringUtils;

import com.customer.portfolio.customerportfoliospring.api.CustomerRequest;
import com.customer.portfolio.customerportfoliospring.api.CustomerResponse;

public class CustomerMapper {
    public static Customer updateCustomerFromDTO(CustomerRequest customerRequest, Customer customer) {

        final String cnpj = StringUtils.hasText(customerRequest.cnpj()) ? customerRequest.cnpj() : customer.cnpj();
        final String name = StringUtils.hasText(customerRequest.name()) ? customerRequest.name() : customer.name();
        final String longitude = StringUtils.hasText(customerRequest.longitude()) ? customerRequest.longitude() : customer.longitude();
        final String latitude = StringUtils.hasText(customerRequest.latitude()) ? customerRequest.latitude() : customer.latitude();

        return new Customer(    
            customer.id(),
            cnpj,
            name,
            longitude,
            latitude,
            customer.createdAt(),
            customer.updatedAt()
            );

    }

    public static CustomerResponse toResponse(Customer customer) {
        return new CustomerResponse(
            customer.id(),
            customer.cnpj(),
            customer.name(), 
            customer.longitude(), 
            customer.latitude(), 
            customer.createdAt(), 
            customer.updatedAt());
      }

}
