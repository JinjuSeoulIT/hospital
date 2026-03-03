package app.nursing.specimen.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Schema(description = "검체 내용")
@Getter
@Setter
@NoArgsConstructor
public class SpecimenDTO {

    @Schema(description = "검체 아이디")
    private String specimenId;

    @Schema(description = "검체 상태")
    private String specimenStatus;

    @Schema(description = "검체 종류")
    private String specimenType;

    @Schema(description = "검사 수행 아이디")
    private String testExecutionId ;

    @Schema(description = "채취 일시")
    private LocalDateTime collectedAt;

    @Schema(description = "채취 담당")
    private String collectedById;

    @Schema(description = "상태")
    private String status;
}
