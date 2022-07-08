package com.digitalhouse.clinic.web.security.config;

import com.digitalhouse.clinic.persistence.entity.AppUser;
import com.digitalhouse.clinic.persistence.entity.AppUserRole;
import com.digitalhouse.clinic.persistence.jparepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private final UserRepository userRepository;

    @Autowired
    public DataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void run(ApplicationArguments args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String adminPassword = passwordEncoder.encode("admin");
        String userPassword = passwordEncoder.encode("user");
        userRepository.save(new AppUser("User", "user", "user@digital.com",
                userPassword, AppUserRole.USER));
        userRepository.save(new AppUser("Admin", "admin", "admin@digital.com",
                adminPassword, AppUserRole.ADMIN));
    }
}
