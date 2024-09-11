package de.sample.schulung.accounts.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

public class InMemoryCustomersSink implements CustomersSink {

  private final Map<UUID, Customer> customers = new HashMap<>();

  @Override
  public Stream<Customer> getCustomers() {
    return customers
      .values()
      .stream();
  }

  @Override
  public Optional<Customer> findCustomerById(UUID uuid) {
    return Optional.ofNullable(
      this.customers.get(uuid)
    );
  }

  @Override
  public boolean customerExists(UUID uuid) {
    return this.customers.containsKey(uuid);
  }

  @Override
  public void createCustomer(Customer customer) {
    var uuid = UUID.randomUUID();
    customer.setUuid(uuid);
    this.customers.put(customer.getUuid(), customer);
  }

  @Override
  public void replaceCustomer(Customer customer) {
    this.customers.put(customer.getUuid(), customer);
  }

  @Override
  public void deleteCustomer(UUID uuid) {
    this.customers.remove(uuid);
  }
}
