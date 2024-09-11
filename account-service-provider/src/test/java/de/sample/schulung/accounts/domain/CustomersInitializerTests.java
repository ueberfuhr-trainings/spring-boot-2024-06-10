package de.sample.schulung.accounts.domain;

import de.sample.schulung.accounts.persistence.DisablePersistenceLayer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@SpringBootTest(
  classes = CustomersInitializer.class,
  properties = {
    "application.customers.initialization.enabled=true"
  }
)
@DisablePersistenceLayer
public class CustomersInitializerTests {

  @MockBean
  CustomersService service;

  @Test
  void shouldBeInitialized() {
    verify(service, atLeastOnce()).createCustomer(any());
  }

}
