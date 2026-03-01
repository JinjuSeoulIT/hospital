package app.nursing.record.mapper;

import app.nursing.record.entity.RecordEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface  RecordMapper {
    List<RecordEntity> search (
            @Param("searchType") String searchType,
            @Param("searchValue") String searchValue
    );
}
