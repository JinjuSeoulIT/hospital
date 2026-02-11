package app.patient.mapstruct;

import app.patient.dto.PatientMemoResDTO;
import app.patient.entity.PatientMemoEntity;
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
public class PatientMemoResMapStructImpl implements PatientMemoResMapStruct {

    @Override
    public PatientMemoResDTO toDTO(PatientMemoEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Long memoId = null;
        Long patientId = null;
        String memo = null;
        String createdBy = null;
        LocalDateTime createdAt = null;

        memoId = entity.getMemoId();
        patientId = entity.getPatientId();
        memo = entity.getMemo();
        createdBy = entity.getCreatedBy();
        createdAt = entity.getCreatedAt();

        PatientMemoResDTO patientMemoResDTO = new PatientMemoResDTO( memoId, patientId, memo, createdBy, createdAt );

        return patientMemoResDTO;
    }

    @Override
    public List<PatientMemoResDTO> toDTOList(List<PatientMemoEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<PatientMemoResDTO> list = new ArrayList<PatientMemoResDTO>( entities.size() );
        for ( PatientMemoEntity patientMemoEntity : entities ) {
            list.add( toDTO( patientMemoEntity ) );
        }

        return list;
    }
}
