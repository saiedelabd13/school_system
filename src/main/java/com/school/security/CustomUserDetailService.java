package com.school.security;

import com.school.security.entity.AppUser;
import com.school.security.reposatory.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomUserDetailService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        @Autowired
        private final UserRepo userRepo;



        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            AppUser user = userRepo.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    user.getPassword(),
                    List.of(new SimpleGrantedAuthority(user.getRole()))
            );
        }    }
}
