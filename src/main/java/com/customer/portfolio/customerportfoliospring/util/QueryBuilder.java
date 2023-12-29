package com.customer.portfolio.customerportfoliospring.util;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import com.customer.portfolio.customerportfoliospring.domain.Customer;

public class QueryBuilder {
  private QueryBuilder() {
  }

  public static Example<Customer> makeQuery(Customer customer) {
    ExampleMatcher exampleMatcher = ExampleMatcher.matchingAll().withIgnoreCase().withIgnoreNullValues();
    return Example.of(customer, exampleMatcher);
  }
}
