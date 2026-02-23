package app.nursing.imaging.controller;

import app.common.ApiResponse;
import app.nursing.imaging.dto.ImagingExamDTO;
import app.nursing.imaging.service.ImagingExamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/imaging-exam")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "ImagingExam", description = "Imaging exam CRUD")
public class ImagingExamController {

    private final ImagingExamService imagingExamService;

    @Operation(summary = "Imaging exam list", description = "Find all imaging exams")
    @GetMapping
    public ResponseEntity<ApiResponse<List<ImagingExamDTO>>> findList() {
        List<ImagingExamDTO> list = imagingExamService.findImagingExamList();
        return ResponseEntity.ok(new ApiResponse<>(true, "OK", list));
    }

    @Operation(summary = "Imaging exam search", description = "Search by visitId or imagingType")
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<ImagingExamDTO>>> search(
            @RequestParam String searchBy,
            @RequestParam String keyword
    ) {
        List<ImagingExamDTO> list = imagingExamService.searchImagingExam(searchBy, keyword);
        return ResponseEntity.ok(new ApiResponse<>(true, "OK", list));
    }

    @Operation(summary = "Imaging exam detail", description = "Find imaging exam by id")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ImagingExamDTO>> findImagingExamDetail(@PathVariable String id) {
        ImagingExamDTO dto = imagingExamService.findImagingExamDetail(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "OK", dto));
    }

    @Operation(summary = "Create imaging exam", description = "Create imaging exam")
    @PostMapping
    public ResponseEntity<ApiResponse<ImagingExamDTO>> registerImagingExam(@RequestBody ImagingExamDTO imagingExamDTO) {
        ImagingExamDTO created = imagingExamService.registerImagingExam(imagingExamDTO);
        return ResponseEntity.ok(new ApiResponse<>(true, "Created", created));
    }

    @Operation(summary = "Update imaging exam", description = "Update imaging exam")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ImagingExamDTO>> modifyImagingExam(
            @PathVariable String id,
            @RequestBody ImagingExamDTO imagingExamDTO
    ) {
        ImagingExamDTO updated = imagingExamService.modifyImagingExam(id, imagingExamDTO);
        return ResponseEntity.ok(new ApiResponse<>(true, "Updated", updated));
    }

    @Operation(summary = "Delete imaging exam", description = "Soft delete imaging exam")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteImagingExam(@PathVariable String id) {
        imagingExamService.deleteImagingExam(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Deleted", id));
    }
}
