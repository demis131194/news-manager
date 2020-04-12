package com.epam.lab.security.controller;

import com.epam.lab.security.model.AuthenticationRequest;
import com.epam.lab.security.model.AuthenticationResponse;
import com.epam.lab.security.model.SecurityUser;
import com.epam.lab.security.service.MyUserDetailsService;
import com.epam.lab.security.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/security")
public class SecurityController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(), authenticationRequest.getPassword());
            authenticationManager.authenticate(authToken);
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password!", e);
        }

        SecurityUser userDetails = (SecurityUser) myUserDetailsService.loadUserByUsername(authenticationRequest.getUserName());
        final String jwt = JwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
