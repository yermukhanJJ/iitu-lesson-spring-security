package com.iitu.lesson.demo3.domain.dao;

import com.iitu.lesson.demo3.domain.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByTokenAndUsernameAndExpiredAtAfterAndActive(String token, String username, LocalDateTime now, boolean isActive);

    List<RefreshToken> findAllByExpiredAtBefore(LocalDateTime now);
}
