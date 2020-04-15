package com.epam.lab.security.controller;

import com.epam.lab.security.model.AuthenticationRequest;
import com.epam.lab.security.model.AuthenticationResponse;
import com.epam.lab.security.model.SecurityUser;
import com.epam.lab.security.service.MyUserDetailsService;
import com.epam.lab.security.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/auth", consumes = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin("http://localhost:3000")
public class SecurityController {

    private AuthenticationManager authenticationManager;
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    public SecurityController(AuthenticationManager authenticationManager, MyUserDetailsService myUserDetailsService) {
        this.authenticationManager = authenticationManager;
        this.myUserDetailsService = myUserDetailsService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                authenticationRequest.getUsername(),
                authenticationRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(authToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String jwt = JwtUtil.generateToken((SecurityUser) authentication.getPrincipal());

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
