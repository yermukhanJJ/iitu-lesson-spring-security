package com.iitu.lesson.demo3.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "AUTH_USER")
@Getter
@Setter
@NoArgsConstructor
public class User extends BaseEntity {
    @Column(name = "FIRSTNAME")
    private String firstname;
    @Column(name = "LASTNAME")
    private String lastname;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "BLOCKED")
    private boolean blocked;
    @Column(name = "PASSWORD")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "ROLE_PROFILE",
            joinColumns = {@JoinColumn(name = "PROFILE_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")}
    )
    private List<Role> roles;

    public User(String firstname, String lastname, String username,
                String email, String password, List<Role> roles) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.blocked = false;
    }
}
