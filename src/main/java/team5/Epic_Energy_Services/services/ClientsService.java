package team5.Epic_Energy_Services.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
public class ClientsService {

    private final Cloudinary cloudinary;


    public ClientsService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }


    //5. UPLOAD LOGO AZIENDALE
    public Cloudinary logoUpload(MultipartFile file, UUID userId) {
        try {
            Map result = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String url = (String) result.get("secure_url");
            log.info(url);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return cloudinary;
    }
}
