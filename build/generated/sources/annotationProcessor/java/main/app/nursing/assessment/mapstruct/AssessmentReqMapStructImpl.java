package app.nursing.assessment.mapstruct;

import app.nursing.assessment.dto.AssessmentDTO;
import app.nursing.assessment.entity.AssessmentEntity;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-11T10:31:01+0900",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.7.jar, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class AssessmentReqMapStructImpl implements AssessmentReqMapStruct {

    @Override
    public AssessmentEntity toEntity(AssessmentDTO dto) {
        if ( dto == null ) {
            return null;
        }

        AssessmentEntity assessmentEntity = new AssessmentEntity();

        assessmentEntity.setAssessmentId( dto.getAssessmentId() );
        assessmentEntity.setVisitId( dto.getVisitId() );
        assessmentEntity.setVisitReason( dto.getVisitReason() );
        assessmentEntity.setMedicalHistory( dto.getMedicalHistory() );
        assessmentEntity.setAllergyYn( dto.getAllergyYn() );
        assessmentEntity.setAllergyNote( dto.getAllergyNote() );
        assessmentEntity.setIsActive( dto.getIsActive() );
        if ( dto.getCreatedAt() != null ) {
            assessmentEntity.setCreatedAt( LocalDateTime.parse( dto.getCreatedAt() ) );
        }
        if ( dto.getUpdatedAt() != null ) {
            assessmentEntity.setUpdatedAt( LocalDateTime.parse( dto.getUpdatedAt() ) );
        }
        assessmentEntity.setNurseId( dto.getNurseId() );

        return assessmentEntity;
    }
}
