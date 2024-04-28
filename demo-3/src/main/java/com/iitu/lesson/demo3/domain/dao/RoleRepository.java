package com.iitu.lesson.demo3.domain.dao;

import com.iitu.lesson.demo3.domain.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findAllByTitle(String title);
}
