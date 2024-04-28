package com.iitu.lesson.demo3.service.impl;

import com.iitu.lesson.demo3.domain.dao.RefreshTokenRepository;
import com.iitu.lesson.demo3.domain.exceptions.NotFoundException;
import com.iitu.lesson.demo3.domain.model.RefreshToken;
import com.iitu.lesson.demo3.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public RefreshToken createNewRefreshToken(@NonNull String username) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setUsername(username);
        log.info("Created new refresh token for user {}", username);
        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public RefreshToken getRefreshTokenByUsernameAndToken(@NonNull String username, @NonNull String token) {
        log.info("getting refresh token for user {}", username);

        RefreshToken refreshToken = refreshTokenRepository.findByTokenAndUsernameAndExpiredAtAfterAndActive(token, username,
                        LocalDateTime.now(), true)
                .orElseThrow(() -> new NotFoundException("Token expired"));
        refreshToken.setActive(false);
        refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }
}
