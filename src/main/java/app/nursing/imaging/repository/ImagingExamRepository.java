package app.nursing.imaging.repository;

import app.nursing.imaging.entity.ImagingExamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImagingExamRepository extends JpaRepository<ImagingExamEntity, String> {

    List<ImagingExamEntity> findByVisitIdAndExamStatusYn(String visitId, String examStatusYn);

    List<ImagingExamEntity> findByImagingTypeContainingIgnoreCaseAndExamStatusYn(String imagingType, String examStatusYn);
}
