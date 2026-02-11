package app.patient.mapstruct;

import app.patient.dto.PatientRestrictionResDTO;
import app.patient.entity.PatientRestrictionEntity;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-11T10:31:01+0900",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.7.jar, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class PatientRestrictionResMapStructImpl implements PatientRestrictionResMapStruct {

    @Override
    public PatientRestrictionResDTO toDTO(PatientRestrictionEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Long restrictionId = null;
        Long patientId = null;
        String restrictionType = null;
        Boolean activeYn = null;
        String reason = null;
        LocalDateTime startAt = null;
        LocalDateTime endAt = null;
        String createdBy = null;

        restrictionId = entity.getRestrictionId();
        patientId = entity.getPatientId();
        restrictionType = entity.getRestrictionType();
        activeYn = entity.getActiveYn();
        reason = entity.getReason();
        startAt = entity.getStartAt();
        endAt = entity.getEndAt();
        createdBy = entity.getCreatedBy();

        PatientRestrictionResDTO patientRestrictionResDTO = new PatientRestrictionResDTO( restrictionId, patientId, restrictionType, activeYn, reason, startAt, endAt, createdBy );

        return patientRestrictionResDTO;
    }

    @Override
    public List<PatientRestrictionResDTO> toDTOList(List<PatientRestrictionEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<PatientRestrictionResDTO> list = new ArrayList<PatientRestrictionResDTO>( entities.size() );
        for ( PatientRestrictionEntity patientRestrictionEntity : entities ) {
            list.add( toDTO( patientRestrictionEntity ) );
        }

        return list;
    }
}
