package com.estacionamento.app.configuration.seeddatabase;

import com.estacionamento.app.entities.User;
import com.estacionamento.app.entities.enums.UserJobRole;
import com.estacionamento.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

@Profile("Dev")
@Configuration
public class SeedUserDatabase implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SeedUserDatabase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        User user = new User();

        user.setName("Admin");
        user.setEmail("admin@admin.com");
        user.setPassword(passwordEncoder.encode("admin@123"));
        user.setRole(UserJobRole.SUPER_ADMIN);

        userRepository.save(user);
    }
}
