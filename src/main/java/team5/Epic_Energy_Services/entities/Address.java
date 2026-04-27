package team5.Epic_Energy_Services.entities;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "addresses")
@NoArgsConstructor
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private Integer number;
    private String locality;
    private Integer zipCode;

    @JoinColumn
    @OneToOne
    private Municipality municipality;


    public Address(String street, Integer number, String locality, Integer zipCode, Municipality municipality) {
        this.street = street;
        this.number = number;
        this.locality = locality;
        this.zipCode = zipCode;
        this.municipality = municipality;
    }
}
