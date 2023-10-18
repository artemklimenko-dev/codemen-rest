package com.codemen.codemenrest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JWTCore {
    @Value("${testing.app.secret}")
    private String secret;
    @Value("${testing.app.expirationMs}")
    private int lifetime;
}
