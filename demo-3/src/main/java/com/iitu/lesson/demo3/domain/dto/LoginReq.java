package com.iitu.lesson.demo3.domain.dto;

import jakarta.validation.constraints.NotEmpty;

public record LoginReq(@NotEmpty String username, @NotEmpty String password) {
}
