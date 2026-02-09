package app.nursing.specimen.repository;

import app.nursing.assessment.entity.AssessmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecimenRepository extends JpaRepository<AssessmentEntity, String> {

}
