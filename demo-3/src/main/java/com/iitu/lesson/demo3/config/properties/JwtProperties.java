package com.iitu.lesson.demo3.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "jwt.token")
public class JwtProperties {
    private String secret;
    private long ttl;
}
