package app.patient.mapstruct;

import app.patient.dto.PatientFlagResDTO;
import app.patient.entity.PatientFlagEntity;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-10T14:06:52+0900",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.7.jar, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class PatientFlagResMapStructImpl implements PatientFlagResMapStruct {

    @Override
    public PatientFlagResDTO toDTO(PatientFlagEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Long flagId = null;
        Long patientId = null;
        String flagType = null;
        String note = null;
        Boolean activeYn = null;
        LocalDateTime createdAt = null;
        LocalDateTime updatedAt = null;

        flagId = entity.getFlagId();
        patientId = entity.getPatientId();
        flagType = entity.getFlagType();
        note = entity.getNote();
        activeYn = entity.getActiveYn();
        createdAt = entity.getCreatedAt();
        updatedAt = entity.getUpdatedAt();

        PatientFlagResDTO patientFlagResDTO = new PatientFlagResDTO( flagId, patientId, flagType, note, activeYn, createdAt, updatedAt );

        return patientFlagResDTO;
    }

    @Override
    public List<PatientFlagResDTO> toDTOList(List<PatientFlagEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<PatientFlagResDTO> list = new ArrayList<PatientFlagResDTO>( entities.size() );
        for ( PatientFlagEntity patientFlagEntity : entities ) {
            list.add( toDTO( patientFlagEntity ) );
        }

        return list;
    }
}
