package thegarlic.forum.repository;

import org.springframework.data.repository.CrudRepository;
import thegarlic.forum.domain.Customer;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);
}