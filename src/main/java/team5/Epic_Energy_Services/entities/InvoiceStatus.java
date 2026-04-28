package team5.Epic_Energy_Services.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "invoice_statuses")
@Getter
@Setter
@NoArgsConstructor
public class InvoiceStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    public InvoiceStatus(String name) {
        this.name = name;
    }
}