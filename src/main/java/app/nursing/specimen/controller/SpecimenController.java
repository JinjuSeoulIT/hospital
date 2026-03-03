package app.nursing.specimen.controller;

import app.common.ApiResponse;
import app.nursing.specimen.dto.SpecimenDTO;
import app.nursing.specimen.service.SpecimenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/specimen")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Specimen", description = "간호사 검체")
public class SpecimenController {


    private final SpecimenService specimenService;


    @Operation(summary = "specimen search", description = "검체 검색")
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<SpecimenDTO>>> searchSpecimens(
            @Parameter(description = "검색 조건 타입: testExecutionId(검사 수행 아이디), collectedAt(채취 일시)")
            @RequestParam("searchType") String searchType,

            @Parameter(description = "사용자 검색 값")
            @RequestParam("searchValue") String searchValue
    ) {

        List<SpecimenDTO> list = specimenService.searchSpecimen(searchType, searchValue);

        return ResponseEntity.ok(new ApiResponse<>(true, "검체 검색 조회 성공", list));
    }



    @Operation(summary = "specimen list", description = "검체 전체 목록 조회")
    @GetMapping
    public ResponseEntity<ApiResponse<List<SpecimenDTO>>> findList() {

        List<SpecimenDTO> list = specimenService.findSpecimenList();

        return ResponseEntity.ok(new ApiResponse<>(true, "검체 전체 목록 조회 성공", list));
    }


    @Operation(summary = "specimen", description = "검체 단건 조회")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SpecimenDTO>> findSpecimenDetail(
            @Parameter(description = "검체 아이디")
            @PathVariable String id) {

        SpecimenDTO specimenDTO = specimenService.findSpecimenDetail(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "검체 단건 조회 성공", specimenDTO));
    }



    @Operation(summary = "Create specimen", description = "검체 신규 생성")
    @PostMapping
    public ResponseEntity<ApiResponse<SpecimenDTO>> registerSpecimen(
            @Parameter(description = "신규 생성을 위한 검체 데이터")
            @RequestBody SpecimenDTO specimen)
    {
        SpecimenDTO specimenDTO = specimenService.registerSpecimen(specimen);
        return ResponseEntity.ok(new ApiResponse<>(true, "검체 신규 생성 성공", specimenDTO));
    }



    @Operation(summary = "Update specimen", description = "검체 수정.")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<SpecimenDTO>> modifySpecimen(
            @Parameter(description = "검체 아이디")
            @PathVariable String id,

            @Parameter(description = "수정을 위한 검체 데이터")
            @RequestBody SpecimenDTO specimenDTO
    ) {

        SpecimenDTO updated = specimenService.modifySpecimen(id, specimenDTO);
        return ResponseEntity.ok(new ApiResponse<>(true, "검체 수정 성공", updated));
    }

    @Operation(summary = "Delete specimen", description = "검체 비활성화(is_active).")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> removeSpecimen(
            @Parameter(description = "검체 아이디")
            @PathVariable String id) {

        specimenService.deleteSpecimen(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "검체가 비활성화 되었습니다", id));
    }





}
