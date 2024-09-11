package de.sample.schulung.accounts.domain;

import jakarta.validation.constraints.NotNull;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

public interface CustomersSink {

  Stream<Customer> getCustomers();

  default Optional<Customer> findCustomerById(@NotNull UUID uuid) {
    return getCustomers()
      .filter(c -> c.getUuid().equals(uuid))
      .findFirst();
  }

  default Stream<Customer> getCustomersByState(@NotNull Customer.CustomerState state) {
    return getCustomers()
      .filter(customer -> state.equals(customer.getState()));
  }

  default boolean customerExists(UUID uuid) {
    return findCustomerById(uuid).isPresent();
  }

  void createCustomer(Customer customer);

  void replaceCustomer( Customer customer);

  void deleteCustomer(@NotNull UUID uuid);

}
