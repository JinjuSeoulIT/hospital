package app.nursing.specimen.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(schema = "HOSPITAL",name = "SUPPORT_SPECIMEN")
public class SpecimenEntity {

    @Id
    @Column(name = "SPECIMEN_ID")
    private String specimenId;

    @Column(name = "SPECIMEN_STATUS")
    private String specimenStatus;

    @Column(name = "SPECIMEN_TYPE")
    private String specimenType;

    @Column(name = "TEST_EXECUTION_ID")
    private String testExecutionId;

    @Column(name = "COLLECTED_AT")
    private LocalDateTime collectedAt;

    @Column(name = "COLLECTED_BY_ID")
    private String collectedById;

    @Column(name = "STATUS")
    private String status;
}
