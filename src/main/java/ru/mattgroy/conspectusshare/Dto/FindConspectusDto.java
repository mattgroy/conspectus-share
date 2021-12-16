package ru.mattgroy.conspectusshare.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindConspectusDto {
    private String name;
    private Integer course;
    private Integer year;
    private Long subjectId;
}
