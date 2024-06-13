package de.sample.schulung.consumer.client;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
@Getter
@Setter
@ConfigurationProperties("customer-api")
public class CustomerApiClientConfiguration {

  private String baseUrl;
  private int timeout;

  // https://docs.spring.io/spring-framework/reference/integration/rest-clients.html#rest-http-interface
  @Bean
  CustomerApiClient provideApiClient() {
    var restClient = RestClient
      .builder()
      .requestFactory(getClientHttpRequestFactory())
      .baseUrl(this.baseUrl)
      .build();
    var restClientAdapter = RestClientAdapter
      .create(restClient);
    return HttpServiceProxyFactory
      .builderFor(restClientAdapter)
      .build()
      .createClient(CustomerApiClient.class);
  }

  private ClientHttpRequestFactory getClientHttpRequestFactory() {
    var clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
    clientHttpRequestFactory.setConnectTimeout(100);
    clientHttpRequestFactory.setConnectionRequestTimeout(timeout);
    return clientHttpRequestFactory;
  }

}
