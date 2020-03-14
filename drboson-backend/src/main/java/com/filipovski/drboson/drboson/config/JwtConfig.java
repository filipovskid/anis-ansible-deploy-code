package com.filipovski.drboson.drboson.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:security/secret.properties")
@Data
public class JwtConfig {

    @Value("${jwt.header:Authorization}")
    private String header;

    @Value("${jwt.prefix:Bearer }")
    private String prefix;

    @Value("${jwt.expiration:#{24*60*60}}")
    private int expiration;

    @Value("${jwt.secret:JwtSecretKey}")
    private String secret;
}
