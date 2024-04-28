package com.iitu.lesson.demo3.service.impl;

import com.iitu.lesson.demo3.domain.dao.UserRepository;
import com.iitu.lesson.demo3.domain.dto.RegistrationReq;
import com.iitu.lesson.demo3.domain.exceptions.BadRequestException;
import com.iitu.lesson.demo3.domain.model.User;
import com.iitu.lesson.demo3.security.UserDetailsImpl;
import com.iitu.lesson.demo3.service.RoleService;
import com.iitu.lesson.demo3.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User createUser(RegistrationReq registrationReq) {

        if (userRepository.existsByUsername(registrationReq.username())) {
            throw new BadRequestException("User already exists");
        }

        User user = new User(
                registrationReq.firstname(),
                registrationReq.lastname(),
                registrationReq.username(),
                registrationReq.email(),
                passwordEncoder.encode(registrationReq.password()),
                roleService.getDefaultRoles()
        );

        return userRepository.save(user);
    }

    @Override
    public User getByUsername(@NonNull String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getByUsername(username);

        return new UserDetailsImpl(user);
    }
}
