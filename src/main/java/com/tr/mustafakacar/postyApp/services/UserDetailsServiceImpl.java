package com.tr.mustafakacar.postyApp.services;

import com.tr.mustafakacar.postyApp.entities.User;
import com.tr.mustafakacar.postyApp.repositories.UserRepository;
import com.tr.mustafakacar.postyApp.security.JwtUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      User user=userRepository.findByUsername(username);
        return JwtUserDetails.create(user);
    }
    public UserDetails loadUserById(long id){
        User user=userRepository.findById(id).get();
        return JwtUserDetails.create(user);
    }
}
