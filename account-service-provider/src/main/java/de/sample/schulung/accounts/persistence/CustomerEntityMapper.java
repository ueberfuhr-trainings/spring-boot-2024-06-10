package de.sample.schulung.accounts.persistence;

import de.sample.schulung.accounts.domain.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerEntityMapper {

  CustomerEntity map(Customer source) {
    var result = new CustomerEntity();
    result.setUuid(source.getUuid());
    result.setName(source.getName());
    result.setDateOfBirth(source.getDateOfBirth());
    result.setState(source.getState());
    return result;
  }

  Customer map(CustomerEntity source) {
    var result = new Customer();
    result.setUuid(source.getUuid());
    result.setName(source.getName());
    result.setDateOfBirth(source.getDateOfBirth());
    result.setState(source.getState());
    return result;
  }

}
