package de.sample.schulung.accounts.domain;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@Validated
@Service
@RequiredArgsConstructor
public class CustomersService {

  private final CustomersSink sink;

  public long count() {
    return sink.count();
  }

  public Stream<Customer> getCustomers() {
    return sink.getCustomers();
  }

  public Stream<Customer> getCustomersByState(@NotNull Customer.CustomerState state) { // TODO enum?
    return sink.getCustomersByState(state);
  }

  public void createCustomer(@Valid Customer customer) {
    sink.createCustomer(customer);
  }

  public Optional<Customer> findCustomerById(@NotNull UUID uuid) {
    return sink.findCustomerById(uuid);
  }

  private void ensureExists(UUID uuid) {
    if (!sink.customerExists(uuid)) {
      throw new NotFoundException();
    }
  }

  public void replaceCustomer(@Valid Customer customer) {
    ensureExists(customer.getUuid());
    sink.replaceCustomer(customer);
  }

  public void deleteCustomer(@NotNull UUID uuid) {
    ensureExists(uuid);
    sink.deleteCustomer(uuid);
  }

  public boolean exists(UUID uuid) {
    return sink.customerExists(uuid);
  }

}
