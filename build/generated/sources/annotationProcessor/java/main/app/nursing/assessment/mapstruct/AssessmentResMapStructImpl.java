package app.nursing.assessment.mapstruct;

import app.nursing.assessment.dto.AssessmentDTO;
import app.nursing.assessment.entity.AssessmentEntity;
import java.time.format.DateTimeFormatter;
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
public class AssessmentResMapStructImpl implements AssessmentResMapStruct {

    @Override
    public AssessmentDTO toDTO(AssessmentEntity entity) {
        if ( entity == null ) {
            return null;
        }

        AssessmentDTO assessmentDTO = new AssessmentDTO();

        assessmentDTO.setAssessmentId( entity.getAssessmentId() );
        assessmentDTO.setVisitId( entity.getVisitId() );
        assessmentDTO.setVisitReason( entity.getVisitReason() );
        assessmentDTO.setMedicalHistory( entity.getMedicalHistory() );
        assessmentDTO.setAllergyYn( entity.getAllergyYn() );
        assessmentDTO.setAllergyNote( entity.getAllergyNote() );
        if ( entity.getCreatedAt() != null ) {
            assessmentDTO.setCreatedAt( DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( entity.getCreatedAt() ) );
        }
        if ( entity.getUpdatedAt() != null ) {
            assessmentDTO.setUpdatedAt( DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( entity.getUpdatedAt() ) );
        }
        assessmentDTO.setNurseId( entity.getNurseId() );

        return assessmentDTO;
    }

    @Override
    public List<AssessmentDTO> toDTOList(List<AssessmentEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<AssessmentDTO> list = new ArrayList<AssessmentDTO>( entities.size() );
        for ( AssessmentEntity assessmentEntity : entities ) {
            list.add( toDTO( assessmentEntity ) );
        }

        return list;
    }
}
