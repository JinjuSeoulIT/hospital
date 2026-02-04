package app.patient.mapstruct;

import app.patient.dto.InsuranceResDTO;
import app.patient.entity.InsuranceEntity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-04T14:48:39+0900",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.7.jar, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class InsuranceResMapStructImpl implements InsuranceResMapStruct {

    @Override
    public InsuranceResDTO toDTO(InsuranceEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Long insuranceId = null;
        Long patientId = null;
        String insuranceType = null;
        String policyNo = null;
        Boolean activeYn = null;
        Boolean verifiedYn = null;
        LocalDate startDate = null;
        LocalDate endDate = null;
        String note = null;
        LocalDateTime createdAt = null;
        LocalDateTime updatedAt = null;

        insuranceId = entity.getInsuranceId();
        patientId = entity.getPatientId();
        insuranceType = entity.getInsuranceType();
        policyNo = entity.getPolicyNo();
        activeYn = entity.getActiveYn();
        verifiedYn = entity.getVerifiedYn();
        startDate = entity.getStartDate();
        endDate = entity.getEndDate();
        note = entity.getNote();
        createdAt = entity.getCreatedAt();
        updatedAt = entity.getUpdatedAt();

        InsuranceResDTO insuranceResDTO = new InsuranceResDTO( insuranceId, patientId, insuranceType, policyNo, activeYn, verifiedYn, startDate, endDate, note, createdAt, updatedAt );

        return insuranceResDTO;
    }

    @Override
    public List<InsuranceResDTO> toDTOList(List<InsuranceEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<InsuranceResDTO> list = new ArrayList<InsuranceResDTO>( entities.size() );
        for ( InsuranceEntity insuranceEntity : entities ) {
            list.add( toDTO( insuranceEntity ) );
        }

        return list;
    }
}
