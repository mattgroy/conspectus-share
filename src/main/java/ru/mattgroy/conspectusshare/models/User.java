package ru.mattgroy.conspectusshare.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String principalId;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String email;

    @Column
    private String photo;

    @Column
    private Instant created;

    @Column
    private Instant lastLogin;

    public User(String principalId, String firstName, String lastName, String email, String photo) {
        this.principalId = principalId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.photo = photo;
        this.created = Instant.now();
    }

    public User(CustomOAuth2User oauth2User) {
        this.principalId = oauth2User.getPrincipalId();
        this.firstName = oauth2User.getFirstName();
        this.lastName = oauth2User.getLastName();
        this.email = oauth2User.getEmail();
        this.photo = oauth2User.getPhoto();
        this.created = Instant.now();
    }
}