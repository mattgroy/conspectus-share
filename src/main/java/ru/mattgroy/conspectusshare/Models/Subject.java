package ru.mattgroy.conspectusshare.Models;

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
    private Long Id;

    @Column
    private String Name;

    @Column
    private String Description;

    @Column(name = "lecturer_name")
    private String LecturerName;

    @Column
    private int Course;

    @Column
    private int Year;
}
