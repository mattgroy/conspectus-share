package ru.mattgroy.conspectusshare.Models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "conspectuses")
public class Conspectus implements Serializable {
    @Id
    private Long Id;

    @Column
    private String Name;

    @Column(name="conspectus_markdown")
    private String ConspectusMarkdown;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "subject_id")
    private Subject Subject;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User Owner;
}
