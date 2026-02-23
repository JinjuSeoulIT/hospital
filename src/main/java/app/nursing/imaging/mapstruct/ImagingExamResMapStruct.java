package app.nursing.imaging.mapstruct;

import app.nursing.imaging.dto.ImagingExamDTO;
import app.nursing.imaging.entity.ImagingExamEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ImagingExamResMapStruct {

    ImagingExamDTO toDTO(ImagingExamEntity entity);

    List<ImagingExamDTO> toDTOList(List<ImagingExamEntity> entities);
}

