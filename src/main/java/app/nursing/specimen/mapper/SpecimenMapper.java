package app.nursing.specimen.mapper;


import app.nursing.specimen.entity.SpecimenEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface  SpecimenMapper {
    List<SpecimenEntity> searchSpecimen (
            @Param("searchType") String searchType,
            @Param("searchValue") String searchValue
    );
}
