package app.patient.mapstruct;

import app.patient.dto.PatientStatusHistoryResDTO;
import app.patient.entity.PatientStatusHistoryEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-06T17:18:43+0900",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.7.jar, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class PatientStatusHistoryResMapStructImpl implements PatientStatusHistoryResMapStruct {

    @Override
    public List<PatientStatusHistoryResDTO> toDTOList(List<PatientStatusHistoryEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<PatientStatusHistoryResDTO> list = new ArrayList<PatientStatusHistoryResDTO>( entities.size() );
        for ( PatientStatusHistoryEntity patientStatusHistoryEntity : entities ) {
            list.add( toDTO( patientStatusHistoryEntity ) );
        }

        return list;
    }

    @Override
    public PatientStatusHistoryResDTO toDTO(PatientStatusHistoryEntity entity) {
        if ( entity == null ) {
            return null;
        }

        PatientStatusHistoryResDTO patientStatusHistoryResDTO = new PatientStatusHistoryResDTO();

        patientStatusHistoryResDTO.setHistoryId( entity.getHistoryId() );
        patientStatusHistoryResDTO.setPatientId( entity.getPatientId() );
        patientStatusHistoryResDTO.setFromStatus( entity.getFromStatus() );
        patientStatusHistoryResDTO.setToStatus( entity.getToStatus() );
        patientStatusHistoryResDTO.setReason( entity.getReason() );
        patientStatusHistoryResDTO.setChangedBy( entity.getChangedBy() );
        patientStatusHistoryResDTO.setChangedAt( entity.getChangedAt() );

        return patientStatusHistoryResDTO;
    }
}
