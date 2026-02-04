package app.patient.service.impl;

import app.patient.storage.PatientStorageService;
import app.patient.dto.ConsentCreateReqDTO;
import app.patient.dto.ConsentResDTO;
import app.patient.dto.ConsentUpdateReqDTO;
import app.patient.entity.ConsentEntity;
import app.patient.exception.ConsentNotFoundException;
import app.patient.mapper.ConsentMapper;
import app.patient.mapstruct.ConsentReqMapStruct;
import app.patient.mapstruct.ConsentResMapStruct;
import app.patient.repository.ConsentRepository;
import app.patient.service.ConsentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class ConsentServiceImpl implements ConsentService {

    private final ConsentRepository consentRepository;
    private final ConsentMapper consentMapper;
    private final ConsentReqMapStruct consentReqMapStruct;
    private final ConsentResMapStruct consentResMapStruct;
    private final PatientStorageService patientStorageService;

    @Override
    public List<ConsentResDTO> findList(Long patientId) {
        log.info("Service: consent list, patientId={}", patientId);
        List<ConsentEntity> entities = consentRepository.findByPatientIdOrderByCreatedAtDesc(patientId);
        return consentResMapStruct.toDTOList(entities);
    }

    @Override
    @Cacheable(value = "CONSENT", key = "#consentId")
    public ConsentResDTO findDetail(Long patientId, Long consentId) {
        log.info("Service: consent detail, patientId={}, consentId={}", patientId, consentId);
        ConsentEntity entity = consentRepository.findByConsentIdAndPatientId(consentId, patientId)
                .orElseThrow(() -> new ConsentNotFoundException(consentId));
        return consentResMapStruct.toDTO(entity);
    }

    @Override
    @Transactional
    public ConsentResDTO register(Long patientId, ConsentCreateReqDTO createReqDTO, MultipartFile file) {
        log.info("Service: consent create, patientId={}", patientId);

        ConsentEntity entity = consentReqMapStruct.toEntity(createReqDTO);
        if (createReqDTO.getPatientId() != null && !patientId.equals(createReqDTO.getPatientId())) {
            throw new IllegalArgumentException("patientId does not match.");
        }
        entity.setPatientId(patientId);
        entity.setActiveYn(Boolean.TRUE);
        entity.setAgreedAt(LocalDateTime.now());

        if (file != null && !file.isEmpty()) {
            String fileUrl = patientStorageService.save(file, "consent");
            entity.setFileUrl(fileUrl);
        }

        ConsentEntity saved = consentRepository.save(entity);
        return consentResMapStruct.toDTO(saved);
    }

    @Override
    @Transactional
    @CacheEvict(value = "CONSENT", key = "#consentId")
    public ConsentResDTO modify(Long patientId, Long consentId, ConsentUpdateReqDTO updateReqDTO, MultipartFile file) {
        log.info("Service: consent update, patientId={}, consentId={}", patientId, consentId);

        ConsentEntity saved = consentRepository.findByConsentIdAndPatientId(consentId, patientId)
                .orElseThrow(() -> new ConsentNotFoundException(consentId));

        if (updateReqDTO.getActiveYn() != null) {
            boolean nextActive = updateReqDTO.getActiveYn();
            saved.setActiveYn(nextActive);
            if (!nextActive && saved.getWithdrawnAt() == null) {
                saved.setWithdrawnAt(LocalDateTime.now());
            }
            if (nextActive) {
                saved.setWithdrawnAt(null);
            }
        }
        if (updateReqDTO.getFileUrl() != null) saved.setFileUrl(updateReqDTO.getFileUrl());
        if (updateReqDTO.getNote() != null) saved.setNote(updateReqDTO.getNote());
        if (updateReqDTO.getAgreedAt() != null) saved.setAgreedAt(updateReqDTO.getAgreedAt());
        if (updateReqDTO.getWithdrawnAt() != null) saved.setWithdrawnAt(updateReqDTO.getWithdrawnAt());

        if (file != null && !file.isEmpty()) {
            String fileUrl = patientStorageService.save(file, "consent");
            saved.setFileUrl(fileUrl);
        }

        return consentResMapStruct.toDTO(saved);
    }

    @Override
    @Transactional
    @CacheEvict(value = "CONSENT", key = "#consentId")
    public void remove(Long patientId, Long consentId) {
        log.info("Service: consent delete, patientId={}, consentId={}", patientId, consentId);

        if (!consentRepository.existsByConsentIdAndPatientId(consentId, patientId)) {
            throw new ConsentNotFoundException(consentId);
        }
        consentRepository.deleteById(consentId);
    }

    @Override
    public List<ConsentResDTO> search(Long patientId, String type, String keyword) {
        if (keyword == null || keyword.isBlank()) {
            throw new IllegalArgumentException("keyword is required");
        }

        List<ConsentEntity> entities = consentMapper.search(type, keyword.trim());
        return consentResMapStruct.toDTOList(
                entities.stream()
                        .filter(e -> patientId.equals(e.getPatientId()))
                        .collect(Collectors.toList())
        );
    }
}

