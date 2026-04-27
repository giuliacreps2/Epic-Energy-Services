package team5.Epic_Energy_Services.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "provinces")
@NoArgsConstructor
@Getter
@Setter
public class Province {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sigla;
    private String name;

    public Province(String sigla, String name) {
        this.sigla = sigla;
        this.name = name;
    }
}
