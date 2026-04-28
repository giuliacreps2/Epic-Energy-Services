package team5.Epic_Energy_Services.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import team5.Epic_Energy_Services.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}