package de.sample.schulung.accounts.persistence;

import de.sample.schulung.accounts.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CustomerEntityRepository extends JpaRepository<CustomerEntity, UUID> {

//  @Query("select c from Customer c where c.state = :state")
//  List<CustomerEntity> findAllCustomersWithState(
//    @Param("state") Customer.CustomerState state
//  );

  List<CustomerEntity> findAllByState(Customer.CustomerState state);

  // Stream<CustomerEntity> streamAllByState(Customer.CustomerState state);

}
