package com.iitu.lesson.demo3.service;

import com.iitu.lesson.demo3.domain.dto.RegistrationReq;
import com.iitu.lesson.demo3.domain.model.User;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends UserDetailsService {

    User createUser(@NonNull RegistrationReq registrationReq);

    User getByUsername(@NonNull String username);

}
