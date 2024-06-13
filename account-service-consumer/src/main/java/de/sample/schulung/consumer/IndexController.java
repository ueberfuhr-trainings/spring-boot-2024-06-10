package de.sample.schulung.consumer;

import de.sample.schulung.consumer.client.CustomerApiClient;
import de.sample.schulung.consumer.client.CustomerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class IndexController {

  private final CustomerApiClient customerApi;

  @GetMapping(produces = MediaType.TEXT_PLAIN_VALUE)
  @ResponseBody
  String listActiveCustomers() {
    return "Active Customers (read from Customers API):"
      + customerApi
      .readAllActive()
      .stream()
      .map(CustomerDto::getName)
      .collect(Collectors.joining("\n - ", "\n - ", ""));
  }

}
