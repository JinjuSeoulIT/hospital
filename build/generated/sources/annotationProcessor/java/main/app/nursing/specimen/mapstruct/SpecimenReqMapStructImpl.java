package app.nursing.specimen.mapstruct;

import app.nursing.specimen.dto.SpecimenDTO;
import app.nursing.specimen.entity.SpecimenEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-03T12:46:34+0900",
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
        specimenEntity.setSpecimenStatus( dto.getSpecimenStatus() );
        specimenEntity.setSpecimenType( dto.getSpecimenType() );
        specimenEntity.setTestExecutionId( dto.getTestExecutionId() );
        specimenEntity.setCollectedAt( dto.getCollectedAt() );
        specimenEntity.setCollectedById( dto.getCollectedById() );
        specimenEntity.setStatus( dto.getStatus() );

        return specimenEntity;
    }
}
