package app.patient.mapstruct;

import app.patient.dto.InsuranceCreateReqDTO;
import app.patient.entity.InsuranceEntity;
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
public class InsuranceReqMapStructImpl implements InsuranceReqMapStruct {

    @Override
    public List<InsuranceEntity> toEntityList(List<InsuranceCreateReqDTO> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<InsuranceEntity> list = new ArrayList<InsuranceEntity>( dtos.size() );
        for ( InsuranceCreateReqDTO insuranceCreateReqDTO : dtos ) {
            list.add( toEntity( insuranceCreateReqDTO ) );
        }

        return list;
    }

    @Override
    public InsuranceEntity toEntity(InsuranceCreateReqDTO dto) {
        if ( dto == null ) {
            return null;
        }

        InsuranceEntity insuranceEntity = new InsuranceEntity();

        insuranceEntity.setPatientId( dto.getPatientId() );
        insuranceEntity.setInsuranceType( dto.getInsuranceType() );
        insuranceEntity.setPolicyNo( dto.getPolicyNo() );
        insuranceEntity.setStartDate( dto.getStartDate() );
        insuranceEntity.setEndDate( dto.getEndDate() );
        insuranceEntity.setNote( dto.getNote() );

        return insuranceEntity;
    }
}
