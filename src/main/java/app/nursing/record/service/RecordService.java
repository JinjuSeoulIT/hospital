package app.nursing.record.service;

import app.nursing.record.dto.RecordDTO;

import java.util.List;

public interface RecordService {
    List<RecordDTO> search(String searchType, String searchValue );
    List<RecordDTO> findRecordList();
    RecordDTO findRecordDetail(String nursingId);
   RecordDTO registerRecord(RecordDTO recordDTO);
    RecordDTO modifyRecord(String nursingId, RecordDTO recordDTO);
    void deleteRecord(String nursingId);
}
