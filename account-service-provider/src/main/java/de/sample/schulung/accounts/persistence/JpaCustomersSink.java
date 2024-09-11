package de.sample.schulung.accounts.persistence;

import de.sample.schulung.accounts.domain.Customer;
import de.sample.schulung.accounts.domain.CustomersSink;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class JpaCustomersSink implements CustomersSink {

  private final CustomerEntityRepository repo;
  private final CustomerEntityMapper mapper;

  @Override
  public Stream<Customer> getCustomers() {
    return repo
      .findAll()
      .stream()
      .map(mapper::map);
  }

  @Override
  public Optional<Customer> findCustomerById(UUID uuid) {
    return repo
      .findById(uuid)
      .map(mapper::map);
  }

  @Override
  public Stream<Customer> getCustomersByState(Customer.CustomerState state) {
    return repo
      .findAllByState(state)
      .stream()
      .map(mapper::map);
  }

  @Override
  public boolean customerExists(UUID uuid) {
    return repo
      .existsById(uuid);
  }

  @Override
  public void createCustomer(Customer customer) {
    var entity = mapper.map(customer);
    repo.save(entity);
    customer.setUuid(entity.getUuid());
  }

  @Override
  public void replaceCustomer(Customer customer) {
    var entity = mapper.map(customer);
    repo.save(entity);
  }

  @Override
  public void deleteCustomer(UUID uuid) {
    repo.deleteById(uuid);
  }
}
