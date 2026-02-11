package app.nursing.specimen.mapstruct;

import app.nursing.specimen.dto.SpecimenDTO;
import app.nursing.specimen.entity.SpecimenEntity;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-11T10:31:01+0900",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.7.jar, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class SpecimenReqMapStructImpl implements SpecimenReqMapStruct {

    @Override
    public SpecimenEntity toEntity(SpecimenDTO dto) {
        if ( dto == null ) {
            return null;
        }

        SpecimenEntity specimenEntity = new SpecimenEntity();

        specimenEntity.setSpecimenId( dto.getSpecimenId() );
        specimenEntity.setVisitId( dto.getVisitId() );
        specimenEntity.setSpecimenType( dto.getSpecimenType() );
        if ( dto.getCollectedAt() != null ) {
            specimenEntity.setCollectedAt( LocalDateTime.parse( dto.getCollectedAt() ) );
        }
        specimenEntity.setStatus( dto.getStatus() );
        if ( dto.getCreatedAt() != null ) {
            specimenEntity.setCreatedAt( LocalDateTime.parse( dto.getCreatedAt() ) );
        }
        if ( dto.getUpdatedAt() != null ) {
            specimenEntity.setUpdatedAt( LocalDateTime.parse( dto.getUpdatedAt() ) );
        }
        specimenEntity.setCreatedBy( dto.getCreatedBy() );

        return specimenEntity;
    }
}
