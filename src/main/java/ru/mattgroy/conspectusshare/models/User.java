package ru.mattgroy.conspectusshare.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    private Long Id;

    @Column
    private String FirstName;

    @Column
    private String LastName;

    @Column
    private String MiddleName;

    @Column
    private String Email;
}