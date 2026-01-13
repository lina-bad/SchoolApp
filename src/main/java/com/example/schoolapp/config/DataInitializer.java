package com.example.schoolapp.config;

import com.example.schoolapp.entity.Role;
import com.example.schoolapp.entity.User;
import com.example.schoolapp.repository.RoleRepository;
import com.example.schoolapp.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final String adminEmail;
    private final String adminPassword;

    public DataInitializer(RoleRepository roleRepository,
                           UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           @Value("${app.admin.email}") String adminEmail,
                           @Value("${app.admin.password}") String adminPassword) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.adminEmail = adminEmail;
        this.adminPassword = adminPassword;
    }

    @Override
    public void run(String... args) {
        Role adminRole = roleRepository.findByName("ADMIN")
                .orElseGet(() -> roleRepository.save(new Role("ADMIN")));
        roleRepository.findByName("TEACHER")
                .orElseGet(() -> roleRepository.save(new Role("TEACHER")));
        roleRepository.findByName("STUDENT")
                .orElseGet(() -> roleRepository.save(new Role("STUDENT")));

        userRepository.findByEmail(adminEmail).orElseGet(() -> {
            User admin = new User(adminEmail, passwordEncoder.encode(adminPassword));
            admin.setRoles(Set.of(adminRole));
            return userRepository.save(admin);
        });
    }
}
