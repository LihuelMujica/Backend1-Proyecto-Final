package com.digitalhouse.clinic.web.security;

import com.digitalhouse.clinic.persistence.entity.AppUser;
import com.digitalhouse.clinic.persistence.jparepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AppUserService implements UserDetailsService {

     private final UserRepository userRepository;

     @Autowired
    public AppUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User email not found"));
        Set<GrantedAuthority> authorizations = new HashSet<>();
        GrantedAuthority authorization;

        authorization = new SimpleGrantedAuthority("ROLE_" +appUser.getAppUserRole());
        authorizations.add(authorization);

        return new User(
                appUser.getUsername(),
                appUser.getPassword(),
                true,true,true,true,
                authorizations
        );

     }
}
