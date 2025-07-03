package com.Usman.SpringSecurity.service;

import com.Usman.SpringSecurity.model.UserPrincipal;
import com.Usman.SpringSecurity.model.Users;
import com.Usman.SpringSecurity.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = repo.findByUsername(username);
        if(users == null){
            throw new UsernameNotFoundException("user not found");
        }
        return new UserPrincipal(users);
    }



}
