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

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode("password");
        String password2 = passwordEncoder.encode("password2)");

        userRepository.save(
                new AppUser("Charly","charly","charly@digital.com",password, AppUserRole.ADMIN)
        );
        userRepository.save(
                new AppUser("Charly2","charly2","charly2@digital.com",password2, AppUserRole.USER)
        );

    }
}
