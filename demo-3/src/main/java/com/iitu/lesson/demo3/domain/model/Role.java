package com.iitu.lesson.demo3.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ROLE")
@Getter
@Setter
@NoArgsConstructor
public class Role extends BaseEntity {
    @Column(name = "TITLE")
    private String title;
}
