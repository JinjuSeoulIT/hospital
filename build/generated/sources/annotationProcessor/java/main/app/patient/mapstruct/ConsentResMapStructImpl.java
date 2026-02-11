package app.patient.mapstruct;

import app.patient.dto.ConsentResDTO;
import app.patient.entity.ConsentEntity;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-11T12:30:52+0900",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.7.jar, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class ConsentResMapStructImpl implements ConsentResMapStruct {

    @Override
    public ConsentResDTO toDTO(ConsentEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Long consentId = null;
        Long patientId = null;
        String consentType = null;
        Boolean activeYn = null;
        LocalDateTime agreedAt = null;
        LocalDateTime withdrawnAt = null;
        String fileUrl = null;
        String note = null;
        LocalDateTime createdAt = null;
        LocalDateTime updatedAt = null;

        consentId = entity.getConsentId();
        patientId = entity.getPatientId();
        consentType = entity.getConsentType();
        activeYn = entity.getActiveYn();
        agreedAt = entity.getAgreedAt();
        withdrawnAt = entity.getWithdrawnAt();
        fileUrl = entity.getFileUrl();
        note = entity.getNote();
        createdAt = entity.getCreatedAt();
        updatedAt = entity.getUpdatedAt();

        ConsentResDTO consentResDTO = new ConsentResDTO( consentId, patientId, consentType, activeYn, agreedAt, withdrawnAt, fileUrl, note, createdAt, updatedAt );

        return consentResDTO;
    }

    @Override
    public List<ConsentResDTO> toDTOList(List<ConsentEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<ConsentResDTO> list = new ArrayList<ConsentResDTO>( entities.size() );
        for ( ConsentEntity consentEntity : entities ) {
            list.add( toDTO( consentEntity ) );
        }

        return list;
    }
}
