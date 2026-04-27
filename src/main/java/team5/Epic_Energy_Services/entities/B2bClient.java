package team5.Epic_Energy_Services.entities;

import jakarta.persistence.*;
import lombok.*;
import team5.Epic_Energy_Services.enums.TypeB2bClient;

import java.math.BigDecimal;
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
<<<<<<< Updated upstream
    private Long vatNumber;
=======
    private Integer vatNumber;
>>>>>>> Stashed changes
    @Column(nullable = false)
    private LocalDate createdAt;
    @Column(nullable = false)
    private LocalDate lastContactDate;
    @Column(nullable = false)
    private BigDecimal annualRevenue;
    @Column(nullable = false)
    private String certifiedEmail;
    @Column(nullable = false)
<<<<<<< Updated upstream
    private Long phoneClient;
=======
    private Integer phone;
>>>>>>> Stashed changes
    @Column(nullable = false)
    private String contactEmail;
    @Column(nullable = false)
    private String contactName;
    @Column(nullable = false)
    private String contactSurname;
    @Column(nullable = false)
<<<<<<< Updated upstream
    private Long contactPhone;
    @Column
    private String companyLogo;

    @Enumerated(EnumType.STRING)
    private TypeB2bClient typeClient;


<<<<<<<< Updated upstream:src/main/java/team5/Epic_Energy_Services/entities/B2bClient.java
//    @OneToOne
//    @JoinColumn(name = "legal_address_id")
//    private String legalAddress;
//
//    @OneToOne
//    @JoinColumn(name = "operational_address_id")
//    private String operationalAddress;


    public B2bClient(String companyName, Long vatNumber, LocalDate createdAt, LocalDate lastContactDate, double annualRevenue, String certifiedEmail, Long phoneClient, String contactEmail, String contactName, String contactSurname, Long contactPhone, TypeB2bClient typeClient) {
========
=======
    private Integer contactPhone;
    @Column
    private String companyLogo;
    @Column(nullable = false)
    private String legalAddress;
    @Column
    private String operationalAddress;


>>>>>>> Stashed changes
    @Enumerated(EnumType.STRING)
    private TypeB2bClient type;


    public B2bClient(String companyName, Integer vatNumber, LocalDate createdAt, LocalDate lastContactDate, BigDecimal annualRevenue, String certifiedEmail, Integer phone, String contactEmail, String contactName, String contactSurname, Integer contactPhone, String legalAddress, String operationalAddress, TypeB2bClient type) {
<<<<<<< Updated upstream
>>>>>>>> Stashed changes:src/main/java/team5/Epic_Energy_Services/entities/b2bClient.java
=======
>>>>>>> Stashed changes
        this.companyName = companyName;
        this.vatNumber = vatNumber;
        this.createdAt = createdAt;
        this.lastContactDate = lastContactDate;
        this.annualRevenue = annualRevenue;
        this.certifiedEmail = certifiedEmail;
<<<<<<< Updated upstream
        this.phoneClient = phoneClient;
=======
        this.phone = phone;
>>>>>>> Stashed changes
        this.contactEmail = contactEmail;
        this.contactName = contactName;
        this.contactSurname = contactSurname;
        this.contactPhone = contactPhone;
        this.companyLogo = "https://ui-avatars.com/api?name=" + companyName + "+" + contactSurname;
<<<<<<< Updated upstream
//        this.legalAddress = legalAddress;
//        this.operationalAddress = operationalAddress;
        this.typeClient = typeClient;
=======
        this.legalAddress = legalAddress;
        this.operationalAddress = operationalAddress;
        this.type = type;
>>>>>>> Stashed changes
    }
}
