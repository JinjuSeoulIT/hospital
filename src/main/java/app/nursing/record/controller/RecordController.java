package app.nursing.record.controller;

import app.common.ApiResponse;
import app.nursing.record.dto.RecordDTO;
import app.nursing.record.service.RecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/record")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Record", description = "간호사 기록 관리")
public class RecordController {

    private final RecordService recordService;

    @Operation(summary = "record search", description = "기록 검색")
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<RecordDTO>>> search(
            @Parameter(description = "검색 조건 타입: receptionId(진료 아이디), recordedAt(기록 시각)")
            @RequestParam("searchType") String searchType,

            @Parameter(description = "사용자 검색 값")
            @RequestParam("searchValue") String searchValue
    ) {
        List<RecordDTO> result = recordService.search(searchType, searchValue);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "기록 검색 성공", result));
    }


    @Operation(summary = "record list", description = "기록 전체 목록 조회")
    @GetMapping
    public ResponseEntity<ApiResponse<List<RecordDTO>>> findList() {

        List<RecordDTO> list = recordService.findRecordList();

        return ResponseEntity.ok(new ApiResponse<>(true, "간호 기록 전체 목록 조회 성공", list));
    }


    @Operation(summary = "record", description = "기록 단건 조회")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<RecordDTO>> findRecordDetail(
            @Parameter(description = "간호 기록 아이디")
            @PathVariable String id) {

        RecordDTO recordDTO = recordService.findRecordDetail(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "간호 단건 조회 성공", recordDTO));
    }



    @Operation(summary = "Create record", description = "기록 신규 생성")
    @PostMapping
    public ResponseEntity<ApiResponse<RecordDTO>> registerRecord(
            @Parameter(description = "신규 간호 기록 등록을 위한 요청 데이터")
            @RequestBody RecordDTO record)
    {RecordDTO recordDTO = recordService.registerRecord(record);
        return ResponseEntity.ok
                (new ApiResponse<>(true, "기록 신규 생성 성공", recordDTO));
    }



    @Operation(summary = "Update record", description = "기록 수정.")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<RecordDTO>> modifyRecord(
            @Parameter(description = "간호 기록 아이디")
            @PathVariable String id,
            @Parameter(description = "간호 기록 수정을 위한 요청 데이터")
            @RequestBody RecordDTO recordDTO
    ) {

        RecordDTO updated = recordService.modifyRecord(id, recordDTO);
        return ResponseEntity.ok(new ApiResponse<>(true, "기록 수정 성공", updated));
    }

    @Operation(summary = "Delete record", description = "기록 비활성화(inactive).")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> removeRecord(
            @Parameter(description = "간호 기록 아이디")
            @PathVariable String id) {

        recordService.deleteRecord(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "비활성화 되었습니다", id));
    }
}
