package app.patient.mapstruct;

import app.patient.dto.ConsentCreateReqDTO;
import app.patient.entity.ConsentEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-10T15:23:03+0900",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.7.jar, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class ConsentReqMapStructImpl implements ConsentReqMapStruct {

    @Override
    public List<ConsentEntity> toEntityList(List<ConsentCreateReqDTO> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<ConsentEntity> list = new ArrayList<ConsentEntity>( dtos.size() );
        for ( ConsentCreateReqDTO consentCreateReqDTO : dtos ) {
            list.add( toEntity( consentCreateReqDTO ) );
        }

        return list;
    }

    @Override
    public ConsentEntity toEntity(ConsentCreateReqDTO dto) {
        if ( dto == null ) {
            return null;
        }

        ConsentEntity consentEntity = new ConsentEntity();

        consentEntity.setPatientId( dto.getPatientId() );
        consentEntity.setConsentType( dto.getConsentType() );
        consentEntity.setFileUrl( dto.getFileUrl() );
        consentEntity.setNote( dto.getNote() );

        return consentEntity;
    }
}
