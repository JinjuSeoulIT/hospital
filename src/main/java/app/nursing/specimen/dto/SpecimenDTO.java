package app.nursing.specimen.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class SpecimenDTO {

    private String specimenId;
    private String specimenStatus;
    private String specimenType;
    private String testExecutionID ;
    private LocalDateTime collectedAt;
    private String collectedById;
    private String status;
}
