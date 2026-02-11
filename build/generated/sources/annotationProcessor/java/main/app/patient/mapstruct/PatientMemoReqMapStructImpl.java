package app.patient.mapstruct;

import app.patient.dto.PatientMemoCreateReqDTO;
import app.patient.entity.PatientMemoEntity;
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
public class PatientMemoReqMapStructImpl implements PatientMemoReqMapStruct {

    @Override
    public List<PatientMemoEntity> toEntityList(List<PatientMemoCreateReqDTO> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<PatientMemoEntity> list = new ArrayList<PatientMemoEntity>( dtos.size() );
        for ( PatientMemoCreateReqDTO patientMemoCreateReqDTO : dtos ) {
            list.add( toEntity( patientMemoCreateReqDTO ) );
        }

        return list;
    }

    @Override
    public PatientMemoEntity toEntity(PatientMemoCreateReqDTO dto) {
        if ( dto == null ) {
            return null;
        }

        PatientMemoEntity patientMemoEntity = new PatientMemoEntity();

        patientMemoEntity.setPatientId( dto.getPatientId() );
        patientMemoEntity.setMemo( dto.getMemo() );
        patientMemoEntity.setCreatedBy( dto.getCreatedBy() );

        return patientMemoEntity;
    }
}
