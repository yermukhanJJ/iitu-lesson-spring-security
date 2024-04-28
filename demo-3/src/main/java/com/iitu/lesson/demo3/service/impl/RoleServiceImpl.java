package com.iitu.lesson.demo3.service.impl;

import com.iitu.lesson.demo3.domain.dao.RoleRepository;
import com.iitu.lesson.demo3.domain.model.Role;
import com.iitu.lesson.demo3.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public List<Role> getDefaultRoles() {
        return roleRepository.findAllByTitle("USER");
    }
}
