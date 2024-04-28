package com.iitu.lesson.demo3.controller;

import com.iitu.lesson.demo3.domain.dto.LoginReq;
import com.iitu.lesson.demo3.domain.dto.LoginRes;
import com.iitu.lesson.demo3.domain.dto.RefreshReq;
import com.iitu.lesson.demo3.domain.dto.RegistrationReq;
import com.iitu.lesson.demo3.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AuthController.CONTROLLER_PATH)
@RequiredArgsConstructor
public class AuthController {

    public static final String CONTROLLER_PATH = "/auth";
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginRes> login(@RequestBody @Valid LoginReq loginReq) {
        return ResponseEntity.ok(authService.login(loginReq));
    }

    @PostMapping("/registration")
    public ResponseEntity<Void> registration(@RequestBody @Valid RegistrationReq registrationReq) {
        authService.registration(registrationReq);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginRes> refresh(@RequestBody @Valid RefreshReq refreshReq) {
        return ResponseEntity.ok(authService.getAccessTokenFromRefreshToken(refreshReq));
    }
}
