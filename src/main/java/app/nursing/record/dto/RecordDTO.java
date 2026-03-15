package app.nursing.record.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Schema(description = "간호 기록 내용")
@Getter
@Setter
@NoArgsConstructor
public class RecordDTO {

    @Schema(description = "간호 기록 아이디")
    private String nursingId;

    @Schema(description = "기록 시각")
    private LocalDateTime recordedAt;

    @Schema(description = "수축기 혈압")
    private Integer systolicBp;

    @Schema(description = "이완기 혈압")
    private Integer diastolicBp;

    @Schema(description = "맥박")
    private Integer pulse;

    @Schema(description = "호흡수")
    private Integer respiration;

    @Schema(description = "체온")
    private Double temperature;

    @Schema(description = "동맥혈산소포화도")
    private Integer spo2;

    @Schema(description = "간호 관찰 내용")
    private String observation;

    @Schema(description = "통증 점수")
    private Integer painScore;

    @Schema(description = "의식 수준")
    private String consciousnessLevel;

    @Schema(description = "초기 평가")
    private String initialAssessment;

    @Schema(description = "상태")
    private String status;

    @Schema(description = "생성 일시")
    private LocalDateTime createdAt;

    @Schema(description = "수정 일시")
    private LocalDateTime updatedAt;

    @Schema(description = "접수 아이디")
    private String receptionId;

}
