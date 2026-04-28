package team5.Epic_Energy_Services.payloads;

import team5.Epic_Energy_Services.entities.Municipality;

public record AddressDTO(String street, Integer number, String locality, Integer zipCode, Municipality municipality) {
}
