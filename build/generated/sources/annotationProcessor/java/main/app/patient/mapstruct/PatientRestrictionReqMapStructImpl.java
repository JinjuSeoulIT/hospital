package app.patient.mapstruct;

import app.patient.dto.PatientRestrictionCreateReqDTO;
import app.patient.entity.PatientRestrictionEntity;
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
public class PatientRestrictionReqMapStructImpl implements PatientRestrictionReqMapStruct {

    @Override
    public List<PatientRestrictionEntity> toEntityList(List<PatientRestrictionCreateReqDTO> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<PatientRestrictionEntity> list = new ArrayList<PatientRestrictionEntity>( dtos.size() );
        for ( PatientRestrictionCreateReqDTO patientRestrictionCreateReqDTO : dtos ) {
            list.add( toEntity( patientRestrictionCreateReqDTO ) );
        }

        return list;
    }

    @Override
    public PatientRestrictionEntity toEntity(PatientRestrictionCreateReqDTO dto) {
        if ( dto == null ) {
            return null;
        }

        PatientRestrictionEntity patientRestrictionEntity = new PatientRestrictionEntity();

        patientRestrictionEntity.setPatientId( dto.getPatientId() );
        patientRestrictionEntity.setRestrictionType( dto.getRestrictionType() );
        patientRestrictionEntity.setReason( dto.getReason() );
        patientRestrictionEntity.setEndAt( dto.getEndAt() );
        patientRestrictionEntity.setCreatedBy( dto.getCreatedBy() );

        return patientRestrictionEntity;
    }
}
