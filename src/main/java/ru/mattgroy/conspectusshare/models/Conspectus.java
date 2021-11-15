package ru.mattgroy.conspectusshare.models;

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
    private Long id;

    @Column
    private String name;

    @Column(name="conspectus_markdown")
    private String conspectusMarkdown;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User owner;
}
