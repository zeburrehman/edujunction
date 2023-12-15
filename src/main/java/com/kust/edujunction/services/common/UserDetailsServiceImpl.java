package com.kust.edujunction.services.common;

import com.kust.edujunction.common.CustomUserDetails;
import com.kust.edujunction.entities.UserEntity;
import com.kust.edujunction.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.debug("Entering in loadUserByUsername Method....");
        UserEntity user = userRepository.findByUsername(username);
        if (user == null) {
            logger.error("Username not found: {}", username);
            throw new UsernameNotFoundException("could not found user..." + username);
        }
        logger.info("User {} Authenticated Successfully", username);
        return new CustomUserDetails(user);
    }
}
