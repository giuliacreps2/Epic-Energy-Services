package team5.Epic_Energy_Services.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "municipalities")
@NoArgsConstructor
@Getter
@Setter
public class Municipality {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @JoinColumn
    @OneToOne
    private Province province;

    public Municipality(String name, Province province) {
        this.name = name;
        this.province = province;
    }
}

