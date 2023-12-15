package com.kust.edujunction.controllers;

import com.kust.edujunction.dtos.AuthRequest;
import com.kust.edujunction.dtos.AuthenticationTokenDTO;
import com.kust.edujunction.services.common.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    @GetMapping("")
    public String getUsername(Principal principal) {
        return principal.getName();
    }

    @PostMapping("login")
    public ResponseEntity<AuthenticationTokenDTO> login(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.username(), authRequest.password())
        );
        if (!authentication.isAuthenticated()) throw new UsernameNotFoundException("User is not authenticated.");
        var token = jwtService.generateToken(authRequest.username());
        return ResponseEntity.ok(new AuthenticationTokenDTO(token));
    }
}
