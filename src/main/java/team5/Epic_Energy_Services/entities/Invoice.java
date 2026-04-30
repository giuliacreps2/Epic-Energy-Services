package team5.Epic_Energy_Services.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "invoices")
@Getter
@Setter
@NoArgsConstructor
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private double amount;
    private int number;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private B2bClient client;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private InvoiceStatus status;
}