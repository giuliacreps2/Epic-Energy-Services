package team5.Epic_Energy_Services.entities;

import jakarta.persistence.*;
import lombok.*;
import team5.Epic_Energy_Services.enums.TypeB2bClient;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString


@Entity
@Table(name = "b2b_Client")
public class B2bClient {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID b2bClientID;

    @Column(nullable = false)
    private String companyName;
    @Column(nullable = false, unique = true)
    private Long vatNumber;
    @Column(nullable = false)
    private LocalDate createdAt;
    @Column(nullable = false)
    private LocalDate lastContactDate;
    @Column(nullable = false)
    private double annualRevenue;
    @Column(nullable = false)
    private String certifiedEmail;
    @Column(nullable = false)
    private Long phoneClient;
    @Column(nullable = false)
    private String contactEmail;
    @Column(nullable = false)
    private String contactName;
    @Column(nullable = false)
    private String contactSurname;
    @Column(nullable = false)
    private Long contactPhone;
    @Column
    private String companyLogo;

    @Enumerated(EnumType.STRING)
    private TypeB2bClient typeClient;


//    @OneToOne
//    @JoinColumn(name = "legal_address_id")
//    private Address legalAddress;
//
//    @OneToOne
//    @JoinColumn(name = "operational_address_id")
//    private Address operationalAddress;


    public B2bClient(String companyName, Long vatNumber, LocalDate createdAt, LocalDate lastContactDate, double annualRevenue, String certifiedEmail, Long phoneClient, String contactEmail, String contactName, String contactSurname, Long contactPhone, TypeB2bClient typeClient) {
        this.companyName = companyName;
        this.vatNumber = vatNumber;
        this.createdAt = createdAt;
        this.lastContactDate = lastContactDate;
        this.annualRevenue = annualRevenue;
        this.certifiedEmail = certifiedEmail;
        this.phoneClient = phoneClient;
        this.contactEmail = contactEmail;
        this.contactName = contactName;
        this.contactSurname = contactSurname;
        this.contactPhone = contactPhone;
        this.companyLogo = "https://ui-avatars.com/api?name=" + companyName + "+" + contactSurname;
        this.typeClient = typeClient;
    }
}
