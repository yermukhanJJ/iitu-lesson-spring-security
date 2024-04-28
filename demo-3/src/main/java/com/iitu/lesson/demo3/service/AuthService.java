package com.iitu.lesson.demo3.service;

import com.iitu.lesson.demo3.domain.dto.LoginReq;
import com.iitu.lesson.demo3.domain.dto.LoginRes;
import com.iitu.lesson.demo3.domain.dto.RefreshReq;
import com.iitu.lesson.demo3.domain.dto.RegistrationReq;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    void registration(@NonNull RegistrationReq registrationReq);

    LoginRes login(@NonNull LoginReq loginReq);

    LoginRes getAccessTokenFromRefreshToken(@NonNull RefreshReq refreshReq);
}
