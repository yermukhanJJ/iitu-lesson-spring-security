package com.iitu.lesson.demo2.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

@Entity
@Table(name = "AUTH_USER")
@Getter
@Setter
@NoArgsConstructor
public class AuthUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "CREATED_DATE")
    private LocalDateTime createdDate;
    @Column(name = "UPDATED_DATE")
    private LocalDateTime updatedDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "ROLE_USER",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")}
    )
    private List<Role> roles;
}
