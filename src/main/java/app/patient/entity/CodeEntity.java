package app.patient.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(schema = "LHS", name = "code")
@Getter
@Setter
public class CodeEntity {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "group_code", nullable = false, length = 50)
    private String groupCode;

    @Column(name = "code", nullable = false, length = 50)
    private String code;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "sort_order", nullable = false)
    private Integer sortOrder;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;
}

