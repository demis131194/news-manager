package com.epam.lab.security.service;

import com.epam.lab.dto.UserTo;
import com.epam.lab.exeption.ServiceException;
import com.epam.lab.security.model.SecurityUser;
import com.epam.lab.security.model.UserRole;
import com.epam.lab.service.UserService;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public MyUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        try {
            UserTo userByLogin = userService.findByLogin(login);
            Set<GrantedAuthority> grantedAuthorities = Sets.newHashSet();
            userByLogin.getRoles().forEach(role -> grantedAuthorities.add(UserRole.valueOf(role).getRoleGrantedAuthority()));

            SecurityUser securityUser = new SecurityUser(userByLogin.getId(), userByLogin.getLogin(), userByLogin.getPassword(), grantedAuthorities);
            securityUser.setName(userByLogin.getName());
            securityUser.setSurname(userByLogin.getSurname());
            return securityUser;
        } catch (ServiceException e) {
            throw new UsernameNotFoundException("User not found by login - " + login, e);
        }
    }
}
