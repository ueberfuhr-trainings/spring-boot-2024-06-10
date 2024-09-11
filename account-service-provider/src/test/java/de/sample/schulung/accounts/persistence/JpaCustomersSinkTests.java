package de.sample.schulung.accounts.persistence;

import de.sample.schulung.accounts.domain.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ComponentScan(
  basePackageClasses = JpaCustomersSink.class
)
public class JpaCustomersSinkTests {

  @Autowired
  JpaCustomersSink sink;

  @Test
  void shouldReturnCustomerThatIsCreatedBefore() {

    // Testfall: Customer anlegen, danach nach UUID wieder auslesen

    var customer = new Customer();
    customer.setName("Tom");
    customer.setState(Customer.CustomerState.LOCKED);
    customer.setDateOfBirth(LocalDate.of(1990, Month.MAY, 5));

    sink.createCustomer(customer);

    assertThat(customer.getUuid())
      .isNotNull();

    var result = sink.findCustomerById(customer.getUuid());
    assertThat(result)
      .isPresent()
      .get()
      .usingRecursiveComparison()
      .isEqualTo(customer);

    sink.deleteCustomer(customer.getUuid());
    var result2 = sink.findCustomerById(customer.getUuid());
    assertThat(result2)
      .isEmpty();

  }


}
