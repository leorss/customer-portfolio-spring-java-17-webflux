package com.customer.portfolio.customerportfoliospring.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.customer.portfolio.customerportfoliospring.api.CustomerRequest;
import com.customer.portfolio.customerportfoliospring.api.CustomerResponse;
import com.customer.portfolio.customerportfoliospring.domain.CustomerMapper;
import com.customer.portfolio.customerportfoliospring.domain.CustomerService;

import jakarta.validation.Valid;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@RestController
@RequestMapping("/customers")
public class CustomerController {
  @Autowired
  private CustomerService customerService;
  
  @PostMapping
  public ResponseEntity<Mono<CustomerResponse>> create(@Valid @RequestBody CustomerRequest request) {
    var customerResponse = customerService.create(request).map(CustomerMapper::toResponse);
    return ResponseEntity.status(HttpStatus.CREATED).body(customerResponse);
  }

  @PatchMapping("{id}")
  public Mono<CustomerResponse> edit(@PathVariable("id") Long id, @RequestBody CustomerRequest request) {
    return customerService.edit(id, request).map(CustomerMapper::toResponse);
  }

  @GetMapping("{id}")
  public Mono<ResponseEntity<CustomerResponse>> get(@PathVariable("id") Long id) {
    return customerService.get(id)
        .map(customer -> ResponseEntity.ok(CustomerMapper.toResponse(customer)))
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }
  
  @GetMapping
  public Flux<CustomerResponse> list(@RequestParam(required = false) String name) {
    return customerService.list(name).map(CustomerMapper::toResponse);
  }

  @DeleteMapping("{id}")
  public Mono<Void> deleteById(@PathVariable("id") Long id) {
    customerService.deleteById(id);
    return Mono.empty();
  }
}
