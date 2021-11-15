package ru.mattgroy.conspectusshare.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "subjects")
public class Subject {
    @Id
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column(name = "lecturer_name")
    private String lecturerName;

    @Column
    private int course;

    @Column
    private int year;
}
