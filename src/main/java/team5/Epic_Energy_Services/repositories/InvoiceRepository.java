package team5.Epic_Energy_Services.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import team5.Epic_Energy_Services.entities.Customer;
import team5.Epic_Energy_Services.entities.Invoice;
import team5.Epic_Energy_Services.entities.InvoiceStatus;

import java.time.LocalDate;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    Page<Invoice> findByCustomer(Customer customer, Pageable pageable);

    Page<Invoice> findByStatus(InvoiceStatus status, Pageable pageable);

    Page<Invoice> findByDate(LocalDate date, Pageable pageable);

    @Query("SELECT i FROM Invoice i WHERE YEAR(i.date) = :year")
    Page<Invoice> findByYear(@Param("year") int year, Pageable pageable);

    Page<Invoice> findByAmountBetween(double min, double max, Pageable pageable);
}