package app.patient.mapstruct;

import app.patient.dto.CreateReqDTO;
import app.patient.entity.PatientEntity;
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
public class PatientReqMapStructImpl implements PatientReqMapStruct {

    @Override
    public List<PatientEntity> toEntityList(List<CreateReqDTO> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<PatientEntity> list = new ArrayList<PatientEntity>( dtos.size() );
        for ( CreateReqDTO createReqDTO : dtos ) {
            list.add( toEntity( createReqDTO ) );
        }

        return list;
    }

    @Override
    public PatientEntity toEntity(CreateReqDTO dto) {
        if ( dto == null ) {
            return null;
        }

        PatientEntity patientEntity = new PatientEntity();

        patientEntity.setName( dto.getName() );
        patientEntity.setGender( dto.getGender() );
        patientEntity.setBirthDate( dto.getBirthDate() );
        patientEntity.setPhone( dto.getPhone() );
        patientEntity.setEmail( dto.getEmail() );
        patientEntity.setAddress( dto.getAddress() );
        patientEntity.setAddressDetail( dto.getAddressDetail() );
        patientEntity.setGuardianName( dto.getGuardianName() );
        patientEntity.setGuardianPhone( dto.getGuardianPhone() );
        patientEntity.setGuardianRelation( dto.getGuardianRelation() );
        patientEntity.setIsForeigner( dto.getIsForeigner() );
        patientEntity.setContactPriority( dto.getContactPriority() );
        patientEntity.setNote( dto.getNote() );

        return patientEntity;
    }
}
