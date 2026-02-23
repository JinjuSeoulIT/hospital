package app.nursing.record.service;

import app.nursing.record.dto.RecordDTO;
import app.nursing.record.entity.RecordEntity;
import app.nursing.record.mapstruct.RecordReqMapStruct;
import app.nursing.record.mapstruct.RecordResMapStruct;
import app.nursing.record.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class RecordServiceImpl implements RecordService {
        private final RecordRepository recordRepository;
        private final RecordReqMapStruct recordReqMapStruct;
        private final RecordResMapStruct recordResMapStruct;




    @Override
    public List<RecordDTO> findRecordList() {
        log.info("간호 기록 전체 조회");
        List<RecordEntity> entities = recordRepository.findAll();
        return recordResMapStruct.toDTOList(entities);
    }

    @Override
    public RecordDTO findRecordDetail(String id) {
        log.info("Record detail nursingId={} 로 간호 기록 단건 조회 메서드가 실행됩니다.", id);

        RecordEntity entity = recordRepository.findById(id).
                orElseThrow(()-> new IllegalArgumentException("해당 기록이 존재하지 않습니다"));

        return recordResMapStruct.toDTO(entity);
    }

    @Override
    @Transactional
    public RecordDTO registerRecord(RecordDTO recordDTO) {
        log.info("간호 기록 신규 생성 메서드가 실행됩니다");
        RecordEntity entity = recordReqMapStruct.toEntity(recordDTO);

        if (entity.getNursingId() == null || entity.getNursingId().trim().isEmpty()) {
            entity.setNursingId("NUR_" + System.currentTimeMillis());
            entity.setStatus("ACTIVE");
        }
        RecordEntity newRecord = recordRepository.save(entity);
        return recordResMapStruct.toDTO(newRecord);
    }


    @Override
    @Transactional
    public RecordDTO modifyRecord(String id, RecordDTO recordDTO) {
        log.info("Modify record id={} 간호 기록 수정 메서드가 실행됩니다", id);

        RecordEntity saved = recordRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("수정할 기록이 존재하지 않습니다"));

        saved.setVisitId(recordDTO.getVisitId());
        saved.setSystolicBp(recordDTO.getSystolicBp());
        saved.setDiastolicBp(recordDTO.getDiastolicBp());
        saved.setPulse(recordDTO.getPulse());
        saved.setRespiration(recordDTO.getRespiration());
        saved.setTemperature(recordDTO.getTemperature());
        saved.setSpo2(recordDTO.getSpo2());
        saved.setObservation(recordDTO.getObservation());
        saved.setPainScore(recordDTO.getPainScore());
        saved.setConsciousnessLevel(recordDTO.getConsciousnessLevel());
        saved.setInitialAssessment(recordDTO.getInitialAssessment());
        saved.setStatus(recordDTO.getStatus());

        RecordEntity updated = recordRepository.save(saved);
        return recordResMapStruct.toDTO(updated);
    }

    @Override
    @Transactional
    public void deleteRecord(String id) {
        log.info("Delete record id={} 기록 비활성화 메서드가 실행됩니다", id);

        RecordEntity entity = recordRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("비활성화 할 문건이 존재하지 않습니다"));

        entity.setStatus("INACTIVE");

        recordRepository.save(entity);

    }

    
}
