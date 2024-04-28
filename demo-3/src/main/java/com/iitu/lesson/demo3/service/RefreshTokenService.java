package com.iitu.lesson.demo3.service;

import com.iitu.lesson.demo3.domain.model.RefreshToken;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public interface RefreshTokenService {

    RefreshToken createNewRefreshToken(@NonNull String username);
    RefreshToken getRefreshTokenByUsernameAndToken(@NonNull String username, @NonNull String token);
}
