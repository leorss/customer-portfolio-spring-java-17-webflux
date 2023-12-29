package com.customer.portfolio.customerportfoliospring.domain;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;

import com.customer.portfolio.customerportfoliospring.api.CustomerRequest;
import com.customer.portfolio.customerportfoliospring.util.QueryBuilder;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class CustomerService {
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public Mono<Customer> create(CustomerRequest customerRequest){
        var customer = new Customer(
            null, 
            customerRequest.cnpj(), 
            customerRequest.name(), 
            customerRequest.longitude(),  
            customerRequest.latitude(),
            null,
            null
        );
        return customerRepository.save(customer);
    }

    public Mono<Customer> edit(Long id, CustomerRequest customerRequest) {
        return customerRepository.findById(id)
            .map(customer -> CustomerMapper.updateCustomerFromDTO(customerRequest, customer))
            .flatMap(customerRepository::save);
      }

      public Mono<Customer> get(Long id) {
        return customerRepository.findById(id);
      }

    public Flux<Customer> list(String name) {
        var customer = new Customer(null, null, name, null, null, null, null);
        Example<Customer> query = QueryBuilder.makeQuery(customer);
        return customerRepository.findAll(query, Sort.by("name").ascending());
    }

    public Mono<Customer> deleteById(Long id) {
        return customerRepository.findById(id)
        .doOnSuccess(c -> customerRepository.delete(c).subscribe());
    }
}
