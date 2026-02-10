package app.patient.mapstruct;

import app.patient.dto.PatientFlagCreateReqDTO;
import app.patient.entity.PatientFlagEntity;
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
public class PatientFlagReqMapStructImpl implements PatientFlagReqMapStruct {

    @Override
    public List<PatientFlagEntity> toEntityList(List<PatientFlagCreateReqDTO> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<PatientFlagEntity> list = new ArrayList<PatientFlagEntity>( dtos.size() );
        for ( PatientFlagCreateReqDTO patientFlagCreateReqDTO : dtos ) {
            list.add( toEntity( patientFlagCreateReqDTO ) );
        }

        return list;
    }

    @Override
    public PatientFlagEntity toEntity(PatientFlagCreateReqDTO dto) {
        if ( dto == null ) {
            return null;
        }

        PatientFlagEntity patientFlagEntity = new PatientFlagEntity();

        patientFlagEntity.setPatientId( dto.getPatientId() );
        patientFlagEntity.setFlagType( dto.getFlagType() );
        patientFlagEntity.setNote( dto.getNote() );

        return patientFlagEntity;
    }
}
