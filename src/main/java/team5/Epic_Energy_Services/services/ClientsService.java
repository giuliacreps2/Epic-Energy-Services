package team5.Epic_Energy_Services.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import team5.Epic_Energy_Services.entities.B2bClient;
import team5.Epic_Energy_Services.exceptions.BadRequestException;
import team5.Epic_Energy_Services.exceptions.NotFoundIdException;
import team5.Epic_Energy_Services.payloads.ClientsDTO;
import team5.Epic_Energy_Services.repositories.ClientsRepository;
import team5.Epic_Energy_Services.tools.EmailSender;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
public class ClientsService {

    private final Cloudinary cloudinary;
    private final ClientsRepository clientsRepository;
    private final EmailSender emailSender;


    public ClientsService(Cloudinary cloudinary, ClientsRepository clientsRepository, EmailSender emailSender) {
        this.cloudinary = cloudinary;
        this.clientsRepository = clientsRepository;
        this.emailSender = emailSender;
    }

    //1.SAVE CLIENT + SEND MAIL
    public B2bClient saveClient(ClientsDTO body) {
        B2bClient newClient = new B2bClient(body.companyName(),
                body.vatNumber(),
                body.createdAt() != null ? body.createdAt() : LocalDate.now(),
                body.lastContactDate() != null ? body.lastContactDate() : LocalDate.now(),
                body.annualRevenue().doubleValue(),
                body.certifiedEmail(),
                body.phoneClient(),
                body.contactEmail(),
                body.contactName(),
                body.contactSurname(),
                body.contactPhone(),
                body.typeClient());
        B2bClient savedClient = this.clientsRepository.save(newClient);
        this.emailSender.sendRegistrationEmail(savedClient);
        log.info("Client saved Successfully" + savedClient);
        return savedClient;
    }

    //2. FINDALL(Pageable pageable)
    public Page<B2bClient> findAllClients(Pageable pageable) {
        Page<B2bClient> clientsList = this.clientsRepository.findAll(pageable);
        return clientsList;
    }

    //3. FINDBYID
    public B2bClient findById(UUID clientId) {
        return this.clientsRepository.findById(clientId).orElseThrow(() -> new NotFoundIdException(clientId));
    }

    //4. UPLOAD LOGO AZIENDALE
    public void logoUpload(MultipartFile file, UUID clientId) {
        try {
            Map result = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String url = (String) result.get("secure_url");
            log.info(url);

            B2bClient client = this.clientsRepository.findById(clientId).orElseThrow(() -> new NotFoundIdException(clientId));
            client.setCompanyLogo(url);
            clientsRepository.save(client);

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }


    //5. FILTRO DETAILS
    public Page<B2bClient> search(Double annualRevenue, LocalDate createdAt, LocalDate lastContactDate, String companyName, String name, Pageable pageable) {
        if (annualRevenue != null && annualRevenue != 0)
            return this.clientsRepository.findByAnnualRevenueGreaterThanEqual(annualRevenue, pageable);

        if (createdAt != null)
            return this.clientsRepository.findAllByCreatedAt(createdAt, pageable);

        if (lastContactDate != null)
            return this.clientsRepository.findAllByLastContactDate(lastContactDate, pageable);

        if (companyName != null)
            return this.clientsRepository.findByCompanyNameContainingIgnoreCase(companyName, pageable);

//        if (contactName != null) {
//            return this.clientsRepository.findByContactNameContainingIgnoreCase(contactName, pageable);
//        }
        if (name != null && !name.isEmpty())
            return this.clientsRepository.findByLegalAddressMunicipalityProvinceNameIgnoreCase(name, pageable);


        return this.clientsRepository.findAll(pageable);
    }


    //6. DELETE
    public void findByIdAndDelete(UUID clientId) {
        B2bClient found = this.clientsRepository.findById(clientId).orElseThrow(() -> new NotFoundIdException(clientId));
        this.clientsRepository.delete(found);
        log.info("Client with id " + clientId + " deleted successfully");
    }


    //7. UPDATE
    public B2bClient findByIdAndUpdate(UUID clientId, ClientsDTO body) {
        B2bClient found = this.clientsRepository.findById(clientId).orElseThrow(() -> new NotFoundIdException(clientId));

        if (!found.getContactEmail().equals(body.contactEmail())) {
            if (this.clientsRepository.existsByContactEmail(body.contactEmail()))
                throw new BadRequestException("Email:" + body.contactEmail() + "already exists");
        }

        found.setCompanyName(body.companyName());
        found.setVatNumber(body.vatNumber());
        found.setAnnualRevenue(body.annualRevenue().doubleValue());
        found.setCreatedAt(body.createdAt());
        found.setLastContactDate(body.lastContactDate());
        found.setCertifiedEmail(body.certifiedEmail());
        found.setPhoneClient(body.phoneClient());
        found.setContactEmail(body.contactEmail());
        found.setContactName(body.contactName());
        found.setContactSurname(body.contactSurname());
        found.setTypeClient(body.typeClient());
        found.setCompanyLogo(body.companyLogo());

        B2bClient updatedClient = this.clientsRepository.save(found);

        log.info("Client with id " + clientId + " updated successfully");

        return updatedClient;
    }


    //    //5. RICERCA per Data di inserimento
//    public Page<B2bClient> findAllByCreatedAtDate(ClientsDTO body, Pageable pageable) {
//        return this.clientsRepository.findAllByCreatedAt(body.createdAt(), pageable);
//    }
//
//    //6. RICERCA per Ultimo Contatto
//    public Page<B2bClient> findAllByLastContactDate(ClientsDTO body, Pageable pageable) {
//        return this.clientsRepository.findAllByLastContactDate(body.lastContactDate(), pageable);
//    }
//
//    //7. RICERCA CLIENTI per Parte del nome
//    public Page<B2bClient> findAllByContactName(ClientsDTO body, Pageable pageable) {
//        return this.clientsRepository.findByContactNameContainingIgnoreCase(body.contactName(), pageable);
//    }
//
//    //8. RICERCA CLIENTE per Parte del nome
//    public B2bClient findByContactName(ClientsDTO body) {
//        return this.clientsRepository.findByContactNameIgnoreCase(body.contactName())
//                .orElseThrow(() -> new NotFoundException(body.contactName() + " not found"));
//    }

    //9. Fatturato annuale


    //10. Ricerca per provincia


    //11. Ricerca per città

}
