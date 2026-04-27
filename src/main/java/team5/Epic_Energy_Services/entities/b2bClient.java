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
public class b2bClient {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID b2bClientID;

    @Column(nullable = false)
    private String companyName;
    @Column(nullable = false, unique = true)
    private Integer vatNumber;
    @Column(nullable = false)
    private LocalDate createdAt;
    @Column(nullable = false)
    private LocalDate lastContactDate;
    @Column(nullable = false)
    private double annualRevenue;
    @Column(nullable = false)
    private String certifiedEmail;
    @Column(nullable = false)
    private Integer phone;
    @Column(nullable = false)
    private String contactEmail;
    @Column(nullable = false)
    private String contactName;
    @Column(nullable = false)
    private String contactSurname;
    @Column(nullable = false)
    private Integer contactPhone;
    @Column
    private String companyLogo;
    @Column(nullable = false)
    private String legalAddress;
    @Column
    private String operationalAddress;


    @Enumerated
    private TypeB2bClient type;


    public b2bClient(String companyName, Integer vatNumber, LocalDate createdAt, LocalDate lastContactDate, double annualRevenue, String certifiedEmail, Integer phone, String contactEmail, String contactName, String contactSurname, Integer contactPhone, String legalAddress, String operationalAddress, TypeB2bClient type) {
        this.companyName = companyName;
        this.vatNumber = vatNumber;
        this.createdAt = createdAt;
        this.lastContactDate = lastContactDate;
        this.annualRevenue = annualRevenue;
        this.certifiedEmail = certifiedEmail;
        this.phone = phone;
        this.contactEmail = contactEmail;
        this.contactName = contactName;
        this.contactSurname = contactSurname;
        this.contactPhone = contactPhone;
        this.companyLogo = "https://ui-avatars.com/api?name=" + companyName + "+" + contactSurname;
        this.legalAddress = legalAddress;
        this.operationalAddress = operationalAddress;
        this.type = type;
    }
}
