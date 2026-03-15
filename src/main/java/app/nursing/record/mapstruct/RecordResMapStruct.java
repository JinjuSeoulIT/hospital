package app.nursing.record.mapstruct;

import app.nursing.record.dto.RecordDTO;
import app.nursing.record.entity.RecordEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RecordResMapStruct {
    RecordDTO toDTO(RecordEntity entity);

    List<RecordDTO> toDTOList(List<RecordEntity> entities);
}
