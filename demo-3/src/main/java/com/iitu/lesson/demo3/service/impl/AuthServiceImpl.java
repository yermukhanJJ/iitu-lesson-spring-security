package com.iitu.lesson.demo3.service.impl;

import com.iitu.lesson.demo3.domain.dto.LoginReq;
import com.iitu.lesson.demo3.domain.dto.LoginRes;
import com.iitu.lesson.demo3.domain.dto.RefreshReq;
import com.iitu.lesson.demo3.domain.dto.RegistrationReq;
import com.iitu.lesson.demo3.domain.model.RefreshToken;
import com.iitu.lesson.demo3.domain.model.Role;
import com.iitu.lesson.demo3.domain.model.User;
import com.iitu.lesson.demo3.security.utils.JwtUtils;
import com.iitu.lesson.demo3.service.AuthService;
import com.iitu.lesson.demo3.service.RefreshTokenService;
import com.iitu.lesson.demo3.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtil;
    private final RefreshTokenService refreshTokenService;

    @Override
    public void registration(@NonNull RegistrationReq registrationReq) {
        userService.createUser(registrationReq);
    }

    @Override
    public LoginRes login(@NonNull LoginReq loginReq) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginReq.username(), loginReq.password())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = userService.getByUsername(loginReq.username());
        List<String> roles = user.getRoles().stream().map(Role::getTitle).toList();
        RefreshToken refreshToken = refreshTokenService.createNewRefreshToken(loginReq.username());
        return new LoginRes(authentication.getName(), jwtUtil.generateJwtToken(authentication),
                refreshToken.getToken(), roles);
    }

    @Override
    public LoginRes getAccessTokenFromRefreshToken(@NonNull RefreshReq refreshReq) {
        refreshTokenService.getRefreshTokenByUsernameAndToken(refreshReq.username(), refreshReq.refreshToken());
        User user = userService.getByUsername(refreshReq.username());
        String accessToken = jwtUtil.generateJwtTokenFromUserDetails(user);
        RefreshToken refreshToken = refreshTokenService.createNewRefreshToken(refreshReq.username());
        return new LoginRes(user.getUsername(), accessToken, refreshToken.getToken(), user.getRoles().stream()
                .map(Role::getTitle).toList());
    }
}
