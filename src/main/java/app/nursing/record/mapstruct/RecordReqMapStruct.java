package app.nursing.record.mapstruct;


import app.nursing.record.dto.RecordDTO;
import app.nursing.record.entity.RecordEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RecordReqMapStruct {

    RecordEntity toEntity(RecordDTO dto) ;
}
