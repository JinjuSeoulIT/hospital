package app.nursing.imaging.mapstruct;

import app.nursing.imaging.dto.ImagingExamDTO;
import app.nursing.imaging.entity.ImagingExamEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ImagingExamReqMapStruct {

    ImagingExamEntity toEntity(ImagingExamDTO dto);
}

