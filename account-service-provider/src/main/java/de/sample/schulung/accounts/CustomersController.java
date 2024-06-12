package de.sample.schulung.accounts;

import de.sample.schulung.accounts.boundary.api.CustomersApi;
import de.sample.schulung.accounts.boundary.api.model.Customer;
import de.sample.schulung.accounts.boundary.api.model.CustomerState;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1")
public class CustomersController implements CustomersApi {

  private final Map<UUID, Customer> customers = new HashMap<>();

  {
    this.customersPost(
      new Customer()
        .name("Max")
        .birthdate(LocalDate.of(2010, Month.FEBRUARY, 10))
        .state(CustomerState.ACTIVE)
    );
    this.customersPost(
      new Customer()
        .name("Julia")
        .birthdate(LocalDate.of(2011, Month.APRIL, 2))
        .state(CustomerState.DISABLED)
    );
  }

  @Override
  public ResponseEntity<List<Customer>> customersGet(CustomerState state) {
    return ResponseEntity.ok(
      this.customers
      .values()
      .stream()
      .filter(customer -> state == null || state == customer.getState())
        .toList()
    );
  }

  @Override
  public ResponseEntity<Customer> customersPost(Customer customer) {
    var uuid = UUID.randomUUID();
    customer.setUuid(uuid);
    this.customers.put(uuid, customer);
    var uri = linkTo(
      methodOn(CustomersApi.class)
        .customersIdGet(uuid)
    ).toUri();
    return ResponseEntity
      .created(uri)
      .body(customer);
  }

  @Override
  public ResponseEntity<Customer> customersIdGet(UUID uuid) {
    var result = this.customers
      .get(uuid);
    if(result == null) {
      throw new NotFoundException();
    }
    return ResponseEntity.ok(result);
  }

  @Override
  public ResponseEntity<Void> customersIdPut(UUID uuid, Customer customer) {
    if (!this.customers.containsKey(uuid)) {
      throw new NotFoundException();
    }
    customer.setUuid(uuid);
    this.customers.put(uuid, customer);
    return ResponseEntity.noContent().build();
  }

  @Override
  public ResponseEntity<Void> customersIdDelete(UUID uuid) {
    if(this.customers.remove(uuid) != null) {
      throw new NotFoundException();
    }
    return ResponseEntity.noContent().build();
  }

}
