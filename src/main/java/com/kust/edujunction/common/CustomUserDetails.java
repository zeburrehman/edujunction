package com.kust.edujunction.common;

import com.kust.edujunction.entities.UserEntity;
import com.kust.edujunction.entities.UserRoleEntity;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
public class CustomUserDetails extends UserEntity implements UserDetails {
    private final String username;
    private final String password;
    private Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(UserEntity user) {

        this.username = user.getUsername();
        this.password = user.getPassword();

        List<GrantedAuthority> auths = new ArrayList<>();
        auths.add(new SimpleGrantedAuthority("ADMIN"));
        for (UserRoleEntity role : this.getRoles()) {
            auths.add(new SimpleGrantedAuthority(role.getName().toUpperCase()));
        }
        this.authorities = auths;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
