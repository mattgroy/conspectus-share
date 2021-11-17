package ru.mattgroy.conspectusshare.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveConspectusDto {
    private String name;
    private String conspectusMarkdown;
    private Long subjectId;
}
