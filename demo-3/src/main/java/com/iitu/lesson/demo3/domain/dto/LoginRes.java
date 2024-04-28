package com.iitu.lesson.demo3.domain.dto;

import java.util.List;

public record LoginRes(String username, String token, String refreshToken, List<String> roles) {
}
