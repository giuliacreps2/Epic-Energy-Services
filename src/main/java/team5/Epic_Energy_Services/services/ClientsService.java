package team5.Epic_Energy_Services.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import team5.Epic_Energy_Services.entities.B2bClient;
import team5.Epic_Energy_Services.exceptions.NotFoundIdException;
import team5.Epic_Energy_Services.payloads.ClientsDTO;
import team5.Epic_Energy_Services.repositories.ClientsRepository;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
public class ClientsService {

    private final Cloudinary cloudinary;
    private final ClientsRepository clientsRepository;

    public ClientsService(Cloudinary cloudinary, ClientsRepository clientsRepository) {
        this.cloudinary = cloudinary;
        this.clientsRepository = clientsRepository;
    }


    //1.SAVE CLIENT
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
        return savedClient;
    }


    //2. FINDBYID
    public B2bClient findById(UUID clientId) {
        return this.clientsRepository.findById(clientId).orElseThrow(() -> new NotFoundIdException(clientId));
    }


    //5. UPLOAD LOGO AZIENDALE
    public void logoUpload(MultipartFile file, UUID clientId) {
        try {
            Map result = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String url = (String) result.get("secure_url");
            log.info(url);

            B2bClient client = this.clientsRepository.findById(clientId).orElseThrow();
            client.setCompanyLogo(url);
            clientsRepository.save(client);

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
