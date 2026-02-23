package app.nursing.record.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class RecordDTO {

    private String nursingId;
    private Long visitId;

    private LocalDateTime recordedAt;

    private Integer systolicBp;
    private Integer diastolicBp;
    private Integer pulse;
    private Integer respiration;
    private Double temperature;
    private Integer spo2;

    private String observation;
    private Integer painScore;
    private String consciousnessLevel;
    private String initialAssessment;

    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
