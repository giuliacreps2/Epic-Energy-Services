package team5.Epic_Energy_Services.dto;
import team5.Epic_Energy_Services.entities.Municipality;

public record AddressDTO(String street, Integer number, String locality, Integer zipCode, Municipality municipality) {
}
