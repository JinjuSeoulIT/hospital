package app.nursing.record.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "NURSING_RECORD")
@Getter
@Setter
@NoArgsConstructor
public class RecordEntity {

    @Id
    @Column(name = "NURSING_ID")
    private String nursingId;



    @Column(name = "RECORDED_AT")
    private LocalDateTime recordedAt;

    @Column(name = "SYSTOLIC_BP")
    private Integer systolicBp;

    @Column(name = "DIASTOLIC_BP")
    private Integer diastolicBp;

    @Column(name = "PULSE")
    private Integer pulse;

    @Column(name = "RESPIRATION")
    private Integer respiration;

    @Column(name = "TEMPERATURE")
    private Double temperature;

    @Column(name = "SPO2")
    private Integer spo2;

    @Column(name = "OBSERVATION")
    private String observation;

    @Column(name = "PAIN_SCORE")
    private Integer painScore;

    @Column(name = "CONSCIOUSNESS_LEVEL")
    private String consciousnessLevel;

    @Column(name = "INITIAL_ASSESSMENT")
    private String initialAssessment;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

    @Column(name = "RECEPTION_ID")
    private String receptionId;
}