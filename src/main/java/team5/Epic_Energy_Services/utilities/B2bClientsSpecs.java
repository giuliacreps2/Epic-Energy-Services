package team5.Epic_Energy_Services.utilities;

import jakarta.persistence.criteria.Expression;
import org.springframework.data.jpa.domain.Specification;
import team5.Epic_Energy_Services.entities.B2bClient;

import java.time.LocalDate;
import java.util.UUID;

public class B2bClientsSpecs {

    //Id
    public static Specification<B2bClient> searchById(UUID clientId) {
        return (root, query, builder) ->
                clientId == null ? null : builder.equal(root.get("clientId"), clientId);
    }

    ;

    //Nome contatto
    public static Specification<B2bClient> hasContactName(String contactName) {
        return (root, query, builder) -> {
            if (contactName == null || contactName.isEmpty()) {
                return builder.conjunction();
            }
            return builder.like(builder.lower(root.get("contactName")),
                    "%" + contactName.toLowerCase() + "%");
        };
    }

    //Cognome contatto
    public static Specification<B2bClient> hasContactSurname(String contactSurname) {
        return (root, query, builder) -> {
            if (contactSurname == null || contactSurname.isEmpty()) {
                return builder.conjunction();
            }
            return builder.like(builder.lower(root.get("contactSurname")),
                    "%" + contactSurname.toLowerCase() + "%");
        };
    }

    //Nome compagnia
    public static Specification<B2bClient> hasCompanyName(String companyName) {
        return (root, query, builder) -> {
            if (companyName == null || companyName.isEmpty()) {
                return builder.conjunction();
            }
            return builder.like(builder.lower(root.get("companyName")),
                    "%" + companyName.toLowerCase() + "%");
        };
    }

    //Pec
    public static Specification<B2bClient> hasCertifiedEmail(String certifiedEmail) {
        return (root, query, builder) ->
                builder.equal(builder.lower(root.get("certifiedEmail")), certifiedEmail.toLowerCase());
    }

    //Mail contatto
    public static Specification<B2bClient> hasContactEmail(String contactEmail) {
        return (root, query, builder) ->
                builder.equal(builder.lower(root.get("contactEmail")), contactEmail.toLowerCase());
    }

    //Fatturato annuale
    public static Specification<B2bClient> hasAnnualRevenue(Double annualRevenue) {
        return (root, query, builder) -> {
            if (annualRevenue <= 0) {
                return builder.conjunction();
            }
            return builder.greaterThanOrEqualTo(root.get("annualRevenue"), annualRevenue);
        };
    }

    //P.iva
    public static Specification<B2bClient> vatNumberStartsWith(Long vatNumber) {
        return (root, query, builder) -> {
            if (vatNumber == null) {
                return null;
            }
            Expression<String> vatNumberAsString = root.get("vatNumber").as(String.class);
            return builder.like(vatNumberAsString, vatNumber.toString() + "%");
        };
    }

    //Numero di telefono
    public static Specification<B2bClient> contactPhoneStartsWith(Long contactPhone) {
        return (root, query, builder) -> {
            if (contactPhone == null) {
                return null;
            }
            Expression<String> contactPhoneAsString = root.get("contactPhone").as(String.class);
            return builder.like(contactPhoneAsString, contactPhone + "%");
        };
    }

    //CreatedAt
    public static Specification<B2bClient> createdAt(LocalDate createdAt) {
        return (root, query, builder) -> {
            if (createdAt == null) {
                return null;
            }
            return builder.equal(root.get("createdAt"), createdAt);
        };
    }

    //CreatedAt
    public static Specification<B2bClient> createdAtBetween(LocalDate createdAt) {
        return (root, query, builder) -> {
            if (createdAt == null) {
                return null;
            }
            return builder.between(root.get("createdAt"), createdAt, createdAt);
        };
    }

    //LastContactDate
    public static Specification<B2bClient> lastContactDate(LocalDate lastContactDate) {
        return (root, query, builder) -> {
            if (lastContactDate == null) {
                return null;
            }
            return builder.equal(root.get("lastContactDate"), lastContactDate);
        };
    }

    //CreatedAt
    public static Specification<B2bClient> lastContactDateBetween(LocalDate lastContactDate) {
        return (root, query, builder) -> {
            if (lastContactDate == null) {
                return null;
            }
            return builder.between(root.get("lastContactDate"), lastContactDate, lastContactDate);
        };
    }

    //Provincia
    public static Specification<B2bClient> livesInProvinces(String name) {
        return (root, query, builder) -> {
            if (name == null || name.isEmpty()) {
                return null;
            }
            return builder.equal(
                    root.join("legalAddress")
                            .join("municipality")
                            .join("province")
                            .get("name"),
                    name
            );
        };
    }
}
