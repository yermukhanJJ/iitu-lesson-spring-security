package com.iitu.lesson.demo3.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "REFRESH_TOKEN")
@Getter
@Setter
@NoArgsConstructor
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "TOKEN")
    private String token;
    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;
    @Column(name = "EXPIRED_AT")
    private LocalDateTime expiredAt;
    @Column(name = "username")
    private String username;
    @Column(name = "IS_ACTIVE")
    private boolean active = true;

    @PrePersist
    void create() {
        this.createdAt = LocalDateTime.now();
        this.expiredAt = this.createdAt.plusDays(30);
    }
}
