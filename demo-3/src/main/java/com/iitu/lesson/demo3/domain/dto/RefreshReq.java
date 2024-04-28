package com.iitu.lesson.demo3.domain.dto;

import jakarta.validation.constraints.NotEmpty;

public record RefreshReq(@NotEmpty String refreshToken, @NotEmpty String username) {
}
