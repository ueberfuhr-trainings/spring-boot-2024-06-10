package de.sample.schulung.accounts.domain;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomersSinkConfiguration {

  @ConditionalOnMissingBean
  @Bean
  CustomersSink inMemorySink() {
    return new InMemoryCustomersSink();
  }

}
