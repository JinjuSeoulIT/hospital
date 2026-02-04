package app.patient.repository;

import app.patient.entity.CodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CodeRepository extends JpaRepository<CodeEntity, Long> {

    List<CodeEntity> findAllByGroupCodeAndIsActiveTrueOrderBySortOrderAscIdAsc(String groupCode);
}

