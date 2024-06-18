package com.example.springsecurity.service.impl;

import com.example.springsecurity.entity.impl.LoginUser;
import com.example.springsecurity.entity.user;
import com.example.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        user user = userService.findUserByName(username);
        Set<String> perms = new HashSet<String>();
        perms.add("*:*:*");
        return new LoginUser((long) user.getId(),user, perms);
    }
}
