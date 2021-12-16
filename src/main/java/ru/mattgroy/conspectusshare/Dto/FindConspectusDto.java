package ru.mattgroy.conspectusshare.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindConspectusDto {
    private String name;
    private int course;
    private int year;
    private Long subjectId;
}
