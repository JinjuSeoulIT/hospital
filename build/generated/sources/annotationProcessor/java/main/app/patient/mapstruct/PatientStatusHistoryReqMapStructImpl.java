package app.patient.mapstruct;

import app.patient.dto.PatientStatusHistoryCreateReqDTO;
import app.patient.entity.PatientStatusHistoryEntity;
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
public class PatientStatusHistoryReqMapStructImpl implements PatientStatusHistoryReqMapStruct {

    @Override
    public List<PatientStatusHistoryEntity> toEntityList(List<PatientStatusHistoryCreateReqDTO> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<PatientStatusHistoryEntity> list = new ArrayList<PatientStatusHistoryEntity>( dtos.size() );
        for ( PatientStatusHistoryCreateReqDTO patientStatusHistoryCreateReqDTO : dtos ) {
            list.add( toEntity( patientStatusHistoryCreateReqDTO ) );
        }

        return list;
    }

    @Override
    public PatientStatusHistoryEntity toEntity(PatientStatusHistoryCreateReqDTO dto) {
        if ( dto == null ) {
            return null;
        }

        PatientStatusHistoryEntity patientStatusHistoryEntity = new PatientStatusHistoryEntity();

        patientStatusHistoryEntity.setPatientId( dto.getPatientId() );
        patientStatusHistoryEntity.setFromStatus( dto.getFromStatus() );
        patientStatusHistoryEntity.setToStatus( dto.getToStatus() );
        patientStatusHistoryEntity.setReason( dto.getReason() );
        patientStatusHistoryEntity.setChangedBy( dto.getChangedBy() );

        return patientStatusHistoryEntity;
    }
}
