package com.customer.portfolio.customerportfoliospring.domain;

import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CustomerRepository extends ReactiveCrudRepository<Customer, Long>, ReactiveQueryByExampleExecutor<Customer> {

}
