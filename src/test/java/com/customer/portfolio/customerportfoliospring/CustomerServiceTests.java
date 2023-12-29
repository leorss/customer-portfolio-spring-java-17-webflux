package com.customer.portfolio.customerportfoliospring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.customer.portfolio.customerportfoliospring.api.CustomerRequest;
import com.customer.portfolio.customerportfoliospring.domain.Customer;
import com.customer.portfolio.customerportfoliospring.domain.CustomerRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Import(TestConfig.class)
public class CustomerServiceTests {
  public static final Customer COMPANY_NAME_A = new Customer(1L, "12345678900001", "Company Name A", "-43.12345", "-22.98765", null, null);

  @Autowired
  WebTestClient webTestClient;

  @Autowired
  CustomerRepository placeRepository;

  @Test
  public void testCreateCustomerSuccess() {
    final String cnpj = "12345678900001";
    final String name = "Valid Name";
    final String longitude = "-43.12345";
    final String latitude = "-22.98765";

    webTestClient
        .post()
        .uri("/customers")
        .bodyValue(
            new CustomerRequest(cnpj, name, longitude, latitude))
        .exchange()
        .expectStatus().isCreated()
        .expectBody()
        // .jsonPath("cnpj").isEqualTo(cnpj)
        .jsonPath("name").isEqualTo(name)
        .jsonPath("longitude").isEqualTo(longitude)
        .jsonPath("latitude").isEqualTo(latitude)
        .jsonPath("createdAt").isNotEmpty()
        .jsonPath("updatedAt").isNotEmpty();
  }

  @Test
  public void testCreateCustomerFailure() {
    final String cnpj = "";
    final String name = "";
    final String longitude = "";
    final String latitude = "";

    webTestClient
        .post()
        .uri("/customers")
        .bodyValue(
            new CustomerRequest(cnpj, name, longitude, latitude))
        .exchange()
        .expectStatus().isBadRequest();
  }

  @Test
  public void testEditCustomerSuccess() {
    final String newCnpj = "0123456789000";
    final String newName = "New Name";
    final String newLongitude = "-13.12345";
    final String newLatitude = "-12.98765";

    // Updates cnpj, name longitude and latitude.
    webTestClient
        .patch()
        .uri("/customers/1")
        .bodyValue(
            new CustomerRequest(newCnpj, newName, newLongitude, newLatitude ))
        .exchange()
        .expectStatus().isOk()
        .expectBody()
        .jsonPath("cnpj").isEqualTo(newCnpj)
        .jsonPath("name").isEqualTo(newName)
        .jsonPath("longitude").isEqualTo(newLongitude)
        .jsonPath("latitude").isEqualTo(newLatitude)
        .jsonPath("createdAt").isNotEmpty()
        .jsonPath("updatedAt").isNotEmpty();

    // Updates only cnpj
    webTestClient
        .patch()
        .uri("/customers/1")
        .bodyValue(
            new CustomerRequest(COMPANY_NAME_A.cnpj(), null, null, null))
        .exchange()
        .expectStatus().isOk()
        .expectBody()
        .jsonPath("cnpj").isEqualTo(COMPANY_NAME_A.cnpj())
        .jsonPath("name").isEqualTo(newName)
        .jsonPath("longitude").isEqualTo(newLongitude)
        .jsonPath("latitude").isEqualTo(newLatitude)
        .jsonPath("createdAt").isNotEmpty()
        .jsonPath("updatedAt").isNotEmpty();

    // Updates only name
    webTestClient
        .patch()
        .uri("/customers/1")
        .bodyValue(
            new CustomerRequest(null, COMPANY_NAME_A.name(), null, null))
        .exchange()
        .expectStatus().isOk()
        .expectBody()
        .jsonPath("cnpj").isEqualTo(COMPANY_NAME_A.cnpj())
        .jsonPath("name").isEqualTo(COMPANY_NAME_A.name())
        .jsonPath("longitude").isEqualTo(newLongitude)
        .jsonPath("latitude").isEqualTo(newLatitude)
        .jsonPath("createdAt").isNotEmpty()
        .jsonPath("updatedAt").isNotEmpty();

    // Updates only longitude
    webTestClient
        .patch()
        .uri("/customers/1")
        .bodyValue(
            new CustomerRequest(null, null, COMPANY_NAME_A.longitude(), null))
        .exchange()
        .expectStatus().isOk()
        .expectBody()
        .jsonPath("cnpj").isEqualTo(COMPANY_NAME_A.cnpj())
        .jsonPath("name").isEqualTo(COMPANY_NAME_A.name())
        .jsonPath("longitude").isEqualTo(COMPANY_NAME_A.longitude())
        .jsonPath("latitude").isEqualTo(newLatitude)
        .jsonPath("createdAt").isNotEmpty()
        .jsonPath("updatedAt").isNotEmpty();

    // Updates only latitude
    webTestClient
        .patch()
        .uri("/customers/1")
        .bodyValue(
            new CustomerRequest(null, null, null, COMPANY_NAME_A.latitude()))
        .exchange()
        .expectStatus().isOk()
        .expectBody()
        .jsonPath("cnpj").isEqualTo(COMPANY_NAME_A.cnpj())
        .jsonPath("name").isEqualTo(COMPANY_NAME_A.name())
        .jsonPath("longitude").isEqualTo(COMPANY_NAME_A.longitude())
        .jsonPath("latitude").isEqualTo(COMPANY_NAME_A.latitude())
        .jsonPath("createdAt").isNotEmpty()
        .jsonPath("updatedAt").isNotEmpty();
  }

  @Test
  public void testGetSuccess() {
    webTestClient
        .get()
        .uri("/customers/1")
        .exchange()
        .expectStatus().isOk()
        .expectBody()
        .jsonPath("cnpj").isEqualTo(COMPANY_NAME_A.cnpj())
        .jsonPath("name").isEqualTo(COMPANY_NAME_A.name())
        .jsonPath("longitude").isEqualTo(COMPANY_NAME_A.longitude())
        .jsonPath("latitude").isEqualTo(COMPANY_NAME_A.latitude())
        .jsonPath("createdAt").isNotEmpty()
        .jsonPath("updatedAt").isNotEmpty();
  }

  @Test
  public void testGetFailure() {
    webTestClient
        .get()
        .uri("/customers/11")
        .exchange()
        .expectStatus().isNotFound();
  }

  @Test
  public void testListAllSuccess() {
    webTestClient
        .get()
        .uri("/customers")
        .exchange()
        .expectStatus().isOk()
        .expectBody()
        .jsonPath("$").isArray()
        .jsonPath("$[0].cnpj").isEqualTo(COMPANY_NAME_A.cnpj())
        .jsonPath("$[0].name").isEqualTo(COMPANY_NAME_A.name())
        .jsonPath("$[0].longitude").isEqualTo(COMPANY_NAME_A.longitude())
        .jsonPath("$[0].latitude").isEqualTo(COMPANY_NAME_A.latitude())
        .jsonPath("$[0].createdAt").isNotEmpty()
        .jsonPath("$[0].updatedAt").isNotEmpty();
  }

  @Test
  public void testListByNameSuccess() {
    webTestClient
        .get()
        .uri("/customers?name=%s".formatted(COMPANY_NAME_A.name()))
        .exchange()
        .expectStatus().isOk()
        .expectBody()
        .jsonPath("$").isArray()
        .jsonPath("$.length()").isEqualTo(1)
        .jsonPath("$[0].cnpj").isEqualTo(COMPANY_NAME_A.cnpj())
        .jsonPath("$[0].name").isEqualTo(COMPANY_NAME_A.name())
        .jsonPath("$[0].longitude").isEqualTo(COMPANY_NAME_A.longitude())
        .jsonPath("$[0].latitude").isEqualTo(COMPANY_NAME_A.latitude())
        .jsonPath("$[0].createdAt").isNotEmpty()
        .jsonPath("$[0].updatedAt").isNotEmpty();
  }

  @Test
  public void testListByNameNotFound() {
    webTestClient
        .get()
        .uri("/customers?name=name")
        .exchange()
        .expectStatus().isOk()
        .expectBody()
        .jsonPath("$").isArray()
        .jsonPath("$.length()").isEqualTo(0);
  }
}
