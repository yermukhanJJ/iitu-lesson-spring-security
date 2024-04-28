package com.iitu.lesson.demo3.domain.dto;

import jakarta.validation.constraints.NotEmpty;

public record RegistrationReq(@NotEmpty String firstname, @NotEmpty String lastname,
                              @NotEmpty String username, @NotEmpty String email,
                              @NotEmpty String password) {
}
