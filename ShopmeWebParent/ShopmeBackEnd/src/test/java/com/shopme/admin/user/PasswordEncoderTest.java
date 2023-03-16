package com.shopme.admin.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderTest {
    @Test
    void testEncoderPassword() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String rawPassword = "nam2020";
        String  encodedPassword = passwordEncoder.encode(rawPassword);

        System.out.println(encodedPassword);

        boolean matches  = passwordEncoder.matches(rawPassword,encodedPassword);

        Assertions.assertThat(matches).isTrue();


    }
}