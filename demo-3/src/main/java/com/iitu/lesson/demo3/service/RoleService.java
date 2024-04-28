package com.iitu.lesson.demo3.service;

import com.iitu.lesson.demo3.domain.model.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {

    List<Role> getDefaultRoles();
}
