package com.kust.edujunction;

import com.kust.edujunction.entities.UserEntity;
import com.kust.edujunction.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@RequiredArgsConstructor
public class EdujunctionApplication implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${test.user.name}")
    String username;

    @Value("${test.user.password}")
    String password;

    public static void main(String[] args) {
        SpringApplication.run(EdujunctionApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        var user = userRepository.findByUsername(username);
        if (user == null) {
            user = new UserEntity();
            user.setUsername(username);
            user.setPassword(passwordEncoder.encode(password));
            userRepository.save(user);
        }
    }
}
