package app.nursing.imaging.service;

import app.nursing.imaging.dto.ImagingExamDTO;
import app.nursing.imaging.entity.ImagingExamEntity;
import app.nursing.imaging.mapstruct.ImagingExamReqMapStruct;
import app.nursing.imaging.mapstruct.ImagingExamResMapStruct;
import app.nursing.imaging.repository.ImagingExamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class ImagingExamServiceImpl implements ImagingExamService {

    private static final String ACTIVE_STATUS = "Y";

    private final ImagingExamRepository imagingExamRepository;
    private final ImagingExamReqMapStruct imagingExamReqMapStruct;
    private final ImagingExamResMapStruct imagingExamResMapStruct;

    @Override
    public List<ImagingExamDTO> findImagingExamList() {
        log.info("Imaging exam list");
        List<ImagingExamEntity> entities = imagingExamRepository.findAll();
        return imagingExamResMapStruct.toDTOList(entities);
    }

    @Override
    public ImagingExamDTO findImagingExamDetail(String id) {
        log.info("Imaging exam detail id={}", id);
        ImagingExamEntity entity = imagingExamRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Imaging exam not found"));
        return imagingExamResMapStruct.toDTO(entity);
    }

    @Override
    @Transactional
    public ImagingExamDTO registerImagingExam(ImagingExamDTO imagingExamDTO) {
        log.info("Register imaging exam");
        ImagingExamEntity entity = imagingExamReqMapStruct.toEntity(imagingExamDTO);

        if (entity.getImagingExamId() == null || entity.getImagingExamId().trim().isEmpty()) {
            entity.setImagingExamId("IE_" + UUID.randomUUID().toString().substring(0, 8));
        }
        if (entity.getExamStatusYn() == null || entity.getExamStatusYn().trim().isEmpty()) {
            entity.setExamStatusYn("Y");
        }

        ImagingExamEntity saved = imagingExamRepository.save(entity);
        return imagingExamResMapStruct.toDTO(saved);
    }

    @Override
    @Transactional
    public ImagingExamDTO modifyImagingExam(String id, ImagingExamDTO imagingExamDTO) {
        log.info("Modify imaging exam id={}", id);
        ImagingExamEntity saved = imagingExamRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Imaging exam not found"));

        saved.setVisitId(imagingExamDTO.getVisitId());
        saved.setImagingType(imagingExamDTO.getImagingType());
        saved.setExamStatusYn(imagingExamDTO.getExamStatusYn());
        saved.setExamAt(imagingExamDTO.getExamAt());

        ImagingExamEntity updated = imagingExamRepository.save(saved);
        return imagingExamResMapStruct.toDTO(updated);
    }

    @Override
    @Transactional
    public void deleteImagingExam(String id) {
        log.info("Delete imaging exam id={}", id);
        ImagingExamEntity entity = imagingExamRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Imaging exam not found"));

        entity.setExamStatusYn("N");
        imagingExamRepository.save(entity);
    }

    @Override
    public List<ImagingExamDTO> searchImagingExam(String searchBy, String keyword) {
        String by = searchBy == null ? "" : searchBy.trim().toLowerCase();
        String value = keyword == null ? "" : keyword.trim();

        if (value.isEmpty()) {
            throw new IllegalArgumentException("keyword is required");
        }

        List<ImagingExamEntity> entities;
        if ("visitid".equals(by) || "visit_id".equals(by)) {
            entities = imagingExamRepository.findByVisitIdAndExamStatusYn(value, ACTIVE_STATUS);
        } else if ("imagingtype".equals(by) || "imaging_type".equals(by)) {
            entities = imagingExamRepository.findByImagingTypeContainingIgnoreCaseAndExamStatusYn(value, ACTIVE_STATUS);
        } else {
            throw new IllegalArgumentException("searchBy must be visitId or imagingType");
        }

        return imagingExamResMapStruct.toDTOList(entities);
    }
}
