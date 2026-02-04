package app.patient.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CodeRes {

    private Long id;
    private String groupCode;
    private String code;
    private String name;
    private Integer sortOrder;
    private Boolean isActive;
}

