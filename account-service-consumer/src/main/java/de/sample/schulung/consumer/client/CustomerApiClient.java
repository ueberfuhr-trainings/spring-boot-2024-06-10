package de.sample.schulung.consumer.client;

import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

import java.util.Collection;

// https://www.baeldung.com/spring-6-http-interface
@Validated
@HttpExchange("/customers")
@SuppressWarnings("unused")
public interface CustomerApiClient {

  default Collection<CustomerDto> readAll() {
    return this.readAllByState(null);
  }

  default Collection<CustomerDto> readAllActive() {
    return this.readAllByState("active");
  }

  @GetExchange
  Collection<CustomerDto> readAllByState(@RequestParam(value = "state", required = false) String state);

  @PostExchange
  CustomerDto create(@Valid @RequestBody CustomerDto customer);

}
