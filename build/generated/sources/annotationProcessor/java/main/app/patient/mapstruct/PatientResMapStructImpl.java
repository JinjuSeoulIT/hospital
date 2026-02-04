package app.patient.mapstruct;

import app.patient.dto.PatientResDTO;
import app.patient.entity.PatientEntity;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-04T14:48:40+0900",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.7.jar, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class PatientResMapStructImpl implements PatientResMapStruct {

    @Override
    public PatientResDTO toDTO(PatientEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Long patientId = null;
        String patientNo = null;
        String name = null;
        String email = null;
        String phone = null;
        String gender = null;
        LocalDate birthDate = null;
        String address = null;
        String addressDetail = null;
        String guardianName = null;
        String guardianPhone = null;
        String guardianRelation = null;
        Boolean isForeigner = null;
        String contactPriority = null;
        String note = null;
        String statusCode = null;
        Boolean isVip = null;
        String photoUrl = null;
        String createdAt = null;
        String updatedAt = null;

        patientId = entity.getPatientId();
        patientNo = entity.getPatientNo();
        name = entity.getName();
        email = entity.getEmail();
        phone = entity.getPhone();
        gender = entity.getGender();
        birthDate = entity.getBirthDate();
        address = entity.getAddress();
        addressDetail = entity.getAddressDetail();
        guardianName = entity.getGuardianName();
        guardianPhone = entity.getGuardianPhone();
        guardianRelation = entity.getGuardianRelation();
        isForeigner = entity.getIsForeigner();
        contactPriority = entity.getContactPriority();
        note = entity.getNote();
        statusCode = entity.getStatusCode();
        isVip = entity.getIsVip();
        photoUrl = entity.getPhotoUrl();
        if ( entity.getCreatedAt() != null ) {
            createdAt = DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( entity.getCreatedAt() );
        }
        if ( entity.getUpdatedAt() != null ) {
            updatedAt = DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( entity.getUpdatedAt() );
        }

        PatientResDTO patientResDTO = new PatientResDTO( patientId, patientNo, name, email, phone, gender, birthDate, address, addressDetail, guardianName, guardianPhone, guardianRelation, isForeigner, contactPriority, note, statusCode, isVip, photoUrl, createdAt, updatedAt );

        return patientResDTO;
    }

    @Override
    public List<PatientResDTO> toDTOList(List<PatientEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<PatientResDTO> list = new ArrayList<PatientResDTO>( entities.size() );
        for ( PatientEntity patientEntity : entities ) {
            list.add( toDTO( patientEntity ) );
        }

        return list;
    }
}
